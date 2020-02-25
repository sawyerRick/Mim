package cn.sawyer.mim.client.service;

import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.tool.model.ServerInfo;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.result.Code;
import org.springframework.stereotype.Service;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 13:32
 **/

public interface AccountService {

    Code login();

    Code sendMsg(MimMessage msg);

    void sendPublicMsg(MimMessage msg);

    ServerInfo getAvaServer(UserInfo userInfo);
}
