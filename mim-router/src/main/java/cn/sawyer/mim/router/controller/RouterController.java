package cn.sawyer.mim.router.controller;

import cn.sawyer.mim.router.cache.RouterCache;
import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.tool.model.ServerInfo;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.result.Code;
import cn.sawyer.mim.tool.result.Results;
import cn.sawyer.mim.router.service.AccountService;
import cn.sawyer.mim.router.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.sawyer.mim.tool.result.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 15:30
 **/

@RestController
@RequestMapping("/")
public class RouterController {

    @Autowired
    AccountService accountService;

    @Autowired
    ServerService serverService;

    @Autowired
    RouterCache cache;

    // 私聊
    @PostMapping("privateMsg")
    public Result privateMsg(@RequestBody MimMessage mimMessage) {


        return Results.newResult(Code.SUCCESS);
    }

    // 群发
    @PostMapping("publicMsg")
    public Result publicMsg(@RequestBody MimMessage mimMessage) {
        System.out.println("recv:" + mimMessage);

//        boolean online = accountService.checkOnline(mimMessage.getUserId());
        if (true) {
            Map<Long, String> routerMap = accountService.parseRouterMap();
            System.out.println("route size:" + routerMap.size());
            for (Map.Entry<Long, String> entry : routerMap.entrySet()) {
                Long userId = entry.getKey();
                ServerInfo serverInfo = new ServerInfo(entry.getValue());
                // 跳过自己
                if (!userId.equals(mimMessage.getUserId())) {
                    mimMessage.setUserId(userId);
                    serverService.distribute(mimMessage, serverInfo);
                } else {
                    System.out.println("跳过自己" + userId + ", " + mimMessage.getUserId());
                }
            }
        }

        return Results.newResult(Code.SUCCESS);
    }

    // 登录
    @PostMapping("login")
    public Result login(@RequestBody UserInfo userInfo) {
        System.out.println("LOGIN :" + userInfo);
        Code loginCode = accountService.login(userInfo);

        if (loginCode.equals(Code.DUPLICATE_LOGIN)) {
            // 重复登录，强制下线
            accountService.logout(userInfo);
        }

        return Results.newResult(Code.SUCCESS);
    }

    // 获取服务器
    @PostMapping("avaServer")
    public Result getAvaServer(@RequestBody UserInfo userInfo) {

        // 为Client路由服务器
        ServerInfo server = accountService.selectServer(userInfo);
        System.out.println("返回路由服务器：" + server);

        return Results.newResult(Code.SUCCESS, server);
    }

    // 注销
    @GetMapping("logout")
    public Result logout(UserInfo userInfo) {

        if (userInfo.getUserId() != null) {
            accountService.logout(userInfo);
        }

        return Results.newResult(Code.SUCCESS);
    }

    // 注册
    @PostMapping("registry")
    public Result registry(UserInfo userInfo) {
        Code registryCode = accountService.registry(userInfo);
        Map<String, Object> data = new HashMap<>();

        // 注册
        if (registryCode.equals(Code.SUCCESS)) {
            data.put("userInfo", userInfo);
        }

        return Results.newResult(Code.SUCCESS, data);
    }

}
