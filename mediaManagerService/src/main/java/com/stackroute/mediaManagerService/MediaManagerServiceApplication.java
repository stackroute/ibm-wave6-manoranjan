package com.stackroute.mediaManagerService;

import com.stackroute.mediaManagerService.service.MediaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class MediaManagerServiceApplication implements CommandLineRunner{

//	@Resource
//	MediaService mediaService;

	public static void main(String[] args) {
		SpringApplication.run(MediaManagerServiceApplication.class, args);
	}

	@Override
	public void run(String... arg) {
//		mediaService.deleteAll();
//		mediaService.init();
	}
}
