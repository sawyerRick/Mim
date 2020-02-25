package cn.sawyer.mim.client.state;

import cn.sawyer.mim.tool.result.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 20:17
 **/
public class InitState implements State {

    ImMachine imMachine;

    public InitState(ImMachine imMachine) {
        this.imMachine = imMachine;
    }

    @Override
    public void sendMsg() {
        System.out.println("尚未初始化...");
    }

    @Override
    public void login() {
        System.out.println("尚未初始化...");
    }

    @Override
    public void logout() {
        System.out.println("尚未初始化...");
    }

    @Override
    public void init() {
        System.out.println("初始化中...");
        imMachine.setCurrState(imMachine.getOfflineState());
        System.out.println("Init -> Offline");
    }
}
