package cn.sawyer.mim.client.thread;

import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.client.service.AccountService;
import cn.sawyer.mim.client.service.LocalService;
import cn.sawyer.mim.client.util.BeanContext;
import cn.sawyer.mim.tool.protocol.req.PshReq;

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

    private LocalService localService;

    public ScanThread() {
        service = BeanContext.getBean(AccountService.class);
        localService = BeanContext.getBean(LocalService.class);
        appConfig = BeanContext.getBean(MimClientConfig.class);
    }

    @Override
    public void run() {

        while (true) {

            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();

            PshReq pshReq = new PshReq();
            pshReq.setSrcId(appConfig.getUserId());
            pshReq.setSrcName(appConfig.getUsername());
            pshReq.setMsg(msg);
            localService.commandHandle(pshReq);
//            service.sendMsg(mimMessage);
//            service.sendPublicMsg(pubReq);
        }
    }
}
