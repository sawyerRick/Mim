package cn.sawyer.mim.client.service.impl;

import cn.sawyer.mim.client.service.LocalService;
import org.springframework.stereotype.Service;

/**
 * @program: mim
 * @description:
 * @author: sawyer
 * @create: 2020-02-22 18:13
 **/

@Service
public class LocalServiceImpl implements LocalService {

    @Override
    public void printNormal(String msg) {
        System.out.println(msg);
    }

    @Override
    public void printSystem(String msg) {
        System.out.println(msg);
    }
}
