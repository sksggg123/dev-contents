package com.goquality.devcontents.yml.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goquality.devcontents.yml.vo.CategoryVO;

@Service
public class CategoryService {
	
	@Autowired private ResourceLoader resourceLoader;
	
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
	
	
	public String getYml() throws IOException {
		String path = "/home/ec2-user/app/git/parsing-storage/category.json";
		
		Yaml yml = new Yaml();
		
		HashMap<String, Object> hm = new HashMap<String, Object>();
		HashMap<String, String> sm = new HashMap<String, String>();
		
		hm = yml.load(new FileInputStream(new File(resourceLoader.getResource(path).getURI().getPath())));
		sm = (HashMap<String, String>) hm.get("categoryName");
		
		return sm.get("categoryName");
		
		
	}
}
