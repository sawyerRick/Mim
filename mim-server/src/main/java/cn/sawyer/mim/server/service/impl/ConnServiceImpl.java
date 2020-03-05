package cn.sawyer.mim.server.service.impl;

import cn.sawyer.mim.server.service.ConnService;
import cn.sawyer.mim.server.util.ConnSessionCache;
import cn.sawyer.mim.tool.enums.MsgType;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import cn.sawyer.mim.tool.protocol.req.PubReq;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Service;

/**
 * @program: mim
 * @description: 连接服务
 * @author: sawyer
 * @create: 2020-02-25 12:59
 **/

@Service
public class ConnServiceImpl implements ConnService {

    @Override
    public void send(PubReq pubReq) {
        MimProtocol protocol = new MimProtocol();
        protocol.setType(MsgType.MSG_RESP);
        protocol.setDestId(pubReq.getDestId());
        protocol.setSrcName(pubReq.getSrcName());
        protocol.setMsg(pubReq.getMsg());

        NioSocketChannel channel = ConnSessionCache.channelMap.get(protocol.getDestId());
        System.out.println("Channel cache 容量：" + ConnSessionCache.channelMap.size());
        if (ConnSessionCache.channelMap.size() == 0) {
            throw new RuntimeException("丢失路由");
        }

        if (channel != null) {
            System.out.println("发送：" + protocol);
            channel.writeAndFlush(protocol);
        } else {
            System.out.println("对方已经不在线:" + protocol.getDestId());
            ConnSessionCache.channelMap.remove(protocol.getDestId());
        }

    }
}
