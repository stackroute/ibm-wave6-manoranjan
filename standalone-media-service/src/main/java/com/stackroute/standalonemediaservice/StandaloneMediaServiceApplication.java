package com.stackroute.standalonemediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class StandaloneMediaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StandaloneMediaServiceApplication.class, args);
	}

}
