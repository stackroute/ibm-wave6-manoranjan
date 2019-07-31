package com.stackroute.mediaManagerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MediaManagerServiceApplication{

    public static void main(String[] args) {
        SpringApplication.run(MediaManagerServiceApplication.class, args);
    }

}
