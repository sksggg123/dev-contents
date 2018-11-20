package com.goquality.devcontents.web;

import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import lombok.Data;

@Data
public class MailModel {
	private String subject;
	private String contents;
	private InternetAddress to;
	private List<InternetAddress> toList; //ryan
	private String toAddr;
	private InternetAddress cc;
	private List<InternetAddress> ccList;
	private InternetAddress from;
	private InternetAddress bcc;
	private String contentType = "text/html";
	private String template;
	//본문 첨부용 이미지 (system file path name, img element id)
	private List<Map<String, String>> imageList;
	//첨부파일 (system file path name, download filename)
	private List<Map<String, String>> attachList;
//	private Map<String, Object> model;
//	private VelocityContext velocityContext;
	
	private String fileName;
}
