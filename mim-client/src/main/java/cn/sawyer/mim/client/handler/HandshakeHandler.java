package cn.sawyer.mim.client.handler;

import cn.sawyer.mim.client.cache.ClientCache;
import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.tool.result.MsgType;
import cn.sawyer.mim.tool.model.MimMessage;
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
        MimMessage message = (MimMessage) msg;
        // 检查 握手回应
        if (message.getType() != null && message.getType() == MsgType.HANDSHAKE_RESP.value()) {
            System.out.println("握手成功！");
            ClientCache.SvSocketHolder = (NioSocketChannel) ctx.channel();
        }
        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    private MimMessage buildHandShakeReq() {
        MimMessage message = new MimMessage();
        message.setUserId(appConfig.getUserId());
        message.setType(MsgType.HANDSHAKE_REQ.value());

        return message;
    }
}
