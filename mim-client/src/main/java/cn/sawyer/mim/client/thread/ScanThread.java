package cn.sawyer.mim.client.thread;

import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.client.service.AccountService;
import cn.sawyer.mim.client.util.BeanContext;
import cn.sawyer.mim.tool.protocol.req.PubReq;

import java.util.Scanner;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 19:41
 **/

public class ScanThread implements Runnable{

    private AccountService service;

    private MimClientConfig appConfig;

    public ScanThread() {
        service = BeanContext.getBean(AccountService.class);
        appConfig = BeanContext.getBean(MimClientConfig.class);
    }

    @Override
    public void run() {

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println(">");
            String msg = scanner.nextLine();

            PubReq pubReq = new PubReq();
            pubReq.setSrcId(appConfig.getUserId());
            pubReq.setSrcName(appConfig.getUsername());
            pubReq.setMsg(msg);

//            service.sendMsg(mimMessage);
            service.sendPublicMsg(pubReq);
        }
    }
}
