package cn.sawyer.mim.server.config;

import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @program: mim
 * @description: ZooKeeper注入
 * @author: sawyer
 * @create: 2020-02-21 10:16
 **/

@Configuration
public class BeanConfig {

    @Autowired
    MimServerConfig mimServerConfig;

    @Bean
    ZooKeeper zooKeeper() throws IOException {
        return new ZooKeeper(mimServerConfig.getZkHost() + ":" + mimServerConfig.getZkPort(), 2000, (e) -> {
        });
    }
}
