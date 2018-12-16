package com.goquality.devcontents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevContentsApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DevContentsApplication.class, args);
		System.out.println("=======Server Started=======");
	}
}
