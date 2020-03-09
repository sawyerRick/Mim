package cn.sawyer.mim.router.service;

import cn.sawyer.mim.tool.protocol.req.PshReq;

import java.util.List;

/**
 * @program: mim
 * @description: 均衡负载算法，
 * @author: sawyer
 * @create: 2020-02-23 20:09
 **/
public interface ServerService {

    String select(List<String> list);

    void push(PshReq pshReq, String server);
}
