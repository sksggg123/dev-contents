package com.goquality.devcontents.kakaobot;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goquality.devcontents.database.dto.links.LinksSaveRequestDTO;
import com.goquality.devcontents.database.service.LinksService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class KakaoBotController {

	
	@Autowired
	private LinksService linkService;
	
	private Environment env;
	
	@GetMapping("/profile")
	public String getProfiles() {
		return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
	}

	/**
	 * 카카오 봇을 통한 DB CRUD
	 */
	@RequestMapping(value = "/dbTest", method = RequestMethod.GET)
	public String dbTest(final Model model)
	{
		model.addAttribute("link", linkService.findAllDesc());
		return "dbTestForm";
	}
	
//	@ResponseBody
//	@PostMapping("/dbTestProc")
	@RequestMapping(value = "/dbTestProc", method = RequestMethod.POST)
	public String saveLinks(@ModelAttribute("LinksSaveRequestDTO") LinksSaveRequestDTO dto, Model model){
		linkService.save(dto);
		
		model.addAttribute("link", linkService.findAllDesc());
		
        return "dbTestListForm";
    }
	
}