package com.moesounds.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.exception.LoginIOException;

/**
 * Controller advisor for all the non-ajax requests in the Moe Sounds application
 * 
 * @author NYPD
 *
 */
@ControllerAdvice(annotations = DefaultController.class)
public class ControllerAdvisor {

    @ExceptionHandler(value = LoginIOException.class)
    public ModelAndView handleLoginIOException(LoginIOException exception) {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginErrorMessage", "The Internet does not seem to work anymore, try again at a later time");

        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleGeneralException(Exception exception) {
        return new ModelAndView("error/general-error");// TODO finish
    }

}
