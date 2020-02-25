package cn.sawyer.mim.server.controller;

import cn.sawyer.mim.server.service.ConnService;
import cn.sawyer.mim.tool.result.Code;
import cn.sawyer.mim.tool.result.MsgType;
import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.server.util.ConnSessionCache;
import cn.sawyer.mim.tool.result.Result;
import cn.sawyer.mim.tool.result.Results;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 21:44
 **/
@RestController
@RequestMapping("/")
public class MsgController {

    @Autowired
    ConnService connService;

    @PostMapping("distribute")
    public Result distribute(@RequestBody MimMessage mimMessage) {
        connService.send(mimMessage);

        return Results.newResult(Code.SUCCESS);
    }

    @GetMapping("back")
    public String sendBack() {
        int time = 0;
        System.out.println("Client nums:" + ConnSessionCache.channelMap.size());
        for (NioSocketChannel channel : ConnSessionCache.channelMap.values()) {
            MimMessage msg = new MimMessage();
            msg.setMsg("Hello Client");
            msg.setType(MsgType.MSG_RESP.value());
            channel.writeAndFlush(msg);
            ++time;
            System.out.println("Send to:" + channel.remoteAddress());
        }

        return "send " + time + " times...";
    }

}
