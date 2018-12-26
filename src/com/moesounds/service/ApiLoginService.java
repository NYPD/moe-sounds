package com.moesounds.service;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import com.moesounds.domain.User;

/**
 * Interface to be implemented by third party API services to handle all the login functionality for the application.
 * 
 * @author NYPD
 */
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
     * After retrieving the token it stores any necessary information needed to make future API calls
     * 
     * @param request - The HttpServletRequest from the API server
     */
    public void verifyAuthenticationResponse(HttpServletRequest request);

    /**
     * Return the corresponding Moe Sounds user using the unique API's user id. If no user is found null is returned.
     * 
     * If available, sets the URL API profile picture for the user
     * 
     * @return Moe Sounds User
     */
    public User getMoeSoundsUser();

    /**
     * Creates API specific cookies to be able to authenticate the user again without having them login again and sets them
     * in the {@link HttpServletResponse}
     * 
     * @param response - The {@link HttpServletResponse} to set cookies into
     */
    public void createUserCookies(HttpServletResponse response);

    /**
     * Should redirect the user to wherever the authentication process begins and try to authenticate the user again
     * seamlessly
     * 
     * @param response
     */
    public void reAuthenticateUser(HttpServletRequest request, HttpServletResponse response);

    /**
     * Helper method provided to determine whether a request was an ajax call or not. Supply an array of all the rest
     * controllers in the application.
     * 
     * <br>
     * <br>
     * Example:
     * 
     * <pre>
     * applicationContext.getBeansWithAnnotation(RestController.class).values().toArray();
     * </pre>
     * 
     * @param request The {@link HttpServletRequest} from the user
     * 
     * @return boolean stating whether the request was an ajax call or not
     */
    public default boolean isHttpServletRequestAjax(Object[] restControllers, HttpServletRequest request) {

        boolean isAjax = false;

        outerloop: for (Object restController : restControllers) {

            RequestMapping classRequestMapping = restController.getClass().getAnnotation(RequestMapping.class);

            Method[] methods = restController.getClass().getMethods();

            for (Method method : methods) {

                RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                if (methodRequestMapping == null) continue;

                String[] mappings = methodRequestMapping.value();

                for (String methodMapping : mappings) {

                    if (classRequestMapping != null) {
                        String[] classMappings = classRequestMapping.value();

                        for (String classMapping : classMappings) {

                            String uri = classMapping + methodMapping;
                            isAjax = uri.contains(request.getRequestURI());
                            if (isAjax) break outerloop;
                        }
                    } else {
                        isAjax = methodMapping.contains(request.getRequestURI());
                        if (isAjax) break outerloop;
                    }

                }
            }
        }

        return isAjax;

    }

}
