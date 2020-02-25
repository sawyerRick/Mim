package cn.sawyer.mim.client.state;

import cn.sawyer.mim.tool.result.Code;

/**
 * @program: mim
 * @description: 状态模式，表示用户状态
 * @author: sawyer
 * @create: 2020-02-24 20:03
 **/

public interface State {

    void sendMsg();

    void login();

    void logout();

    void init();
}
