package cn.sawyer.mim.router.controller;

import cn.sawyer.mim.router.cache.RouterCache;
import cn.sawyer.mim.tool.enums.Code;
import cn.sawyer.mim.tool.protocol.req.LoginReq;
import cn.sawyer.mim.tool.protocol.req.PubReq;
import cn.sawyer.mim.tool.result.Results;
import cn.sawyer.mim.router.service.AccountService;
import cn.sawyer.mim.router.service.ServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.sawyer.mim.tool.result.Result;

import java.util.HashMap;
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

    private static final Logger logger = LoggerFactory.getLogger(RouterController.class);

    @Autowired
    AccountService accountService;

    @Autowired
    ServerService serverService;

    @Autowired
    RouterCache cache;

    // 私聊
//    @PostMapping("ptv")
//    public Result privateMsg(@RequestBody  mimMessage) {
//
//
//        return Results.newResult(Code.SUCCESS);
//    }

    // 群发
    @PostMapping("pub")
    public Result publicMsg(@RequestBody PubReq pubReq) {
        System.out.println("群发请求:" + pubReq);
        boolean online = accountService.checkOnline(pubReq.getSrcId());
        if (online) {
            Map<Long, String> routerMap = accountService.parseRouterMap();
            for (Map.Entry<Long, String> entry : routerMap.entrySet()) {
                Long destId = entry.getKey();
                String server = entry.getValue();
                // 跳过自己
                if (!destId.equals(pubReq.getSrcId())) {
                    pubReq.setDestId(destId);
                    serverService.pub(pubReq, server);
                } else {
                    System.out.println("跳过了自己...");
                }
            }
        }

        return Results.newResult(Code.SUCCESS);
    }

    // 登录
    @PostMapping("login")
    public Result login(@RequestBody LoginReq loginReq) {
        Map<String, Object> data = new HashMap<>();
        Code loginCode = accountService.login(loginReq.getUserId(), loginReq.getUsername());
        if (loginCode.equals(Code.SUCCESS)) {
            String server = accountService.route(loginReq.getUserId());
            data.put("server", server);
        } else if (loginCode.equals(Code.DUPLICATE_LOGIN)) {
            // 重复登录，强制下线
            accountService.logout(loginReq.getUserId());
        }

        return Results.newResult(Code.SUCCESS, data);
    }

    // 注销
    @GetMapping("logout")
    public Result logout(@RequestBody LoginReq loginReq) {

        accountService.logout(loginReq.getUserId());

        return Results.newResult(Code.SUCCESS);
    }

    // 注册
    @PostMapping("registry")
    public Result registry(@RequestBody LoginReq loginReq) {
        Code registryCode = accountService.registry(loginReq.getUserId(), loginReq.getUsername());
        Map<String, Object> data = new HashMap<>();

        // 注册
        if (registryCode.equals(Code.SUCCESS)) {
            data.put("userInfo", loginReq);
        }

        return Results.newResult(Code.SUCCESS, data);
    }

}
