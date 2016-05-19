package com.moesounds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.domain.Page;
import com.moesounds.service.MoeSoundsService;

@DefaultController
public class HomeController {

	@Autowired
	private MoeSoundsService moeSoundsService;
	
	@RequestMapping(value = {"/home", "/"})
	public ModelAndView getHomePage() {
		
		ModelAndView mav = new ModelAndView("home");
		
		Page randomPage = moeSoundsService.getRandomPage();
		mav.addObject("randomPage", randomPage);
		
		return mav;
	}
}
