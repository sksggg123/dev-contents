package com.goquality.devcontents.web;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

	@Autowired
	@Qualifier("getJavaMailSenderGmail")
	JavaMailSender gmailSender;
	
	@Autowired
	@Qualifier("getJavaMailSenderNaver")
	JavaMailSender naverSender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	
	
	/**
	 * 메일 템플릿 프로세서
	 * @param mail
	 * @return
	 */
	public String mailBuild(MailModel mail)	{
		Context context = new Context();
		context.setVariable("testMessage", mail.getContents());
		context.setVariable("toAddress", mail.getTo());
		context.setVariable("subject", mail.getSubject());
		context.setVariable("fileName", mail.getFileName());
		//context.setVariable("memName", member.getMemName());
		
		return templateEngine.process("mailTemplate", context);
	}
	
	/**
	 * Gmail로 보내기
	 * @param mailModel
	 */
	public void gmailSender(MailModel mail) throws MessagingException	{
	
		//ResultModel result = new ResultModel(CommonConstants.ERR_NULL_PARAMS);
		
		//List<InternetAddress> toList = mail.getToList(); // 받는사람 여러명일때 (미완성)
		InternetAddress to = mail.getTo();
		String subject = mail.getSubject();
		String contents = mail.getContents();
		InternetAddress cc = mail.getCc();
		//InternetAddress bcc = mail.getBcc();
		String fileName = mail.getFileName(); // 첨부파일명
		String fpath = "C:\\Users\\한정수\\everythingelse\\Desktop\\"; // 관리자가 메일로 보낼 첨부파일 모아두는 경로 지정 -> 관리자가 여러 명일 경우 경로가 다른 문제..
		
		
		if(to != null && !subject.equals("") && !contents.equals("")) {
			
			
			MimeMessage message = gmailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			try {
				helper.setFrom(new InternetAddress("rhep0820@gmail.com"));
				helper.setTo(to);
				helper.setSubject(subject);
				if(cc != null) { helper.setCc(cc); }
				//if(bcc != null)	{ helper.setBcc(bcc); }
				//helper.setText(contents);	// mail폼에서 작성된 메세지만 보낼 때
				//helper.setText(htmlMsg, true); //htmlMsg 직접 구성해서 메세지 보낼 때
				
				// Thymeleaf 템플릿 빌드
				String content = mailBuild(mail);  
				helper.setText(content, true);
				
				// inline 파일 삽입 (로고)
				FileSystemResource headerImg = new FileSystemResource(new File(fpath+"unnamed.png"));
				helper.addInline("headerImg", headerImg);
				
				// 첨부파일 추가
				if(fileName !=null) {
					//ClassPathResource file = new ClassPathResource(path);
					FileSystemResource file = new FileSystemResource(new File(fpath+fileName)); //첨부파일 모아두는 경로(fpath)+파일명(fileName)
					helper.addAttachment(fileName, file);
					//helper.addInline(fileName, file); // 파일 id를 파일명으로 
					
				}
				
			}catch (MessagingException e) {
				e.printStackTrace();
				//return result;
			}
			
			gmailSender.send(message);
			//result.setResult(CommonConstants.OK);	
		}
		
		//return result;
	}

	
	/**
	 * Naver로 보내기
	 * @param mail
	 */
//	public ResultModel naverSender(MailModel mail)	{
//		
//		ResultModel result = new ResultModel(CommonConstants.ERR_NULL_PARAMS);
//		
//		InternetAddress to = mail.getTo();
//		String subject = mail.getSubject();
//		String contents = mail.getContents();
//		InternetAddress cc = mail.getCc();
//		InternetAddress bcc = mail.getBcc();
//		
//		if(to != null && !subject.equals("") && !contents.equals("")) {
//			
//			MimeMessage message = naverSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(message);
//
//			try {
//				helper.setFrom(new InternetAddress("메일주소@naver.com"));
//				helper.setTo(to);
//				helper.setSubject(subject);
//				helper.setText(contents);
//				if(cc != null)
//					helper.setCc(cc);
//				if(bcc != null)
//					helper.setBcc(bcc);
//				
//			}catch (MessagingException e) {
//				e.printStackTrace();
//				return result;
//			}
//			
//			naverSender.send(message);
//			result.setResult(CommonConstants.OK);	
//		}
//		
//		return result;
//	}


	
}
