package com.moesounds.exception;

import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.controller.RestControllerAdvisor;
import com.moesounds.domain.User;

/**
 * Exception to be thrown for ajax calls when the {@link User} object is null in the
 * {@link MoeSoundsSessionBean}. This should then get picked up by the {@link RestControllerAdvisor}
 * which should cause the front-end to redirect the user to the login page.
 * 
 * @author NYPD
 *
 */
public class NoUserInSessionException4Ajax extends RuntimeException {

    private static final long serialVersionUID = -8013378433601634580L;

    public NoUserInSessionException4Ajax() {
        super();
    }

}
