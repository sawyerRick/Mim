package cn.sawyer.mim.server.service.impl;

import cn.sawyer.mim.server.service.ZkRegistry;
import cn.sawyer.mim.tool.constant.Constants;
import cn.sawyer.mim.tool.model.ServerInfo;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: mim
 * @description: zk注册服务
 * @author: sawyer
 * @create: 2020-02-21 10:33
 **/
@Service
public class ZkRegistryImpl implements ZkRegistry {

    @Autowired
    ZooKeeper zooKeeper;

    private static final Logger logger = LoggerFactory.getLogger(ZkRegistry.class);

    @Override
    public void registry(String host, Integer httpPort, Integer msgPort) {
        ServerInfo serverInfo = new ServerInfo(host, httpPort, msgPort);
        try {

            if (zooKeeper.exists(Constants.ZK_SV_ROOT, false) == null) {
                System.out.println("根节点创建成功");
                zooKeeper.create(Constants.ZK_SV_ROOT, "alive".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            String path = zooKeeper.create(Constants.ZK_SV_ROOT + "/" + serverInfo.toString(), "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            logger.info("[+] 服务端注册成功:" + path);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("[-] 服务端注册失败:" + serverInfo);
        }
    }
}
