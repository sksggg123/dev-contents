package com.goquality.devcontents;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.apple.eawt.Application;

@EnableJpaAuditing
@SpringBootApplication
public class DevContentsApplication {
	
	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/app/config/goquality/real-application.yml";
	
	public static void main(String[] args) {
//		SpringApplication.run(DevContentsApplication.class, args);
		new SpringApplicationBuilder(Application.class).properties(APPLICATION_LOCATIONS).run(args);
		
	}
}
