package com.fantasybaby.mybatis;

import com.fantasybaby.mybatis.websocket.NettyServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.Resource;

/**
 * the websocket server
 * @author fantasybaby
 */
@SpringBootApplication
@ComponentScan("com.fantasybaby")
public class WebsocketDemoApplication implements CommandLineRunner {
    @Resource
    private NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(WebsocketDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.start();
    }
}
