package com.moesounds.exception;

/**
 * Runtime exception to be thrown whenever the state token on oAuth implementations comes back
 * different from the original state token saved to the session. Meaning someone be hacking up in
 * here.
 * 
 * @author NYPD
 *
 */
public class InvalidStateTokenException extends RuntimeException {

    private static final long serialVersionUID = -2579101508329721581L;

    public InvalidStateTokenException() {
        super();
    }

}
