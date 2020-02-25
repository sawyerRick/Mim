package cn.sawyer.mim.server.service;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-21 10:31
 **/
public interface ZkRegistry {

    void registry(String host, Integer serverPort, Integer serverTcpPort);
}
