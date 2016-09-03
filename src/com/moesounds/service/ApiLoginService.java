package com.moesounds.service;

import javax.servlet.http.HttpServletRequest;

public interface ApiLoginService {

    /**
     * Creates the authentication request String URL needed to send to the API servers.
     * 
     * If a checked exception occurs while creating the String URL, it should be caught and thrown
     * as a AuthRequestUrlBuilderException.
     * 
     * @return The authentication request String URL
     */
    public String getAuthenticationRequestUrl();

    public void verifyAuthenticationResponse(HttpServletRequest request);
}
