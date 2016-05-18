package com.moesounds.controller;

import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.dao.AdminDAO;
import com.moesounds.dao.MoeSoundsDAO;
import com.moesounds.domain.PageMedia;
import com.moesounds.domain.User;

@DefaultController
public class HomeController {

	@Autowired
	private AdminDAO userDAO;
	@Autowired
	private MoeSoundsDAO moeSoundsDAO;
	
	@RequestMapping(value = {"/home", "/"})
	public ModelAndView getHomePage() {
		
		List<User> allUsers = userDAO.getAllUsers();
		
		PageMedia lastPageMedia = moeSoundsDAO.getLastPageMedia();
		
		byte[] carouselImageSmall = lastPageMedia.getCarouselImageSmall();
		
		
		String encodeBase64String = Base64.encodeBase64String(carouselImageSmall);
		
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("allUsers", allUsers);
		mav.addObject("encodeBase64String", encodeBase64String);
		
		return mav;
	}
}
