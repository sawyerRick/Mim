//package cn.sawyer;
//
//import cn.sawyer.mim.tool.model.ServerInfo;
//import cn.sawyer.mim.tool.enums.Code;
//import cn.sawyer.mim.tool.result.Result;
//import com.alibaba.fastjson.JSON;
//import okhttp3.*;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.concurrent.TimeUnit;
//
///**
// * @program: mim
// * @description:
// * @author: sawyer
// * @create: 2020-02-24 13:50
// **/
//public class t {
//
//    @Test
//    public void tets() throws IOException {
//
//
//        RequestBody body = RequestBody.create(MediaType.parse("application/json"), "haha");
//
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(10, TimeUnit.SECONDS)
//                .writeTimeout(10,TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true);
//
//        OkHttpClient okHttpClient = builder.build();
//
//        String loginUrl = "http://localhost:8082/testlogin";
//        String getAvaServerUrl = "http://localhost:8082/testGetServer";
//
//
//        Request loginReq = new Request.Builder()
//                .url(loginUrl)
//                .post(body)
//                .build();
//
//        Response loginResp = okHttpClient.newCall(loginReq).execute();
//        if (loginResp.isSuccessful()) {
//            Result loginResult = JSON.parseObject(loginResp.body().string(), Result.class);
//            System.out.println(loginResult);
//            // 获取服务器
//            if (loginResult.getCode().equals(Code.SUCCESS.code)) {
//
//                Request getServerReq = new Request.Builder()
//                        .url(getAvaServerUrl)
//                        .post(body)
//                        .build();
//                Response getServerResp = okHttpClient.newCall(getServerReq).execute();
//                String string = getServerResp.body().string();
//                System.out.println(string);
//                Result result = JSON.parseObject(string, Result.class);
//                ServerInfo serverInfo = JSON.parseObject(result.getData().toString(), ServerInfo.class);
//                System.out.println("[+] Server Info!" + serverInfo);
//            } else {
//                System.out.println("登录失败");
//            }
//        }
//    }
//}
