package cn.sawyer.mim.server.service;

import cn.sawyer.mim.tool.protocol.req.PubReq;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-25 12:58
 **/
public interface ConnService {

    void send(PubReq pubReq);

}
