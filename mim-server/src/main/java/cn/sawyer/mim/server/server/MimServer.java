package cn.sawyer.mim.server.server;

import cn.sawyer.mim.server.config.MimServerConfig;
import cn.sawyer.mim.server.handler.HandShakeHandler;
import cn.sawyer.mim.server.handler.HeartBeatHandler;
import cn.sawyer.mim.server.handler.ServerHandler;
import cn.sawyer.mim.tool.model.ServerInfo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @program: mim
 * @description: mim服务器
 * @author: sawyer
 * @create: 2020-02-21 10:43
 **/


@Component
public class MimServer {

    private static final Logger logger = LoggerFactory.getLogger(MimServer.class);

    @Autowired
    ZooKeeper zooKeeper;

    @Autowired
    MimServerConfig config;

    public void start() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap boot = new ServerBootstrap();
        boot.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO)) // 给boss添加Handler
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new ObjectDecoder(1024 * 1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        channel.pipeline().addLast(new ObjectEncoder());
                        channel.pipeline().addLast("readTimeoutHandler",new ReadTimeoutHandler(20));

                        channel.pipeline().addLast(new HandShakeHandler());
                        channel.pipeline().addLast(new HeartBeatHandler());
                        channel.pipeline().addLast(new ServerHandler());
                    }
                }).option(ChannelOption.SO_BACKLOG, 100)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        ServerInfo serverInfo = new ServerInfo(config.getHost(), config.getHttpPort(), config.getMsgPort());
        logger.info("[+] 服务端上线..." + serverInfo.toString());
        try {
            ChannelFuture future = boot.bind(serverInfo.getHost(), serverInfo.getMsgPort()).sync();
            future.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}