package cn.sawyer.mim.client.handler;

import cn.sawyer.mim.tool.result.MsgType;
import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.client.service.MimClientService;
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
        System.out.println("你已经断开连接... server:" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object object) {
        MimMessage msg = (MimMessage) object;

        // 消息回应
        if (msg.getType() != null && msg.getType().equals(MsgType.MSG_RESP.value())) {
            System.out.println("[+] 消息：" + msg.getMsg());
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
