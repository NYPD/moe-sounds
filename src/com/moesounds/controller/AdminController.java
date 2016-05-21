package com.moesounds.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.domain.Page;
import com.moesounds.service.MoeSoundsService;

@DefaultController
public class AdminController {

	@Autowired
	private MoeSoundsService moeSoundsService;
	
	@RequestMapping(value = "/admin")
	public ModelAndView getHomePage() {
		
		ModelAndView mav = new ModelAndView("admin");
		
		Collection<Page> allPages = moeSoundsService.getAllPages();
		mav.addObject("allPages", allPages);
		
		return mav;
	}
}
