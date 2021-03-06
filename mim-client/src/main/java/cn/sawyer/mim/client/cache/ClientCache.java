package cn.sawyer.mim.client.cache;

import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 11:26
 **/
public class ClientCache {

    // 持有可用服务器信息
    public static volatile String server;

    // 当前持有的服务器Channel
    public static volatile NioSocketChannel SvSocketHolder;
}
