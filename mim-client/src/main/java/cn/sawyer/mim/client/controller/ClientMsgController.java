package cn.sawyer.mim.client.controller;

import cn.sawyer.mim.client.cache.ClientCache;
import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.tool.enums.MsgType;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 11:24
 **/

@RestController
@RequestMapping("/")
public class ClientMsgController {

    @Autowired
    MimClientConfig appConfig;

    @GetMapping("/clientSend")
    public String clientSend() {

        NioSocketChannel socketChannel = ClientCache.SvSocketHolder;

        MimProtocol protocol = new MimProtocol();

        protocol.setType(MsgType.MSG_REQ);
        protocol.setMsg("Hello server!!!");
        System.out.println("发送：" + protocol);
        socketChannel.writeAndFlush(protocol);

        return "Client send...";
    }
}
