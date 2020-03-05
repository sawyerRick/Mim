package cn.sawyer.mim.client.client;

import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.client.handler.ClientMsgHandler;
import cn.sawyer.mim.client.handler.HandshakeHandler;
import cn.sawyer.mim.client.handler.HeartBeatHandler;
import cn.sawyer.mim.tool.model.ServerInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 17:59
 **/

@Component
public class MimClient {

    @Autowired
    ClientMsgHandler clientMsgHandler;

    @Autowired
    HandshakeHandler handshakeHandler;

    @Autowired
    HeartBeatHandler heartBeatHandler;

    @Autowired
    MimClientConfig appConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(MimClient.class);

    private SocketChannel socketChannel;

    public void start(String server) {
        ServerInfo serverInfo = new ServerInfo(server);
        System.out.println("server:" + serverInfo);
        EventLoopGroup client = new NioEventLoopGroup();

        Bootstrap boot = new Bootstrap();

        boot.group(client)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        // 解码/编码
                        channel.pipeline().addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
                        channel.pipeline().addLast(new ObjectEncoder());
                        // 超时处理
                        channel.pipeline().addLast("readTimeoutHandler",new ReadTimeoutHandler(20));
                        // 握手
                        channel.pipeline().addLast(handshakeHandler);
                        // 心跳
                        channel.pipeline().addLast(heartBeatHandler);
                        // 消息处理
                        channel.pipeline().addLast(clientMsgHandler);
                    }
                });

        try {
            ChannelFuture future = boot.connect(serverInfo.getHost(), serverInfo.getMsgPort()).sync();
            if (future.isSuccess()) {
                socketChannel = (SocketChannel) future.channel();
                LOGGER.info("[+] Client启动成功...");
                System.out.print("\u001b[34m " + appConfig.getUsername() + "$ \u001b[0m");
            } else {
                LOGGER.error("[!] Client启动失败...");
                throw new RuntimeException("Client启动失败...");
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("客户端连接关闭...");
            client.shutdownGracefully();
        }
    }
}
