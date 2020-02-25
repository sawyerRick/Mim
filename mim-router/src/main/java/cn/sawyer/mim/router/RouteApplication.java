package cn.sawyer.mim.router;

import cn.sawyer.mim.tool.constant.Constants;
import cn.sawyer.mim.router.watch.ChildNodeWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 15:29
 **/

@SpringBootApplication
public class RouteApplication implements CommandLineRunner {

    @Autowired
    ZooKeeper zooKeeper;

    @Autowired
    ChildNodeWatcher childNodeWatcher;

    public static void main(String[] args) {
        SpringApplication.run(RouteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (zooKeeper.exists(Constants.ZK_SV_ROOT, false) == null) {
            System.out.println("创建根节点");
            zooKeeper.create(Constants.ZK_SV_ROOT, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        zooKeeper.getChildren(Constants.ZK_SV_ROOT, childNodeWatcher);
    }
}
