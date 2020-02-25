package cn.sawyer.mim.client;

import cn.sawyer.mim.client.client.MimClient;
import cn.sawyer.mim.client.config.MimClientConfig;
import cn.sawyer.mim.client.thread.ClientBootThread;
import cn.sawyer.mim.client.thread.ScanThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: mim
 * @description: Client Main
 * @author: sawyer
 * @create: 2020-02-22 19:43
 **/
@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    @Autowired
    MimClient mimClient;

    @Autowired
    MimClientConfig appConfig;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(appConfig.getUsername() + ", " + appConfig.getUserId());
        Thread boot = new Thread(new ClientBootThread());
        Thread scan = new Thread(new ScanThread());
        boot.start();
        scan.start();
    }

}
