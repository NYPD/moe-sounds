package com.moesounds.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.domain.User;
import com.moesounds.domain.enums.ApiType;
import com.moesounds.domain.enums.UserRole;
import com.moesounds.service.ApiLoginService;
import com.moesounds.util.AppConstants;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MoeSoundsSessionBean moeSoundsSessionBean;
    @Autowired
    private ApplicationContext applicationContext;

    private UserRole allowedRole;

    public SecurityInterceptor(UserRole allowedRole) {
        this.allowedRole = allowedRole;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws IOException {

        User user = moeSoundsSessionBean.getUser();
        boolean isAllowedUser = user != null && user.getUserRole() == this.allowedRole;

        if (isAllowedUser) {
            return true;
        } else {

            ApiType apiType = ApiType.GOOGLE;// null;

            for (Cookie cookie : request.getCookies()) {
                String cookieName = cookie.getName();

                boolean isNotApiTypeCookie = !AppConstants.API_TYPE_COOKIE_NAME.equals(cookieName);
                if(isNotApiTypeCookie) continue;

                String apiTypename = cookie.getValue();
                apiType = ApiType.findByName(apiTypename);
            }

            boolean noApiTypeCookieFound = apiType == null;
            if (noApiTypeCookieFound) response.sendRedirect("error/acess-denied");

            Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(apiType.getApiLoginServiceClass());

            /*
             * We should theoretically find one, if for some unknown reason we don't, send the user
             * to the login page
             */
            boolean noApiLoginService = beansWithAnnotation == null || beansWithAnnotation.size() == 0;
            if (noApiLoginService) {
                response.sendRedirect("admin");
                return false;
            }

            ApiLoginService apiLoginService = (ApiLoginService) beansWithAnnotation.values().toArray()[0];

            apiLoginService.reAuthenticateUser(response);

        }

        return false;
    }

}
