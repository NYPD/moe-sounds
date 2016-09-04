package com.moesounds.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.domain.User;
import com.moesounds.domain.enums.UserRole;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private MoeSoundsSessionBean moeSoundsSessionBean;

    private UserRole allowedRole;

    public SecurityInterceptor(UserRole allowedRole) {
        this.allowedRole = allowedRole;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) {

        User user = moeSoundsSessionBean.getUser();

        System.out.println(user);
        System.out.println(allowedRole);

        return true;
    }
}
