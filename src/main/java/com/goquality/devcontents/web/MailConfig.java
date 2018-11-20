package com.goquality.devcontents.web;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {


	@Bean
	public JavaMailSender getJavaMailSenderGmail() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setProtocol("smtp");
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setUsername(""); // 메일 주소
		mailSender.setPassword(""); // 메일 비번
		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "false");
		
		mailSender.setJavaMailProperties(prop);

		return mailSender;
	}
	
	
	@Bean
	public JavaMailSender getJavaMailSenderNaver() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		mailSender.setHost("smtp.naver.com");
		mailSender.setPort(587);
		mailSender.setProtocol("smtp");
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setUsername("메일주소@naver.com"); // 메일 주소
		mailSender.setPassword("비밀번호"); // 메일 비번
		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		
		mailSender.setJavaMailProperties(prop);

		return mailSender;
	}
}
