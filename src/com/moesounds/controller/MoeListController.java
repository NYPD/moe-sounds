package com.moesounds.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.domain.Page;
import com.moesounds.service.MoeSoundsService;

@DefaultController
public class MoeListController {

    @Autowired
    private MoeSoundsService moeSoundsService;

    @RequestMapping(value = {"/all", "/moe-list"})
    public ModelAndView getSpecificPage() {

        Collection<Page> allPages = moeSoundsService.getAllPages();

        ModelAndView mav = new ModelAndView("moe-list");

        mav.addObject("allPages", allPages);

        return mav;
    }

}
