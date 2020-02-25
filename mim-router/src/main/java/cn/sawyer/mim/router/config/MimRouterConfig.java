package cn.sawyer.mim.router.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description: mim初始配置
 * @author: sawyer
 * @create: 2020-02-21 10:18
 **/

@Configuration
public class MimRouterConfig {

    @Value("${mim.config.zk.host}")
    String zkHost;

    @Value("${mim.config.zk.port}")
    Integer zkPort;

    public String getZkHost() {
        return zkHost;
    }

    public void setZkHost(String zkHost) {
        this.zkHost = zkHost;
    }

    public Integer getZkPort() {
        return zkPort;
    }

    public void setZkPort(Integer zkPort) {
        this.zkPort = zkPort;
    }

    @Override
    public String toString() {
        return "MimConfig{" +
                "zkHost='" + zkHost + '\'' +
                ", zkPort=" + zkPort +
                '}';
    }
}
