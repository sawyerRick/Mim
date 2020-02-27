package cn.sawyer.mim.router.service;

import cn.sawyer.mim.tool.model.ServerInfo;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.enums.Code;

import java.util.Map;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 16:00
 **/

public interface AccountService {

    Code login(Long userId, String username);

    String route(Long userId);

    Code registry(Long userId, String username);

    Code logout(Long userId);

    Boolean checkOnline(Long userId);

    Map<Long, String> parseRouterMap();
}
