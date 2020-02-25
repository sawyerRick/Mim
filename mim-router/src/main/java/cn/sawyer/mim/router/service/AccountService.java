package cn.sawyer.mim.router.service;

import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.tool.model.ServerInfo;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.result.Code;

import java.util.List;
import java.util.Map;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 16:00
 **/

public interface AccountService {

    Code login(UserInfo userInfo);

    Code registry(UserInfo userInfo);

    Code logout(UserInfo userInfo);

    ServerInfo selectServer(UserInfo userInfo);

    boolean checkOnline(Long userId);

    Map<Long, String> parseRouterMap();
}
