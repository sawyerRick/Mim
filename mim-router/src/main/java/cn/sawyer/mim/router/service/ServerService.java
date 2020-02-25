package cn.sawyer.mim.router.service;

import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.tool.model.ServerInfo;

import java.util.List;

/**
 * @program: mim
 * @description: 均衡负载算法，
 * @author: sawyer
 * @create: 2020-02-23 20:09
 **/
public interface ServerService {

    String select(List<String> list);

    void distribute(MimMessage msg, ServerInfo serverInfo);
}
