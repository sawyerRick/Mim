package cn.sawyer.mim.router.config;

import okhttp3.OkHttpClient;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @program: mim
 * @description: ZooKeeper注入
 * @author: sawyer
 * @create: 2020-02-21 10:16
 **/

@Configuration
public class BeanConfig {

    @Autowired
    MimRouterConfig mimRouterConfig;

    @Bean
    ZooKeeper zooKeeper() throws IOException {
        // 连接并监听ZK_SV_ROOT
        return new ZooKeeper(mimRouterConfig.getZkHost() + ":" + mimRouterConfig.getZkPort(), 2000, (event) -> System.out.println("ZooKeeper连接成功... event type:" + event.getType()));
    }

    @Bean
    public OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);
        return builder.build();
    }
}
