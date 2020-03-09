package cn.sawyer.mim.client.service;

import cn.sawyer.mim.tool.enums.Code;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import cn.sawyer.mim.tool.protocol.req.PshReq;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 13:32
 **/

public interface AccountService {

    Code login(long userId, String username);

    Code sendMsg(MimProtocol protocol);

    void sendPublicMsg(PshReq pshReq);

    void sendPrivateMsg(PshReq pshReq);

    void logout(Long userId, String username);
}
