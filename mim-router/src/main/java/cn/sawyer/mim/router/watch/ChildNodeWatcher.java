package cn.sawyer.mim.router.watch;

import cn.sawyer.mim.router.cache.ServerCache;
import cn.sawyer.mim.tool.constant.Constants;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: mim
 * @description: ZooKeeper监听器
 * @author: sawyer
 * @create: 2020-02-23 21:55
 **/

@Component
public class ChildNodeWatcher implements Watcher {

    @Autowired
    ZooKeeper zooKeeper;

    @Autowired
    ServerCache cache;

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
            System.out.println("[+] NodeChildrenChanged!!!" + event.getPath());
            System.out.println("[+] reGet:" + event.getPath());
            try {
                List<String> servers = zooKeeper.getChildren(Constants.ZK_SV_ROOT, this);
                cache.update(servers);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("[-] Ava server:");
            for (String s: cache.getAll()) {
                System.out.print(s+ ", ");
            }
            System.out.println();
        }
    }
}
