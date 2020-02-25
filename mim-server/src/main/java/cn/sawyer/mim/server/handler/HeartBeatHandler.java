package cn.sawyer.mim.server.handler;

import cn.sawyer.mim.tool.result.MsgType;
import cn.sawyer.mim.tool.model.MimMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 21:00
 **/
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        MimMessage message = (MimMessage) msg;
        // 返回心跳应答消息
        if (message != null
                && message.getType() == MsgType.HEARTBEAT_REQ
                .value()) {
            MimMessage heartBeat = buildHeatBeat();
            System.out
                    .println("Send heart beat response message to client " + ctx.channel().remoteAddress() +  ": ---> " + new Date().toString());
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.fireExceptionCaught(cause);
    }

    private MimMessage buildHeatBeat() {
        MimMessage message = new MimMessage();
        message.setType(MsgType.HEARTBEAT_RESP.value());
        return message;
    }
}
