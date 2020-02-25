package cn.sawyer.mim.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-25 11:15
 **/

@Configuration
public class MimClientConfig {

    @Value("${mim.config.userId}")
    private Long userId;

    @Value("${mim.config.url.avaServer}")
    private String urlAvaServer;

    @Value("${mim.config.username}")
    private String username;

    @Value("${mim.config.url.login}")
    private String urlLogin;

    @Value("${mim.config.url.publicMsg}")
    private String urlPublicMsg;

    @Value("${mim.config.router.host}")
    private String routerHost;

    @Value("${mim.config.router.port}")
    private String routerPort;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrlLogin() {
        return urlLogin;
    }

    public void setUrlLogin(String urlLogin) {
        this.urlLogin = urlLogin;
    }

    public String getUrlPublicMsg() {
        return urlPublicMsg;
    }

    public void setUrlPublicMsg(String urlPublicMsg) {
        this.urlPublicMsg = urlPublicMsg;
    }

    public String getRouterHost() {
        return routerHost;
    }

    public void setRouterHost(String routerHost) {
        this.routerHost = routerHost;
    }

    public String getRouterPort() {
        return routerPort;
    }

    public void setRouterPort(String routerPort) {
        this.routerPort = routerPort;
    }

    public String getUrlAvaServer() {
        return urlAvaServer;
    }

    public void setUrlAvaServer(String urlAvaServer) {
        this.urlAvaServer = urlAvaServer;
    }
}
