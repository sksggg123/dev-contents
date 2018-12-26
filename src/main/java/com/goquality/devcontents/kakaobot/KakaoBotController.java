package com.goquality.devcontents.kakaobot;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goquality.devcontents.database.dto.links.LinksSaveRequestDTO;
import com.goquality.devcontents.database.service.LinksService;
import com.goquality.devcontents.yml.service.CategoryService;
import com.goquality.devcontents.yml.vo.CategoryVO;

@Controller
public class KakaoBotController {

	
	@Autowired
	private LinksService linkService;
	
	@Autowired
	private CategoryService category;
	
	/**
	 * 카카오 봇을 통한 DB CRUD
	 * @throws IOException 
	 */
	@RequestMapping(value = "/dbTest", method = RequestMethod.GET)
	public String dbTest(final Model model) throws IOException
	{
		model.addAttribute("link", linkService.findAllDesc());
//		CategoryVO categories = category.getCategoryList();
//		model.addAttribute("categories", category.getYml());
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