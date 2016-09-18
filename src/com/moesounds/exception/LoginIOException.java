package com.moesounds.exception;

import com.moesounds.controller.ControllerAdvisor;

/**
 * Generic Logic exception to be thrown whenever an IO exception occurs when something for some
 * reason goes wrong with redirecting and sending Internet requests.
 * 
 * Used to redirect all these type of issues to our non-ajax {@link ControllerAdvisor}
 * 
 * @author NYPD
 */
public class LoginIOException extends RuntimeException {

    private static final long serialVersionUID = 2795530368386310074L;

    public LoginIOException() {
        super();
    }

}
