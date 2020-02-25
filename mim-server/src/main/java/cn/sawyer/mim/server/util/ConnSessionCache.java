package cn.sawyer.mim.server.util;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 21:40
 **/
public class ConnSessionCache {

    public static final Map<Long, NioSocketChannel> channelMap = new ConcurrentHashMap<>();
}
