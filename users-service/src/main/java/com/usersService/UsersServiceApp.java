package com.usersService;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UsersServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(UsersServiceApp.class,args);
    }
}
