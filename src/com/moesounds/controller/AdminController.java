package com.moesounds.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Enumeration;
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
import com.moesounds.exception.InvalidStateTokenException;
import com.moesounds.service.GoogleApiService;
import com.moesounds.service.MoeSoundsService;
import com.moesounds.util.AppConstants;

@DefaultController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private MoeSoundsService moeSoundsService;
	@Autowired
	private GoogleApiService googleApiService;
	
	@RequestMapping
	public ModelAndView getLoginPage() {
		//TODO logic to determine if they need to login or not
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value = "google-oauth-login")
	public void googleOAuthLogin(HttpServletResponse response, HttpSession session, @RequestParam(value = "rememberMe", required = false) boolean rememberMe) throws IOException {
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put(AppConstants.REMEBER_ME_PARAMETER_NAME, String.valueOf(rememberMe));
		
		String loginStateToken = googleApiService.getStateToken(parameters);
		URI authenticationRequestUri = googleApiService.getAuthenticationRequestUri(loginStateToken);
		
		session.setAttribute("googleStateToken", loginStateToken);
		
		response.sendRedirect(authenticationRequestUri.toString());
		
	}
	
	@RequestMapping(value = "google-oauth-verify")
	public void googleOAuthVerify(HttpServletRequest request, HttpServletResponse response, HttpSession session) {  
		
		
		String googleStateToken = request.getParameter("state");
		String sessionStateToken = (String) session.getAttribute("googleStateToken");
		
		boolean notSameState = !googleStateToken.equals(sessionStateToken);
		if(notSameState) throw new InvalidStateTokenException();
		
		Map<String, String> stateTokenParameters = googleApiService.getStateTokenParameters(googleStateToken);
		
		String code = request.getParameter("code");
		
		request.getParameterMap();
		
		
		
		
		
		
		
		
//		String clientId = googleClientSecrets.getDetails().getClientId();
//		String clientSecret = googleClientSecrets.getDetails().getClientSecret();
//		
//		
//		GoogleTokenResponse googleTokenResponse = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(), clientId, clientSecret, code, "http://casa.erikmunoz.me:8080/admin/verify").execute();
//
//		System.out.println("Access token: " + googleTokenResponse.getAccessToken());
	}
	
	@RequestMapping(value = "maintenance")
	public ModelAndView getAdminMaintenancePage() {
		
		ModelAndView mav = new ModelAndView("maintenance");
		
		Page randomPage = moeSoundsService.getRandomPage();
		
		mav.addObject("page", randomPage);
		
		return mav;
	}
	
	
	
}
