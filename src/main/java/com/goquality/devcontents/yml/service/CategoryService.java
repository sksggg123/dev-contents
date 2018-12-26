package com.goquality.devcontents.yml.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goquality.devcontents.yml.vo.CategoryVO;

@Service
public class CategoryService {
	
	public CategoryVO getCategoryList() {
			
		CategoryVO catVO = new CategoryVO();
		
		try{
			ObjectMapper mapper = new ObjectMapper();
			catVO = mapper.readValue(new File("/home/ec2-user/app/git/parsing-storage/category.json"), CategoryVO.class);
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	
		return catVO; 
	}

}
