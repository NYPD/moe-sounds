package com.moesounds.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.moesounds.exception.FileReadException;
import com.moesounds.exception.InvalidStateTokenException;

@RestControllerAdvice(annotations = RestController.class)
public class RestControllerAdvisor {

    @ExceptionHandler(value = FileReadException.class)
    public String handleFileReadException(InvalidStateTokenException exception) {
        return "Error occured when attempting to read the File, please try again later or submit a report";
    }

    @ExceptionHandler(value = Exception.class)
    public String handleGeneralException(Exception exception) {
        return "Something went wrong back here, please try again. If this keeps happening submit a report";
    }

}
