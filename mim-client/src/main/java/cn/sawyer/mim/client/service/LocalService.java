package cn.sawyer.mim.client.service;

import cn.sawyer.mim.tool.protocol.MimProtocol;
import cn.sawyer.mim.tool.protocol.req.PshReq;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 18:12
 **/

public interface LocalService {

    void printNormal(MimProtocol protocol);

    void printSystem(String msg);

    void commandHandle(PshReq req);
}
