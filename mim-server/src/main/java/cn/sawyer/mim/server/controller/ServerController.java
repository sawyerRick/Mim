package cn.sawyer.mim.server.controller;

import cn.sawyer.mim.server.service.ConnService;
import cn.sawyer.mim.tool.enums.Code;
import cn.sawyer.mim.tool.enums.MsgType;
import cn.sawyer.mim.server.util.ConnSessionCache;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import cn.sawyer.mim.tool.protocol.req.PshReq;
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
public class ServerController {

    @Autowired
    ConnService connService;

    @PostMapping("push")
    public Result pubMsg(@RequestBody PshReq pshReq) {
        System.out.println("推送请求：" + pshReq);
        connService.send(pshReq);

        return Results.newResult(Code.SUCCESS);
    }

    // 手动测试回应
    @GetMapping("back")
    public String sendBack() {
        int time = 0;
        System.out.println("Client nums:" + ConnSessionCache.channelMap.size());
        for (NioSocketChannel channel : ConnSessionCache.channelMap.values()) {
            MimProtocol protocol = new MimProtocol();
            protocol.setMsg("Hello Client");
            protocol.setType(MsgType.MSG_RESP);
            channel.writeAndFlush(protocol);
            ++time;
            System.out.println("Send to:" + channel.remoteAddress());
        }

        return "send " + time + " times...";
    }

}
