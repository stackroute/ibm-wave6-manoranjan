package com.stackroute.episodicmediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EpisodicMediaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpisodicMediaServiceApplication.class, args);
	}

}
