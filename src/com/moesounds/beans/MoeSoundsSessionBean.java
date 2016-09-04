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

}
