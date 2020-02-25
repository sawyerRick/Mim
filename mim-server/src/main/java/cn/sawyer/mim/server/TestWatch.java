package cn.sawyer.mim.server;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 21:04
 **/
public class TestWatch {

    static ZooKeeper zooKeeper;

    static String route = "/route";

    static List<String> avaServer = new ArrayList<>();

    public TestWatch() throws IOException {
    }

    public static void main(String[] args) throws Exception {

        String host = "localhost";
        Integer port = 2181;
        // 连接
        zooKeeper = new ZooKeeper(host + ":" + port, 2000, (event) -> {

            if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
                System.out.println("[+] NodeChildrenChanged!!!" + event.getPath());
                avaServer.clear();
                System.out.println("reGet:" + event.getPath());
                try {
                    avaServer = zooKeeper.getChildren(route, true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Ava server:");
                for (String s : avaServer) {
                    System.out.print(s + "，");
                }
            }
        });
        zooKeeper.getChildren(route, true);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
