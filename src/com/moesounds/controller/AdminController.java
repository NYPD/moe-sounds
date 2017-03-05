package com.moesounds.controller;

import java.io.IOException;
import java.util.Collection;

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

    @RequestMapping
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "api/google-oauth-login")
    public void googleOAuthLogin(HttpServletResponse response, @RequestParam(value = "rememberMe", required = false) boolean rememberMe) throws IOException {

        moeSoundsSessionBean.setRememberMe(rememberMe);

        String authenticationRequestUrl = googleLoginService.getAuthenticationRequestUrl();

        response.sendRedirect(authenticationRequestUrl);

    }

    @RequestMapping(value = "api/google-oauth-verify")
    public ModelAndView googleOAuthVerify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        googleLoginService.verifyAuthenticationResponse(request);

        User moeSoundsUser = googleLoginService.getMoeSoundsUser();

        boolean unauthorized = moeSoundsUser == null;
        if (unauthorized) throw new UnauthorizedUserException(request);

        moeSoundsSessionBean.setUser(moeSoundsUser);

        boolean rememberMe = moeSoundsSessionBean.isRememberMe();
        if (rememberMe) googleLoginService.createUserCookies(response);

        return new ModelAndView("redirect:/admin/maintenance");
    }

    @RequestMapping(value = "maintenance")
    public ModelAndView getAdminMaintenancePage() {

        ModelAndView mav = new ModelAndView("maintenance");

        Collection<Page> allPages = moeSoundsService.getAllPages();
        mav.addObject("allPages", allPages);

        return mav;
    }

}
