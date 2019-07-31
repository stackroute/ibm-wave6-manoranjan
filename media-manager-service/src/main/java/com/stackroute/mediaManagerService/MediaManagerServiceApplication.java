package com.stackroute.mediaManagerService;

import com.stackroute.mediaManagerService.service.MediaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import javax.annotation.Resource;

@EnableEurekaClient
@SpringBootApplication
public class MediaManagerServiceApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(MediaManagerServiceApplication.class, args);
    }

    @Override
    public void run(String... arg) {
    }
}
