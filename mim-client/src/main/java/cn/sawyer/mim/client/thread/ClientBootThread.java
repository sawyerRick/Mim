package cn.sawyer.mim.client.thread;

import cn.sawyer.mim.client.cache.ClientCache;
import cn.sawyer.mim.client.client.MimClient;
import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.client.service.AccountService;
import cn.sawyer.mim.client.util.BeanContext;
import cn.sawyer.mim.tool.enums.Code;

/**
 * @program: mim
 * @description: 客户端连接线程
 * @author: sawyer
 * @create: 2020-02-25 12:09
 **/
public class ClientBootThread implements Runnable{

    MimClient mimClient;

    AccountService accountService;

    MimClientConfig appConfig;

    public ClientBootThread() {
        appConfig = BeanContext.getBean(MimClientConfig.class);
        accountService = BeanContext.getBean(AccountService.class);
        mimClient = BeanContext.getBean(MimClient.class);
    }

    @Override
    public void run() {

        Code loginCode = accountService.login(appConfig.getUserId(), appConfig.getUsername());
        if (loginCode.equals(Code.SUCCESS)) {
            mimClient.start(ClientCache.server);
        }
    }
}
