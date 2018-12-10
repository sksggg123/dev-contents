package com.goquality.devcontents.web;

import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goquality.devcontents.database.dto.links.LinksMainResponseDTO;
import com.goquality.devcontents.database.dto.links.LinksSaveRequestDTO;
import com.goquality.devcontents.database.service.LinksService;

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
	
}