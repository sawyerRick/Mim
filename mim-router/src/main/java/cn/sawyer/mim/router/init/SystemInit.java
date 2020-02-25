package cn.sawyer.mim.router.init;

import cn.sawyer.mim.router.cache.RouterCache;
import cn.sawyer.mim.tool.constant.Constants;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 00:06
 **/
@Component
public class SystemInit {

    @Autowired
    RouterCache cache;

    @Autowired
    ZooKeeper zooKeeper;

    @PostConstruct
    private void init() throws Exception {
        // 更新可用服务器缓存。
        cache.update(zooKeeper.getChildren(Constants.ZK_SV_ROOT, false));
        System.out.println("可用服务器缓存：");
        for (String s : cache.getAll()) {
            System.out.println(s);
        }
    }
}
