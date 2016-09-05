package com.moesounds.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;

@ControllerAdvice(annotations = DefaultController.class)
public class ControllerAdvisor {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleGeneralException(Exception exception) {
        return new ModelAndView("error/general-error");
    }

}
