package com.moesounds.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.exception.FileReadException;
import com.moesounds.model.AjaxError;

@RestControllerAdvice(annotations = RestController.class)
public class RestControllerAdvisor {

    private final Logger logger = LoggerFactory.getLogger(RestControllerAdvisor.class);

    @ExceptionHandler(value = FileReadException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleFileReadException(FileReadException exception) {

        logger.error(exception.getErrorMessage(), exception);

        ModelAndView modelAndView = new ModelAndView("/modal-content/error/ajax-error");

        String modalTitle = "File Read Error";
        String modalMessage = "Error occured when attempting to read a file, please try again later or submit a report";

        AjaxError ajaxError = new AjaxError(modalTitle, modalMessage);

        modelAndView.addObject("ajaxError", ajaxError);

        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleGeneralException(Exception exception) {

        logger.error("Something goofed up somewhere", exception);

        ModelAndView modelAndView = new ModelAndView("/modal-content/error/ajax-error");

        String modalTitle = "RIP";
        String modalMessage = "Something went wrong back here, please try again later. If this keeps happening submit a report";

        AjaxError ajaxError = new AjaxError(modalTitle, modalMessage);

        modelAndView.addObject("ajaxError", ajaxError);

        return modelAndView;
    }

}
