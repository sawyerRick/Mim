package cn.sawyer.mim.client.init;

import cn.sawyer.mim.client.cache.ClientCache;
import cn.sawyer.mim.client.client.MimClient;
import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.tool.model.ServerInfo;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.result.Code;
import cn.sawyer.mim.client.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 00:43
 **/
@Component
public class InitClient {

    @Autowired
    AccountService accountService;


    @Autowired
    MimClient client;

    @Autowired
    MimClientConfig clientConfig;

    @PostConstruct
    public void initClient() {
        UserInfo userInfo = new UserInfo(clientConfig.getUserId(), clientConfig.getUsername());

        // 登录
        Code code = accountService.login();

        if (code.equals(Code.SUCCESS)) {
            System.out.println("登录成功" + code.toString());
            // 获取服务器路由
            ServerInfo serverInfo = accountService.getAvaServer(userInfo);
            if (serverInfo != null) {
                ClientCache.serverInfoHolder = serverInfo;
                System.out.println("获取路由信息成功");
            } else {
                System.out.println("获取路由信息失败");
            }
        } else {
            System.out.println("登录失败" + code.toString());
        }
    }

}
