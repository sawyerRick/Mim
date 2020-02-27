package cn.sawyer.mim.client.handler;

import cn.sawyer.mim.tool.enums.MsgType;
import cn.sawyer.mim.client.service.MimClientService;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 18:02
 **/
@Component
public class ClientMsgHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ClientMsgHandler.class);

    @Autowired
    MimClientService service;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("已经断开连接... remote:" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object object) {
        MimProtocol protocol = (MimProtocol) object;

        // 消息回应
        if (protocol.getType() != null && protocol.getType().equals(MsgType.MSG_RESP)) {
            System.out.println("[+] 收到消息：" + protocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
