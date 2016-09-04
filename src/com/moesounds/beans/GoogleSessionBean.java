package com.moesounds.beans;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

public class GoogleSessionBean {

    private String googleStateToken;
    private GoogleCredential googleCredential;

    public String getGoogleStateToken() {
        return googleStateToken;
    }

    public void setGoogleStateToken(String googleStateToken) {
        this.googleStateToken = googleStateToken;
    }

    public GoogleCredential getGoogleCredential() {
        return googleCredential;
    }

    public void setGoogleCredential(GoogleCredential googleCredential) {
        this.googleCredential = googleCredential;
    }

}
