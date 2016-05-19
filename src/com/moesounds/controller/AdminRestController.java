package com.moesounds.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.moesounds.model.PageForm;
import com.moesounds.service.MoeSoundsService;

@RestController
public class AdminRestController {

	@Autowired
	private MoeSoundsService moeSoundsService;
	
	@RequestMapping(value = "save-page-form", method = RequestMethod.POST)
	public int savePageForm(PageForm pageForm) throws IOException {  
		
		moeSoundsService.savePageForm(pageForm);
		
		Integer pageId = pageForm.getPageId();
		return pageId;
		
	}
	
	
}
