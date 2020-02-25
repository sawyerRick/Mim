package cn.sawyer.mim.server.config;

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
public class MimServerConfig {

    @Value("${mim.config.zk.host}")
    String zkHost;

    @Value("${mim.config.zk.port}")
    Integer zkPort;

    @Value(("${mim.config.server.host}"))
    String host;

    @Value("${mim.config.server.port}")
    Integer httpPort;

    @Value("${mim.config.server.msgPort}")
    Integer msgPort;

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
    }

    public Integer getMsgPort() {
        return msgPort;
    }

    public void setMsgPort(Integer msgPort) {
        this.msgPort = msgPort;
    }

    @Override
    public String toString() {
        return "MimConfig{" +
                "zkHost='" + zkHost + '\'' +
                ", zkPort=" + zkPort +
                ", host='" + host + '\'' +
                ", httpPort=" + httpPort +
                ", msgPort=" + msgPort +
                '}';
    }
}
