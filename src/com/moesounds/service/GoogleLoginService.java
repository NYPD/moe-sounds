package com.moesounds.service;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.model.Userinfoplus;
import com.moesounds.annotation.GoogleLogin;
import com.moesounds.beans.GoogleSessionBean;
import com.moesounds.domain.User;
import com.moesounds.domain.enums.ApiType;
import com.moesounds.exception.InvalidStateTokenException;
import com.moesounds.exception.LoginIOException;
import com.moesounds.exception.NoUserInSessionException4Ajax;
import com.moesounds.exception.google.AuthorizationCodeResponseException;
import com.moesounds.util.AppConstants;

@Service
@GoogleLogin
public class GoogleLoginService extends ApiLoginService {

    @Autowired
    private GoogleClientSecrets googleClientSecrets;
    @Autowired
    private JacksonFactory googleJacksonFactory;
    @Autowired
    private NetHttpTransport netHttpTransport;
    @Autowired
    private GoogleSessionBean googleSessionBean;
    @Autowired
    private Environment springEnvironment;
    @Autowired
    private AdminService adminService;

    private static final String PROFILE_SCOPE = "profile";
    private static final String OPEN_ID_SCOPE = "openid";

    private static String GOOGLE_OAUTH_REDIRECT_URI;
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;

    private final Logger logger = LoggerFactory.getLogger(GoogleLoginService.class);

    @Override
    public String getAuthenticationRequestUrl() {

        String stateToken = this.getStateToken();
        GoogleAuthorizationCodeRequestUrl googleAuthorizationCodeRequestUrl = new GoogleAuthorizationCodeRequestUrl(
                googleClientSecrets,
                GOOGLE_OAUTH_REDIRECT_URI,
                Arrays.asList(OPEN_ID_SCOPE, PROFILE_SCOPE));

        googleAuthorizationCodeRequestUrl.setState(stateToken);
        googleSessionBean.setGoogleStateToken(stateToken);

        return googleAuthorizationCodeRequestUrl.build();

    }

    @Override
    public void verifyAuthenticationResponse(HttpServletRequest request) {

        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        boolean hasQueryString = queryString != null;
        if (hasQueryString) requestURL.append("?").append(queryString);

        String encodedResponseUrl = requestURL.toString();
        AuthorizationCodeResponseUrl authorizationCodeResponseUrl = new AuthorizationCodeResponseUrl(encodedResponseUrl);

        String error = authorizationCodeResponseUrl.getError();

        boolean hasError = error != null;
        if (hasError) throw new AuthorizationCodeResponseException(authorizationCodeResponseUrl);

        String googleStateToken = authorizationCodeResponseUrl.getState();
        String sessionStateToken = googleSessionBean.getGoogleStateToken();

        boolean notSameStateToken = !StringUtils.equals(googleStateToken, sessionStateToken);
        if (notSameStateToken) throw new InvalidStateTokenException();

        try {

            String code = authorizationCodeResponseUrl.getCode();
            GoogleTokenResponse googleTokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    netHttpTransport,
                    googleJacksonFactory,
                    CLIENT_ID,
                    CLIENT_SECRET,
                    code,
                    GOOGLE_OAUTH_REDIRECT_URI).execute();

            GoogleCredential googleCredential = new GoogleCredential.Builder()
                    .setJsonFactory(googleJacksonFactory)
                    .setTransport(netHttpTransport)
                    .setClientSecrets(googleClientSecrets)
                    .build()
                    .setFromTokenResponse(googleTokenResponse);

            googleSessionBean.setGoogleCredential(googleCredential);

        } catch (IOException e) {
            logger.error("Error attempting to get a GoogleTokenResponse", e);
            throw new LoginIOException();
        }

    }

    @Override
    public User getMoeSoundsUser() {

        GoogleCredential googleCredential = googleSessionBean.getGoogleCredential();
        Oauth2 oauth2 = new Oauth2.Builder(
                netHttpTransport,
                googleJacksonFactory,
                googleCredential).setApplicationName(AppConstants.APPLICATION_NAME).build();

        try {
            Userinfoplus userinfoplus = oauth2.userinfo().get().execute();

            String apiUserId = userinfoplus.getId();
            String profilePictureUrl = userinfoplus.getPicture();

            User user = adminService.getUser(ApiType.GOOGLE, apiUserId);

            if (user == null)
                return null;

            user.setUserProfilePicture(profilePictureUrl);

            return user;
        } catch (IOException e) {
            logger.error("Error attempting to get the Google Userinfoplus object", e);
            throw new LoginIOException();
        }

    }

    @Override
    public void createUserCookies(HttpServletResponse response) {

        Cookie cookie = new Cookie(AppConstants.API_TYPE_COOKIE_NAME, ApiType.GOOGLE.name());
        cookie.setPath("/admin");
        cookie.setMaxAge(AppConstants.FIVE_YEARS_IN_SECONDS);

        response.addCookie(cookie);

    }

    @Override
    public void reAuthenticateUser(HttpServletRequest request, HttpServletResponse response) {

        boolean isAjax = this.isHttpServletRequestAjax(request);
        if (isAjax) throw new NoUserInSessionException4Ajax();

        try {
            response.sendRedirect("/admin/api/google-oauth-login");
        } catch (IOException e) {
            logger.error("Error redirecting to google-oauth-login", e);
            throw new LoginIOException();
        }
    }

    private String getStateToken() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }

    @PostConstruct
    private void init() {

        Details webDetails = googleClientSecrets.getWeb();

        CLIENT_ID = webDetails.getClientId();
        CLIENT_SECRET = webDetails.getClientSecret();

        // If it is a development profile, try and find a localhost redirect
        String[] activeProfiles = springEnvironment.getActiveProfiles();
        boolean isDevelopment = Arrays.stream(activeProfiles).filter(x -> AppConstants.DEVELOPMENT_PROFILE.equals(x)).findAny().orElse(null) != null;

        for (String redirectUri : webDetails.getRedirectUris()) {

            boolean isLocalHost = redirectUri.contains("localhost");

            if (isLocalHost && isDevelopment)
                GOOGLE_OAUTH_REDIRECT_URI = redirectUri;
            else if (!isLocalHost && !isDevelopment)
                GOOGLE_OAUTH_REDIRECT_URI = redirectUri;

            if (GOOGLE_OAUTH_REDIRECT_URI != null) break;

        }

    }

}
