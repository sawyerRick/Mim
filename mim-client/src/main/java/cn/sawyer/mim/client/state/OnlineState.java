package cn.sawyer.mim.client.state;

import cn.sawyer.mim.client.service.AccountService;
import cn.sawyer.mim.tool.result.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description: 在线状态
 * @author: sawyer
 * @create: 2020-02-24 20:04
 **/
public class OnlineState implements State{

    ImMachine imMachine;

    public OnlineState(ImMachine imMachine) {
        this.imMachine = imMachine;
    }

    @Override
    public void sendMsg() {
        System.out.println("请输入消息...");
    }

    @Override
    public void login() {
        System.out.println("已经登录");
    }

    @Override
    public void logout() {
        System.out.println("正在注销...");
        imMachine.setCurrState(imMachine.getOfflineState());
    }

    @Override
    public void init() {
        System.out.println("不能逆初始化...");
    }
}
