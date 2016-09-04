package com.moesounds.service;

import javax.servlet.http.HttpServletRequest;

import com.moesounds.domain.User;

public interface ApiLoginService {

    /**
     * Creates the authentication request String URL needed to send to the API servers.
     * 
     * @return The authentication request String URL
     */
    public String getAuthenticationRequestUrl();

    /**
     * Verifies the response from the API server and retrieves the token needed to make API calls.
     * 
     * After retrieving the token it stores any necessary information needed to make future API
     * calls
     * 
     * @param request - The HttpServletRequest from the API server
     */
    public void verifyAuthenticationResponse(HttpServletRequest request);

    /**
     * Return the corresponding Moe Sounds user using the unique API's user id. If no user is found
     * null is returned.
     * 
     * If available, sets the URL API profile picture for the user
     * 
     * @return Moe Sounds User
     */
    public User getMoeSoundsUser();
}
