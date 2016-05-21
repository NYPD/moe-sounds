package com.moesounds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moesounds.model.PageForm;
import com.moesounds.service.MoeSoundsService;

@RestController
@RequestMapping(value = "/admin")
public class AdminRestController {

	@Autowired
	private MoeSoundsService moeSoundsService;
	
	@RequestMapping(value = "save-page-form", method = RequestMethod.POST)
	public int savePageForm(PageForm pageForm) {  
		
		moeSoundsService.savePageForm(pageForm);
		
		Integer pageId = pageForm.getPageId();
		return pageId;
		
	}
	
	@RequestMapping(value = "delete-page", method = RequestMethod.POST)
	public void deletePage(@RequestParam("pageId") int pageId) {  
		moeSoundsService.deletePage(pageId);
	}
	
}
