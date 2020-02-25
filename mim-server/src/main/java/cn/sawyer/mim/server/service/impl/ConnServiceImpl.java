package cn.sawyer.mim.server.service.impl;

import cn.sawyer.mim.server.service.ConnService;
import cn.sawyer.mim.server.util.ConnSessionCache;
import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.tool.result.MsgType;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program: mim
 * @description: 连接服务
 * @author: sawyer
 * @create: 2020-02-25 12:59
 **/

@Service
public class ConnServiceImpl implements ConnService {

    @Override
    public void send(MimMessage msg) {
        msg.setType(MsgType.MSG_RESP.value());
        Long objectId = msg.getUserId();
        NioSocketChannel channel = ConnSessionCache.channelMap.get(objectId);
        System.out.println("Send:" + msg + " to :" + objectId);
        channel.writeAndFlush(msg);
    }
}
