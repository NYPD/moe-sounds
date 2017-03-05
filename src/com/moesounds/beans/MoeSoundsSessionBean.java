package com.moesounds.beans;

import com.moesounds.configuration.ApplicationConfiguration;
import com.moesounds.domain.User;

/**
 * Session bean containing all the relevant information needed for the Moe Sounds application and
 * the currently logged in user.
 * 
 * This is set up in {@link ApplicationConfiguration}
 * 
 * @author NYPD
 */
public class MoeSoundsSessionBean {

    private User user;
    private boolean rememberMe;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public boolean isRememberMe() {
        return rememberMe;
    }
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

}
