package cn.sawyer.mim.router.service.impl;

import cn.sawyer.mim.router.cache.RouterCache;
import cn.sawyer.mim.router.service.AccountService;
import cn.sawyer.mim.router.service.ServerService;
import cn.sawyer.mim.tool.constant.Constants;
import cn.sawyer.mim.tool.model.ServerInfo;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.enums.Code;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    ZooKeeper zooKeeper;

    @Autowired
    ServerService serverService;

    @Autowired
    RouterCache cache;

    @Override
    public Code login(Long userId, String username){
        // Redis key
        String nameKey = Constants.ACCOUNT_PREFIX + username;
        System.out.println("nameKey:" + nameKey);

        Long userIdFromDb = Long.parseLong(redisTemplate.opsForValue().get(nameKey));

        Long loginStatus;
        Code code = Code.SUCCESS;

        // 登录
        if (userIdFromDb.equals(userId)) {
            // 检查已登录
            loginStatus = redisTemplate.opsForSet().add(Constants.ONLINE_KEY, userId.toString());
            if (loginStatus == 0) {
                code = Code.DUPLICATE_LOGIN;
            }
        } else {
            code = Code.LOGIN_ERROR;
        }

        return code;
    }

    @Override
    public String route(Long userId) {
        // 配置id-server路由
        String server = serverService.select(cache.getAll());
        logger.info("保存路由：userId:" + userId + ", server:" + server);
        String key = Constants.ROUTED_KEY;
        redisTemplate.opsForHash().put(key, userId.toString(), server);

        return server;
    }

    @Override
    public Code registry(Long userId, String username) {

        String idKey = Constants.ACCOUNT_PREFIX + userId;
        String nameKey = Constants.ACCOUNT_PREFIX + username;

        Code code = Code.SUCCESS;

        String name = redisTemplate.opsForValue().get(idKey);
        if (name == null) {
            redisTemplate.opsForValue().set(idKey, username);
            redisTemplate.opsForValue().set(nameKey, userId.toString());
        } else {
            code = Code.DUPLICATE_REGISTRY;
        }

        return code;
    }

    @Override
    public Code logout(Long userId) {

        String key = Constants.ONLINE_KEY;
        redisTemplate.opsForSet().remove(key, userId.toString());
        logger.info("注销：" + userId);

        return Code.SUCCESS;
    }

    @Override
    public Boolean checkOnline(Long userId) {
        String key = Constants.ONLINE_KEY;
        System.out.println("检查在线：" + key);

        return redisTemplate.opsForSet().isMember(key, userId.toString());
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
