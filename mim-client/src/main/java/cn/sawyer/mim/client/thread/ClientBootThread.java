package cn.sawyer.mim.client.thread;

import cn.sawyer.mim.client.cache.ClientCache;
import cn.sawyer.mim.client.client.MimClient;
import cn.sawyer.mim.client.util.BeanContext;

/**
 * @program: mim
 * @description: 客户端连接线程
 * @author: sawyer
 * @create: 2020-02-25 12:09
 **/
public class ClientBootThread implements Runnable{

    MimClient mimClient;

    public ClientBootThread() {
        mimClient = BeanContext.getBean(MimClient.class);
    }

    @Override
    public void run() {
        mimClient.start(ClientCache.serverInfoHolder);
    }
}
