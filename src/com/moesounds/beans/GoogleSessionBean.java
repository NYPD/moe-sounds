package com.moesounds.beans;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.moesounds.configuration.api.GoogleConfiguration;

/**
 * Session bean containing all the relevant information specific to the Google API and the current
 * user.
 * 
 * This is set up in {@link GoogleConfiguration}
 * 
 * @author NYPD
 */
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
