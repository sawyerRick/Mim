package cn.sawyer.mim.server.handler;

import cn.sawyer.mim.tool.result.MsgType;
import cn.sawyer.mim.tool.model.MimMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 15:57
 **/
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接断开：" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) {

        MimMessage msg = (MimMessage) obj;
        if (msg.getType() != null && msg.getType().equals(MsgType.MSG_REQ.value())) {
            System.out.println("[+] 服务器收到消息！" + msg);
        }
//        System.out.println("msg = " + msg);
//        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[!] ServerHandler channelRead错误");
        cause.printStackTrace();
    }

    private MimMessage buildMsg() {
        MimMessage mimMessage = new MimMessage();
        mimMessage.setType(MsgType.MSG_RESP.value());
        return mimMessage;
    }
}
