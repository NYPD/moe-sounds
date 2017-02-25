package com.moesounds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.domain.Page;
import com.moesounds.service.MoeSoundsService;

@DefaultController
public class HomeController {

    @Autowired
    private MoeSoundsService moeSoundsService;

    @RequestMapping(value = {"/random", "/"})
    public ModelAndView getHomePage() {

        ModelAndView mav = new ModelAndView("home");

        Page randomPage = moeSoundsService.getRandomPage();
        mav.addObject("page", randomPage);

        return mav;
    }

    @RequestMapping(value = {"/page"})
    public ModelAndView getSpecificPage(@RequestParam("pageId") int pageId) {

        ModelAndView mav = new ModelAndView("home");

        Page specificPage = moeSoundsService.getSpecificPage(pageId);
        mav.addObject("page", specificPage);

        return mav;
    }
}
