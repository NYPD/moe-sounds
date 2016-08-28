package com.moesounds.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.domain.Media;
import com.moesounds.domain.Page;
import com.moesounds.domain.enums.MediaType;
import com.moesounds.service.MoeSoundsService;

@DefaultController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private MoeSoundsService moeSoundsService;
	
	@RequestMapping(value = "login")
	public ModelAndView getHomePage() {
		
		ModelAndView mav = new ModelAndView("admin");
		
//		Collection<Page> allPages = moeSoundsService.getAllPages();
//		mav.addObject("allPages", allPages);
//		
		Page randomPage = moeSoundsService.getRandomPage();
		if(randomPage == null) return mav;
		
		
		Media backgroundInner = randomPage.getMediaWithMediaType(MediaType.BACKGROUND_INNER);
		Media carousalBig = randomPage.getMediaWithMediaType(MediaType.CAROUSEL_IMAGE_BIG);
		Media carousalSmall = randomPage.getMediaWithMediaType(MediaType.CAROUSEL_IMAGE_SMALL);
		Media pageBackGround = randomPage.getMediaWithMediaType(MediaType.PAGE_BACKGROUND);
		Media soundFile = randomPage.getMediaWithMediaType(MediaType.SOUND_FILE);
		
		mav.addObject("page", randomPage);
		mav.addObject("backgroundInner", backgroundInner);
		mav.addObject("carousalBig", carousalBig);
		mav.addObject("carousalSmall", carousalSmall);
		mav.addObject("pageBackGround", pageBackGround);
		mav.addObject("soundFile", soundFile);
		
		return mav;
	}
}
