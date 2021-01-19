package com.baidu.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RunBasicsEurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(RunBasicsEurekaServer.class);
    }
}
