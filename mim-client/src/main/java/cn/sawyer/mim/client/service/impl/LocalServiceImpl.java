package cn.sawyer.mim.client.service.impl;

import cn.sawyer.mim.client.service.LocalService;
import cn.sawyer.mim.tool.protocol.MimProtocol;
import org.springframework.stereotype.Service;


/**
 * @program: mim
 * @description: 本地服务
 * @author: sawyer
 * @create: 2020-02-22 18:13
 **/

/**
 * ansi转义
 * 重置：\u001b[0m
 *
 * 黑色：\u001b[30m
 *
 * 红色：\u001b[31m
 *
 * 绿色：\u001b[32m
 *
 * 黄色：\u001b[33m
 *
 * 蓝色：\u001b[34m
 *
 * 洋红：\u001b[35m
 *
 * 青色：\u001b[36m
 *
 * 白特：\u001b[37m
 */

@Service
public class LocalServiceImpl implements LocalService {

    @Override
    public void printNormal(MimProtocol protocol) {
        String line = "\n\u001b[34m" + protocol.getSrcName() + "\u001b[0m:\u001b[36m" + protocol.getMsg() + "\u001b[0m";
        System.out.println(line + "\n");
    }

    @Override
    public void printSystem(String msg) {
        System.out.println(msg);
    }
}
