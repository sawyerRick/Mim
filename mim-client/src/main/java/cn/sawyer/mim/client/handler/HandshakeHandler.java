package cn.sawyer.mim.client.handler;

import cn.sawyer.mim.client.cache.ClientCache;
import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.tool.enums.MsgType;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 20:18
 **/
@Component
public class HandshakeHandler extends ChannelInboundHandlerAdapter {

    @Autowired
    MimClientConfig appConfig;

    private static final Logger logger = LoggerFactory.getLogger(HandshakeHandler.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 握手请求
        ctx.writeAndFlush(buildHandShakeReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MimProtocol protocol = (MimProtocol) msg;
        // 检查 握手回应
        if (protocol.getType() != null && protocol.getType() == MsgType.HANDSHAKE_RESP) {
            System.out.println("握手成功！");
            ClientCache.SvSocketHolder = (NioSocketChannel) ctx.channel();
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    private MimProtocol buildHandShakeReq() {
        MimProtocol protocol = new MimProtocol();
        protocol.setSrcId(appConfig.getUserId());
        protocol.setType(MsgType.HANDSHAKE_REQ);

        return protocol;
    }
}
