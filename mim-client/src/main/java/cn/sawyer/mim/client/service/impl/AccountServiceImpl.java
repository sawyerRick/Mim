package cn.sawyer.mim.client.service.impl;

import cn.sawyer.mim.client.cache.ClientCache;
import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.tool.model.UserInfo;
import cn.sawyer.mim.tool.enums.Code;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import cn.sawyer.mim.tool.protocol.req.LoginReq;
import cn.sawyer.mim.tool.protocol.req.PubReq;
import cn.sawyer.mim.tool.result.Result;
import cn.sawyer.mim.client.service.AccountService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-24 13:32
 **/
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    OkHttpClient okHttpClient;

    @Autowired
    MimClientConfig appConfig;

    @Override
    public Code login(long userId, String username) {
        UserInfo userInfo = new UserInfo(appConfig.getUserId(), appConfig.getUsername());
        String json = JSONObject.toJSONString(userInfo);
        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);

        Request loginReq = new Request.Builder()
                .url(appConfig.getUrlLogin())
                .post(body)
                .build();

        try {
            Response loginResp = okHttpClient.newCall(loginReq).execute();

            if (loginResp.isSuccessful()) {
                Result loginResult = JSON.parseObject(loginResp.body().string(), Result.class);
                JSONObject data = JSON.parseObject(loginResult.getData().toString());
                String server = data.get("server").toString();
                ClientCache.server = server;
                System.out.println(loginResult);
                if (loginResult.getCode().equals(Code.SUCCESS.code)) {
                    return Code.SUCCESS;
                }
            }
        } catch (Exception e) {
            logger.error("登录失败,请重试");
            e.printStackTrace();
            logger.debug(e.getMessage());
        }

        return Code.LOGIN_ERROR;
    }


    // 手动发送
    @Override
    public Code sendMsg(MimProtocol protocol) {

        if (ClientCache.SvSocketHolder != null && ClientCache.server != null) {
            try {
                ClientCache.SvSocketHolder.writeAndFlush(protocol);
                System.out.println("发送成功..." + protocol);
            } catch (Exception e) {
                System.out.println("发送错误，");
                e.printStackTrace();
            }
        }

        return Code.SUCCESS;
    }

    // 群发
    @Override
    public void sendPublicMsg(PubReq pubReq) {
        try {
            String msgString = JSONObject.toJSONString(pubReq);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), msgString);
            String url = "http://" + appConfig.getRouterHost() + ":" + appConfig.getRouterPort() + "/pubMsg";
            Request loginReq = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response resp = okHttpClient.newCall(loginReq).execute();

            if (resp.isSuccessful()) {
                logger.debug("发送成功:" + pubReq);
                logger.debug(resp.body().string());
            } else {
                logger.error("发送失败" + msgString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logout(Long userId, String username) {
        LoginReq logoutReq = new LoginReq();
        logoutReq.setUserId(userId);
        logoutReq.setUsername(username);
        try {
            String msgString = JSONObject.toJSONString(logoutReq);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), msgString);
            String url = "http://" + appConfig.getRouterHost() + ":" + appConfig.getRouterPort() + "/logout";
            Request loginReq = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response resp = okHttpClient.newCall(loginReq).execute();

            if (resp.isSuccessful()) {
                logger.debug("发送成功:" + logoutReq);
                logger.debug(resp.body().string());
            } else {
                logger.error("发送失败" + msgString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
