package com.goquality.devcontents.kakaobot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goquality.devcontents.database.dto.links.LinksSaveRequestDTO;
import com.goquality.devcontents.database.service.LinksService;

@Controller
public class KakaoBotController {

	
	@Autowired
	private LinksService linksService;
	
	/**
	 * 카카오 봇을 통한 DB CRUD
	 */
	@RequestMapping(value = "/dbTest", method = RequestMethod.GET)
	public String dbTest()
	{
		return "dbTestForm";
	}
	
	@ResponseBody
	@PostMapping("/dbTestProc")
	public String saveLinks(@ModelAttribute("LinksSaveRequestDTO") LinksSaveRequestDTO dto){
        linksService.save(dto);
        
        return linksService.findAllDesc().toString();
    }
}