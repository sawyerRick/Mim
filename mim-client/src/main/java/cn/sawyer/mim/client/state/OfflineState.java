package cn.sawyer.mim.client.state;

import cn.sawyer.mim.client.service.AccountService;
import cn.sawyer.mim.client.util.BeanContext;
import cn.sawyer.mim.tool.result.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description: 离线状态
 * @author: sawyer
 * @create: 2020-02-24 20:05
 **/
public class OfflineState implements State{


    ImMachine imMachine;

    AccountService service;

    public OfflineState(ImMachine imMachine) {
        service = BeanContext.getBean(AccountService.class);
        System.out.println("SEE HERE");
        System.out.println(service);
        this.imMachine = imMachine;
    }

    @Override
    public void sendMsg() {
        System.out.println("尚未登录...");
    }

    @Override
    public void login() {
        System.out.println("登录中...");
        imMachine.setCurrState(imMachine.getOnlineState());
    }

    @Override
    public void logout() {
        System.out.println("尚未登录...");
    }

    @Override
    public void init() {
        System.out.println("不能逆初始化");
    }
}
