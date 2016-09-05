package com.moesounds.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;

@DefaultController
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "access-denied")
    public ModelAndView getAccessDeniedPage() {
        return new ModelAndView("error/access-denied");
    }
}
