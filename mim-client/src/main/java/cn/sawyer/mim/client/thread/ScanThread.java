package cn.sawyer.mim.client.thread;

import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.client.service.AccountService;
import cn.sawyer.mim.client.util.BeanContext;
import cn.sawyer.mim.tool.result.MsgType;
import cn.sawyer.mim.tool.model.MimMessage;

import java.util.Scanner;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 19:41
 **/

public class ScanThread implements Runnable{

    private AccountService service;

    private MimClientConfig mimClientConfig;

    public ScanThread() {
        service = BeanContext.getBean(AccountService.class);
        mimClientConfig = BeanContext.getBean(MimClientConfig.class);
    }

    @Override
    public void run() {

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println(">");
            String msg = scanner.nextLine();

            MimMessage mimMessage = new MimMessage();
            mimMessage.setUserId(mimClientConfig.getUserId());
            mimMessage.setUsername(mimClientConfig.getUsername());
            mimMessage.setMsg(msg);
            mimMessage.setType(MsgType.MSG_REQ.value());

//            service.sendMsg(mimMessage);
            service.sendPublicMsg(mimMessage);
        }
    }
}
