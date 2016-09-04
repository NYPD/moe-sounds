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
import com.moesounds.annotation.GoogleLogin;
import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.domain.Page;
import com.moesounds.domain.User;
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

    @RequestMapping
    public ModelAndView getLoginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "api/google-oauth-login")
    public void googleOAuthLogin(HttpServletResponse response, @RequestParam(value = "rememberMe", required = false) boolean rememberMe) throws IOException {

        Map<String, String> parameters = new HashMap<>();
        parameters.put(AppConstants.REMEBER_ME_PARAMETER_NAME, String.valueOf(rememberMe));

        String authenticationRequestUri = googleLoginService.getAuthenticationRequestUrl();

        response.sendRedirect(authenticationRequestUri.toString());

    }

    @RequestMapping(value = "api/google-oauth-verify")
    public ModelAndView googleOAuthVerify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

        googleLoginService.verifyAuthenticationResponse(request);
        User moeSoundsUser = googleLoginService.getMoeSoundsUser();

        moeSoundsSessionBean.setUser(moeSoundsUser);

        return new ModelAndView("redirect:/admin/maintenance");
    }

    @RequestMapping(value = "maintenance")
    public ModelAndView getAdminMaintenancePage() {

        ModelAndView mav = new ModelAndView("maintenance");

        Page randomPage = moeSoundsService.getRandomPage();

        mav.addObject("page", randomPage);

        return mav;
    }

}
