package com.goquality.devcontents.web;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MailController {

	
//	@Autowired
//	private JavaMailSender sender;
	
	@Resource
	private MailService  mailService;
	
	
	/**
	 * 메일 작성 폼
	 * @return
	 */
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public String mailForm()  {
		
		return "mailForm";
	}

	
	/**
	 * Gmail로 보내기
	 * @param mail
	 * @return
	 * @throws MessagingException
	 */
	@ResponseBody
	@PostMapping("/sendMailGmail")
	public String sendMailGmail(@ModelAttribute("mail") MailModel mail) throws MessagingException {
		
		mailService.gmailSender(mail);
		
		return "메일 전송 성공!";
	}
	
	
//	/**
//	 * Gmail로 보내기
//	 * @param mail
//	 * @return
//	 * @throws MessagingException 
//	 */
//	@ResponseBody
//	@PostMapping("/sendMailGmail")
//	public ModelMap sendMailGmail(@ModelAttribute("mail") MailModel mail) throws MessagingException {
//		
//		ModelMap map = new ModelMap();
//			
//		ResultModel	result = mailService.gmailSender(mail);
//		map.addAttribute("result", result);
//		
//		return map;
//	}
//	
//	
//	/**
//	 * Naver로 보내기
//	 * @param mail
//	 * @return
//	 */
//	@ResponseBody
//	@PostMapping("/sendMailNaver")
//	public ModelMap sendMailNaver(@ModelAttribute("mailModel") MailModel mail) {
//		
//		ModelMap map = new ModelMap();
//		
//		ResultModel	result = mailService.naverSender(mail);
//		map.addAttribute("result", result);
//		
//		return map;
//	}

	
	
	
/*	@RequestMapping("/sendMailAtt")
	public String sendMailAttachment() throws MessagingException {
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true); // 멀티파트 메세지를 날릴 땐 true flag 필요
		
		try {
			helper.setTo("ryanhan@cloudcash.kr");
			helper.setText("첨부 파일이 있는 메일 내용 (테스트)");
			helper.setSubject("스프링 부트로 보낸 메일!");
			
			ClassPathResource file = new ClassPathResource("puppy.jpg");
			
			//FileSystemResource file = new FileSystemResource(new File("puppy.jpg"));
			helper.addAttachment("puppy.jpg", file);
			
		} catch (MessagingException e) {
			e.printStackTrace();
			return "Error while sending mail..";
		}
		
		sender.send(message);
		return "첨부 메일 전송 성공!";
		
	}*/
}
