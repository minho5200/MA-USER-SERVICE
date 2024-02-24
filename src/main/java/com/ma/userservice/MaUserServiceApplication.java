package com.ma.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MaUserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MaUserServiceApplication.class, args);
    }

}
