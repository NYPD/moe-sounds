package com.moesounds.exception;

import javax.servlet.http.HttpServletRequest;

/**
 * Custom {@link RuntimeException} where a non-admin user attempted to login into an admin page
 * 
 * @author NYPD
 *
 */
public class UnauthorizedUserException extends RuntimeException {

    private static final long serialVersionUID = 1987666346814209997L;
    private String ipAddress;

    public UnauthorizedUserException(HttpServletRequest request) {
        super();

        String forwardedForHeader = request.getHeader("X-FORWARDED-FOR");
        this.ipAddress = forwardedForHeader != null ? forwardedForHeader : request.getRemoteAddr();
    }

    public String getIpAddress() {
        return ipAddress;
    }

}
