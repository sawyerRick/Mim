package cn.sawyer.mim.server.handler;

import cn.sawyer.mim.tool.enums.MsgType;
import cn.sawyer.mim.tool.protocol.MimProtocol;
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

        MimProtocol protocol = (MimProtocol) obj;
        if (protocol.getType().equals(MsgType.MSG_REQ)) {
            System.out.println("[+] 服务器收到：" + protocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("[!] ServerHandler channelRead错误");
        cause.printStackTrace();
    }
}
