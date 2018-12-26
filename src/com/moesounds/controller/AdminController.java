package com.moesounds.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.annotation.GoogleLogin;
import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.domain.Page;
import com.moesounds.domain.User;
import com.moesounds.exception.UnauthorizedUserException;
import com.moesounds.service.ApiLoginService;
import com.moesounds.service.MoeSoundsService;
import com.moesounds.util.AppConstants;

@DefaultController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private MoeSoundsService moeSoundsService;
    @Autowired
    private MoeSoundsSessionBean moeSoundsSessionBean;
    @Autowired
    @GoogleLogin
    private ApiLoginService googleLoginService;

    @RequestMapping(value = {"", "login"})
    public ModelAndView getLoginPage(@RequestParam(value = "prevPath", required = false) String prevPath) {

        User user = moeSoundsSessionBean.getUser();

        if (user != null)
            return new ModelAndView("redirect:/admin/maintenance");
        else if (prevPath != null)
            return new ModelAndView("redirect:" + prevPath);

        return new ModelAndView("login");
    }

    @RequestMapping(value = "logout")
    public ModelAndView logout(HttpSession session,HttpServletRequest request, HttpServletResponse response) {

        moeSoundsSessionBean.setUser(null);
        session.invalidate();

        ModelAndView modelAndView = new ModelAndView("redirect:/admin");

        if (request == null || request.getCookies() == null) return modelAndView;

        for (Cookie cookie : request.getCookies()) {
            String cookieName = cookie.getName();

            boolean isNotApiTypeCookie = !AppConstants.API_TYPE_COOKIE_NAME.equals(cookieName);
            if (isNotApiTypeCookie) continue;

            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath("/admin");

            response.addCookie(cookie);
        }

        return modelAndView;
    }

    @RequestMapping(value = "api/google-oauth-login")
    public void googleOAuthLogin(HttpServletResponse response,
            @RequestParam(value = "rememberMe", required = false) boolean rememberMe,
            @RequestParam(value = "prevPath", required = false) String prevPath) throws IOException {

        moeSoundsSessionBean.setRememberMe(rememberMe);
        moeSoundsSessionBean.setPrevPath(prevPath);

        String authenticationRequestUrl = googleLoginService.getAuthenticationRequestUrl();

        response.sendRedirect(authenticationRequestUrl);

    }

    @RequestMapping(value = "api/google-oauth-verify")
    public ModelAndView googleOAuthVerify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        googleLoginService.verifyAuthenticationResponse(request);

        User moeSoundsUser = googleLoginService.getUser();

        boolean unauthorized = moeSoundsUser == null;
        if (unauthorized) throw new UnauthorizedUserException(request);

        moeSoundsSessionBean.setUser(moeSoundsUser);

        boolean rememberMe = moeSoundsSessionBean.isRememberMe();
        if (rememberMe) googleLoginService.createUserCookies(response);

        String prevPath = moeSoundsSessionBean.getPrevPath();
        boolean hasPrevPath = prevPath != null;

        if (hasPrevPath) {
            moeSoundsSessionBean.setPrevPath(null);
            return new ModelAndView("redirect:" + prevPath);
        } else {
            return new ModelAndView("redirect:/admin/maintenance");
        }

    }

    @RequestMapping(value = "maintenance")
    public ModelAndView getAdminMaintenancePage() {

        ModelAndView mav = new ModelAndView("maintenance");

        Collection<Page> allPages = moeSoundsService.getAllPages();
        mav.addObject("allPages", allPages);

        return mav;
    }

}
