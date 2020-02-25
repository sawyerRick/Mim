//package cn.sawyer.test;
//
//import org.apache.tomcat.jni.Time;
//import org.apache.zookeeper.CreateMode;
//import org.apache.zookeeper.KeeperException;
//import org.apache.zookeeper.ZooDefs;
//import org.apache.zookeeper.ZooKeeper;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * @program: mim
// * @description:
// * @author: sawyer
// * @create: 2020-02-21 10:12
// **/
//public class MyTest {
//
//    @Test
//    public void t() {
//        Time.sleep(10000);
//    }
//
//    @Test
//    public void testServerRegistry() throws KeeperException, InterruptedException, IOException {
//        String host = "localhost";
//        Integer port = 2181;
//        // 连接
//        ZooKeeper zooKeeper = new ZooKeeper(host + ":" + port, 2000, (e) -> {
//            System.out.println("触发了：" + e.getType());
//        });
//        // 创建临时节点
//        String node = "/route/xxxx:ip";
//        zooKeeper.exists(node, false);
//        String ans = zooKeeper.create(node, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
//        System.out.println("ans = " + ans);
//        synchronized (MyTest.class) {
//            MyTest.class.wait();
//        }
//    }
//
//    @Test
//    public void zkTest() throws KeeperException, InterruptedException, IOException {
//        String host = "localhost";
//        Integer port = 2181;
//        // 连接
//        ZooKeeper zooKeeper = new ZooKeeper(host + ":" + port, 2000, (e) -> {
//            System.out.println("触发了：" + e.getType());
//        });
//
//        // 创建父节点，返回路径
////        String path = zooKeeper.create("/coooool", "cool".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
////        System.out.println(path);
//
//        // 创建子节点
////        String children = zooKeeper.create("/coooool/aaaa", "cool".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
////        System.out.println("children = " + children);
//
////         获取节点
//        byte[] data = zooKeeper.getData("/coooool", false, null);
//        System.out.println(new String(data));
//
//        List<String> child = zooKeeper.getChildren("/coooool", false);
//        for (String s : child) {
//            System.out.println(s);
//        }
//
//        // 修改节点的值
////        Stat s = zooKeeper.setData("/coooool", "asdfasdf".getBytes(), -1);
////        System.out.println(s.toString());
//    }
//}
