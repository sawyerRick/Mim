package cn.sawyer.mim.server.handler;

import cn.sawyer.mim.tool.result.MsgType;
import cn.sawyer.mim.tool.model.MimMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;

import static cn.sawyer.mim.server.util.ConnSessionCache.channelMap;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 20:42
 **/
public class HandShakeHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MimMessage message = (MimMessage) msg;

        if (message != null && message.getType() == MsgType.HANDSHAKE_REQ.value()) {

            MimMessage mimMessage;
            mimMessage = buildResponse();
            System.out.println("[+] 握手请求:" + message);
            System.out.println("UserId: " + message.getUserId() + " 连接...");
            ctx.writeAndFlush(mimMessage);
            if (channelMap.containsKey(message.getUserId())) {
                // 清除重复连接
                NioSocketChannel channel = channelMap.get(message.getUserId());
                System.out.println("清除连接：" + channel.remoteAddress() + " by userId:" + message.getUserId());
                channelMap.remove(message.getUserId());
                channel.close();
            }
            channelMap.put(message.getUserId(), (NioSocketChannel) ctx.channel());
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private MimMessage buildResponse() {
        MimMessage message = new MimMessage();
        message.setType(MsgType.HANDSHAKE_RESP.value());

        return message;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        ctx.fireExceptionCaught(cause);
    }
}
