package cn.sawyer.mim.client.controller;

import cn.sawyer.mim.client.cache.ClientCache;
import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.tool.result.MsgType;
import cn.sawyer.mim.tool.model.MimMessage;
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

        MimMessage mimMessage = new MimMessage();
        mimMessage.setUserId(appConfig.getUserId());
        mimMessage.setUsername(appConfig.getUsername());

        mimMessage.setType(MsgType.MSG_REQ.value());
        mimMessage.setMsg("Hello server!!!");
        socketChannel.writeAndFlush(mimMessage);

        return "Client send...";
    }
}
