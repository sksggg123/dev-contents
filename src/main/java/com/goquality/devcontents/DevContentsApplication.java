package com.goquality.devcontents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DevContentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DevContentsApplication.class, args);
		System.out.println("=======Server Started=======");
	}
}
