package cn.sawyer.mim.router.service.impl;

import cn.sawyer.mim.router.config.MimRouterConfig;
import cn.sawyer.mim.router.service.ServerService;
import cn.sawyer.mim.tool.model.MimMessage;
import cn.sawyer.mim.tool.model.ServerInfo;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: mim
 * @description: 轮询算法
 * @author: sawyer
 * @create: 2020-02-23 20:17
 **/

@Service
public class ServerServiceImpl implements ServerService {

    @Autowired
    MimRouterConfig routerConfig;

    @Autowired
    OkHttpClient okHttpClient;

    private AtomicInteger atom = new AtomicInteger(0);

    @Override
    public String select(List<String> list) {
        if (list.size() == 0) {
            throw new RuntimeException("无可用服务器");
        }

        int offset = atom.getAndIncrement() % list.size();

        return list.get(offset);
    }

    @Override
    public void distribute(MimMessage msg, ServerInfo serverInfo) {
        try {
            String msgString = JSONObject.toJSONString(msg);
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), msgString);
            String url = "http://" + serverInfo.getHost() + ":" + serverInfo.getHttpPort() + "/distribute";
            Request loginReq = new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();

            Response disResp = okHttpClient.newCall(loginReq).execute();

            if (disResp.isSuccessful()) {
                System.out.println("发送成功:" + msgString + " distribute to" + serverInfo);
                System.out.println(disResp.body().string());
            } else {
                System.out.println("发送失败" + msgString + " distribute to" + serverInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
