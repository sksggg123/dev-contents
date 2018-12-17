package com.goquality.devcontents;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevContentsApplication {
	
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/home/ec2-user/app/git/config/goquality/real-application.yml";
	
	public static void main(String[] args) {
//		SpringApplication.run(DevContentsApplication.class, args);
		new SpringApplicationBuilder(DevContentsApplication.class).properties(APPLICATION_LOCATIONS).run(args);
		
	}
}
