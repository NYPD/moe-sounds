package com.moesounds.beans;

import com.moesounds.domain.User;

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
