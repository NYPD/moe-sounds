package com.moesounds.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.exception.InvalidStateTokenException;
import com.moesounds.exception.LoginIOException;
import com.moesounds.exception.UnauthorizedUserException;
import com.moesounds.exception.google.AuthorizationCodeResponseException;
import com.moesounds.util.AppConstants;

/**
 * Controller advisor for all the non-ajax requests in the Moe Sounds application
 * 
 * @author NYPD
 *
 */
@ControllerAdvice(annotations = DefaultController.class)
public class ControllerAdvisor {

    private final Logger logger = LoggerFactory.getLogger(ControllerAdvisor.class);

    @ExceptionHandler(value = LoginIOException.class)
    public ModelAndView handleLoginIOException(LoginIOException exception) {

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginErrorMessage", "The Internet does not seem to work anymore, try again at a later time.");

        return modelAndView;
    }

    @ExceptionHandler(value = AuthorizationCodeResponseException.class)
    public ModelAndView handleAuthorizationCodeResponseExcpetion(AuthorizationCodeResponseException exception) {

        String error = exception.getError();
        String errorDescription = exception.getErrorDescription();
        String errorUri = exception.getErrorUri();

        logger.error("Error authorizing the Google API response. \n" +
                "Error code: " + error + "\n" +
                "Description: " + errorDescription + "\n" +
                "For more information visit: " + errorUri);

        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginErrorMessage", "Can't login you in if you don't grant "
                + AppConstants.APPLICATION_NAME
                + " minimal permissions bro. Unless something horrible went wrong try again later.");

        return modelAndView;
    }

    @ExceptionHandler(value = InvalidStateTokenException.class)
    public ModelAndView handleInvalidStateTokenException(InvalidStateTokenException exception) {

        logger.error("State tokens did not match for attempted user sign on");

        ModelAndView modelAndView = new ModelAndView("error/access-denied");
        return modelAndView;
    }
    
    @ExceptionHandler(value = UnauthorizedUserException.class)
    public ModelAndView handleUnauthorizedUserException(UnauthorizedUserException exception) {

        logger.info("Unauthorized user attempted to login as an admin from > " + exception.getIpAddress());

        ModelAndView modelAndView = new ModelAndView("error/access-denied");
        return modelAndView;
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleGeneralException(Exception exception) {

        logger.error("Something messed up", exception);

        return new ModelAndView("error/general-error");
    }

}
