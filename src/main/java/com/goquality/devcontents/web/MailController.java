package com.goquality.devcontents.web;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
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
	
//	private Environment env;
//	
//	@GetMapping("/profile")
//	public String getProfiles() {
//		return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
//	}

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
	
}