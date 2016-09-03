package com.moesounds.beans;

public class MoeSoundsSessionBean {

    public String googleStateToken;

    public String getGoogleStateToken() {

        return googleStateToken;
    }

    public void setGoogleStateToken(String googleStateToken) {

        this.googleStateToken = googleStateToken;
    }

    @Override
    public String toString() {

        return "MoeSoundsSessionBean [googleStateToken=" + googleStateToken + "]";
    }

}
