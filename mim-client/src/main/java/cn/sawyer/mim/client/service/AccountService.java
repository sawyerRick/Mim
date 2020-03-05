package cn.sawyer.mim.client.service;

import cn.sawyer.mim.tool.model.ServerInfo;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.enums.Code;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import cn.sawyer.mim.tool.protocol.req.PubReq;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 13:32
 **/

public interface AccountService {

    Code login(long userId, String username);

    Code sendMsg(MimProtocol protocol);

    void sendPublicMsg(PubReq pubReq);

    void logout(Long userId, String username);
}
