package com.moesounds.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moesounds.annotation.DefaultController;
import com.moesounds.domain.Page;
import com.moesounds.service.ApiLoginService;
import com.moesounds.service.MoeSoundsService;
import com.moesounds.util.AppConstants;

@DefaultController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private MoeSoundsService moeSoundsService;
    @Autowired
    private ApiLoginService apiLoginService;

    @RequestMapping
    public ModelAndView getLoginPage() {

        return new ModelAndView("login");
    }

    @RequestMapping(value = "google-oauth-login")
    public void googleOAuthLogin(HttpServletResponse response, @RequestParam(value = "rememberMe", required = false) boolean rememberMe) throws IOException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put(AppConstants.REMEBER_ME_PARAMETER_NAME, String.valueOf(rememberMe));

        String authenticationRequestUri = apiLoginService.getAuthenticationRequestUrl();

        response.sendRedirect(authenticationRequestUri.toString());

    }

    @RequestMapping(value = "google-oauth-verify")
    public void googleOAuthVerify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        apiLoginService.verifyAuthenticationRequestResponse(request);
    }

    @RequestMapping(value = "maintenance")
    public ModelAndView getAdminMaintenancePage() {

        ModelAndView mav = new ModelAndView("maintenance");

        Page randomPage = moeSoundsService.getRandomPage();

        mav.addObject("page", randomPage);

        return mav;
    }

}
