package com.moesounds.exception.google;

import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;

/**
 * Custom {@link RuntimeException} that occurred on the Google API side when attempting authenticate
 * the API response. This could range from a server issue to the user simply not granting permission
 * to Moe Sounds.
 * 
 * @author NYPD
 *
 */
public class AuthorizationCodeResponseExcpetion extends RuntimeException {

    private static final long serialVersionUID = 8348153829305231702L;

    private String error;
    private String errorDescription;
    private String errorUri;

    public AuthorizationCodeResponseExcpetion(AuthorizationCodeResponseUrl authorizationCodeResponseUrl) {
        super();
        this.error = authorizationCodeResponseUrl.getError();
        this.errorDescription = authorizationCodeResponseUrl.getErrorDescription();
        this.errorUri = authorizationCodeResponseUrl.getErrorUri();
    }

    // Default Accessors *********************************
    public String getError() {
        return error;
    }
    public String getErrorDescription() {
        return errorDescription;
    }
    public String getErrorUri() {
        return errorUri;
    }

    @Override
    public String toString() {
        return "AuthorizationCodeResponseExcpetion [error=" + error + ", errorDescription=" + errorDescription
                + ", errorUri=" + errorUri + "]";
    }

}
