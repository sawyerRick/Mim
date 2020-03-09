package cn.sawyer.mim.router.cache;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: mim
 * @description: 可用服务器缓存
 * @author: sawyer
 * @create: 2020-02-23 21:57
 **/

@Component
public class ServerCache {

    @Autowired
    ZooKeeper zooKeeper;

    private Map<String, String> cache = new ConcurrentHashMap<>(128);

    public List<String> getAll() {

        List<String> list = new ArrayList<>();
        for (Map.Entry<String, String> entry : cache.entrySet()) {
            list.add(entry.getKey());
        }

        return list;
    }

    public void update(List<String> newList) {
        cache.clear();
        for (String s : newList) {
            cache.put(s, s);
        }
    }
}
