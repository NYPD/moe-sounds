package com.moesounds.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;

@DefaultController
public class HomeController {

	@RequestMapping(value = {"/home", "/"})
	public ModelAndView getHomePage(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("home");
		return mav;

	}
}
