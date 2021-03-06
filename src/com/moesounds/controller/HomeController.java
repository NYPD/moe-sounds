package com.moesounds.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.domain.Page;
import com.moesounds.domain.enums.DefaultBackground;
import com.moesounds.service.MoeSoundsService;

@DefaultController
public class HomeController {

    @Autowired
    private MoeSoundsService moeSoundsService;

    @RequestMapping(value = {"/", "/random", "/page/{pageId}"})
    public ModelAndView getHomePage(@PathVariable(value = "pageId", required = false) Integer pageId) {

        ModelAndView mav = new ModelAndView("home");

        Page page = pageId == null ? moeSoundsService.getRandomPage() : moeSoundsService.getSpecificPage(pageId);;

        mav.addObject("page", page);
        mav.addObject("randomBackground", DefaultBackground.randomBackground());

        return mav;
    }

}
