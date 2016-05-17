package com.moesounds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.dao.AdminDAO;
import com.moesounds.domain.User;

@DefaultController
public class HomeController {

	@Autowired
	private AdminDAO userDAO;
	
	@RequestMapping(value = {"/home", "/"})
	public ModelAndView getHomePage() {
		
		List<User> allUsers = userDAO.getAllUsers();
		
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("allUsers", allUsers);
		
		return mav;
	}
}
