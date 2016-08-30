package com.moesounds.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.domain.Page;
import com.moesounds.service.MoeSoundsService;

@DefaultController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private MoeSoundsService moeSoundsService;
	
	@RequestMapping(value = "login")
	public ModelAndView getHomePage() {
		
		ModelAndView mav = new ModelAndView("admin");
		
		Page randomPage = moeSoundsService.getRandomPage();
		
		mav.addObject("page", randomPage);
		
		return mav;
	}
}
