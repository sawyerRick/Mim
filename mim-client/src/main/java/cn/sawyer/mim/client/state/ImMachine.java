package cn.sawyer.mim.client.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: mim
 * @description: 无情的IM机器，只会在几种状态之间跳转。
 * @author: sawyer
 * @create: 2020-02-24 20:16
 **/

public class ImMachine implements State{

    State currState; // 实时状态

    State offlineState = new OfflineState(this);

    State onlineState = new OnlineState(this);

    State initState = new InitState(this);

    public ImMachine() {
        currState = initState;
    }

    @Override
    public void sendMsg() {
        currState.sendMsg();
    }

    @Override
    public void login() {
        currState.login();
    }

    @Override
    public void logout() {
        currState.logout();
    }

    @Override
    public void init() {
        currState.init();
    }

    public void setCurrState(State currState) {
        this.currState = currState;
    }

    public State getOfflineState() {
        return offlineState;
    }

    public State getOnlineState() {
        return onlineState;
    }

    public State getInitState() {
        return initState;
    }
}