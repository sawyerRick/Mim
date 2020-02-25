package cn.sawyer.mim.server;

import cn.sawyer.mim.server.config.MimServerConfig;
import cn.sawyer.mim.server.server.MimServer;
import cn.sawyer.mim.server.service.ZkRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: mim
 * @description: 启动
 * @author: sawyer
 * @create: 2020-02-21 10:15
 **/

@SpringBootApplication
public class ServerApplication implements CommandLineRunner {

    @Autowired
    ZkRegistry zkRegistry;

    @Autowired
    MimServerConfig mimServerConfig;

    @Autowired
    MimServer server;

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @Override
    public void run(String... args) {
        zkRegistry.registry(mimServerConfig.getHost(), mimServerConfig.getHttpPort(), mimServerConfig.getMsgPort());
        server.start();
    }
}
