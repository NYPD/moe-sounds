package com.moesounds.interceptor;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.domain.User;
import com.moesounds.domain.enums.ApiType;
import com.moesounds.domain.enums.UserRole;
import com.moesounds.service.ApiLoginService;
import com.moesounds.util.AppConstants;

/**
 * Security interceptor that checks to see if current user is allowed to continue with the request
 * based on the {@link UserRole} passed in during class instantiation. If there is no current user
 * in the session It checks and see if the user has any cookies created from previous sessions. If
 * so, it attempts to use the appropriate third party API to re authenticate the user. If no cookies
 * are found, the user is redirected to an error page.
 * 
 * Keep in mind if for some reason the user does have the appropriate cookies, but does not have the
 * correct access the particular part in the system, this security filter should intercept the
 * request again and verify.
 * 
 * @author NYPD
 *
 */
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

        if (request == null) return false;

        User user = moeSoundsSessionBean.getUser();
        boolean isAllowedUser = user != null && user.getUserRole() == this.allowedRole;
        if (isAllowedUser) return true;

        boolean noCookies = request.getCookies() == null;
        if (noCookies) {
            response.sendRedirect("error/access-denied");
            return false;
        }

        ApiType apiType = null;

        for (Cookie cookie : request.getCookies()) {
            String cookieName = cookie.getName();

            boolean isNotApiTypeCookie = !AppConstants.API_TYPE_COOKIE_NAME.equals(cookieName);
            if(isNotApiTypeCookie) continue;

            String apiTypename = cookie.getValue();
            apiType = ApiType.findByName(apiTypename);

            break;
        }

        boolean noApiTypeCookieFound = apiType == null;
        if (noApiTypeCookieFound) {
            response.sendRedirect("error/access-denied");
            return false;
        }

        Map<String, Object> apiLoginServices = applicationContext.getBeansWithAnnotation(apiType.getApiLoginServiceClass());

        /*
         * We should theoretically find a API Type, if for some unknown reason we don't, send the
         * user to the login page
         */
        boolean noApiLoginService = apiLoginServices == null || apiLoginServices.size() == 0;
        if (noApiLoginService) {
            response.sendRedirect("admin");
            return false;
        }

        boolean isAjax = false;
        Object[] restControllers = applicationContext.getBeansWithAnnotation(RestController.class).values().toArray();

        outerloop: for (Object restController : restControllers) {

            RequestMapping classRequestMapping = restController.getClass().getAnnotation(RequestMapping.class);

            Method[] methods = restController.getClass().getMethods();

            for (Method method : methods) {

                RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
                if (methodRequestMapping == null) continue;

                String[] mappings = methodRequestMapping.value();

                for (String methodMapping : mappings) {

                    String[] classMappings = classRequestMapping.value();

                    for (String classMapping : classMappings) {

                        String uri = classMapping + methodMapping;
                        isAjax = uri.contains(request.getRequestURI());
                        if (isAjax) break outerloop;
                    }

                }
            }
        }

        ApiLoginService apiLoginService = (ApiLoginService) apiLoginServices.values().toArray()[0];
        apiLoginService.reAuthenticateUser(response);

        return false;
    }

}
