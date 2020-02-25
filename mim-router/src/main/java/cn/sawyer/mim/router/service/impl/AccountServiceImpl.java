package cn.sawyer.mim.router.service.impl;

import cn.sawyer.mim.router.cache.RouterCache;
import cn.sawyer.mim.router.service.AccountService;
import cn.sawyer.mim.router.service.ServerService;
import cn.sawyer.mim.tool.constant.Constants;
import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.tool.model.ServerInfo;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.result.Code;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-23 15:55
 **/

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    ZooKeeper zooKeeper;

    @Autowired
    ServerService serverService;

    @Autowired
    RouterCache cache;

    @Override
    public Code login(UserInfo userInfo){
        // Redis key
        String key = Constants.ACCOUNT_PREFIX + userInfo.getUsername();

        Long userIdFromDb = Long.valueOf(redisTemplate.opsForValue().get(key));

        Long res;
        Code code = Code.SUCCESS;

        // 登录
        if (userIdFromDb.equals(userInfo.getUserId())) {
            // 检查已登录
            res = redisTemplate.opsForSet().add(Constants.ONLINE, userInfo.getUserId().toString());
            code = res == 1 ? Code.DUPLICATE_LOGIN : code;
        } else {
            code = Code.LOGIN_ERROR;
        }

        return code;
    }

    @Override
    public Code registry(UserInfo userInfo) {
        String key = Constants.ACCOUNT_PREFIX + userInfo.getUserId();
        String dupKey = Constants.ACCOUNT_PREFIX + userInfo.getUsername();
        Code code = Code.SUCCESS;

        String name = redisTemplate.opsForValue().get(key);
        if (name == null) {
            redisTemplate.opsForValue().set(key, userInfo.getUsername());
            redisTemplate.opsForValue().set(dupKey, userInfo.getUserId().toString());
        } else {
            code = Code.DUPLICATE_REGISTRY;
        }

        return code;
    }

    @Override
    public Code logout(UserInfo userInfo) {

        if (userInfo.getUserId() != null) {
            String key = Constants.ONLINE;
            Long logoutOk = redisTemplate.opsForSet().remove(key, userInfo.getUserId().toString());
            System.out.println("注销状态：" + logoutOk);
        }

        return Code.SUCCESS;
    }

    @Override
    public ServerInfo selectServer(UserInfo userInfo) {
        ServerInfo serverInfo = null;

        boolean online = checkOnline(userInfo.getUserId());
        if (true) {
            String server = serverService.select(cache.getAll());
            serverInfo = new ServerInfo(server);

            String key = Constants.ROUTED_KEY;

            // 保存userId-server路由状态
            System.out.println("userId:" + userInfo.getUserId() + ", server:" + server);
            redisTemplate.opsForHash().put(key, userInfo.getUserId().toString(), server);
        } else {
            System.out.println("不在线");
        }

        return serverInfo;
    }

    @Override
    public boolean checkOnline(Long userId){
        String key = Constants.ONLINE;
        System.out.println("检查在线：" + key);
        boolean online = redisTemplate.opsForSet().isMember(key, userId.toString());

        return online;
    }

    @Override
    public Map<Long, String> parseRouterMap() {
        Map<Long, String> map = new HashMap<>();

        Set ids = redisTemplate.opsForHash().keys(Constants.ROUTED_KEY);
        for (Object id : ids) {
            String server = (String) redisTemplate.opsForHash().get(Constants.ROUTED_KEY, id);
            map.put(Long.parseLong(id.toString()), server);
        }

        return map;
    }

}
