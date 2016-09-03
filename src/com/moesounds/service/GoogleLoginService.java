package com.moesounds.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.moesounds.beans.MoeSoundsSessionBean;
import com.moesounds.exception.AuthRequestUrlBuilderException;
import com.moesounds.exception.InvalidStateTokenException;

@Service
public class GoogleLoginService implements ApiLoginService {

    @Autowired
    private GoogleClientSecrets googleClientSecrets;
    @Autowired
    private JacksonFactory googleJacksonFactory;
    @Autowired
    private MoeSoundsSessionBean moeSoundsSessionBean;

    private static final String SECURITY_TOKEN_PARAMETER = "security_token";

    private static String GOOGLE_OAUTH_URI;
    private static String GOOGLE_OAUTH_REDIRECT_URI;
    private static String CLIENT_ID;
    private static String CLIENT_SECRET;

    @Override
    public String getAuthenticationRequestUrl() {

        try {
            String stateToken = this.getStateToken(new HashMap<>());

            moeSoundsSessionBean.setGoogleStateToken(stateToken);

            String authRequestQueryString = "client_id=" + CLIENT_ID +
                    "&response_type=code" +
                    "&scope=openid profile" +
                    "&redirect_uri=" + GOOGLE_OAUTH_REDIRECT_URI +
                    "&state=" + stateToken;

            URL googleOauthUrl = new URL(GOOGLE_OAUTH_URI);

            String protocol = googleOauthUrl.getProtocol();
            String authority = googleOauthUrl.getAuthority();
            String path = googleOauthUrl.getPath();

            URI uri = new URI(protocol, authority, path, authRequestQueryString, null);
            return uri.toASCIIString();
        } catch (URISyntaxException | UnsupportedEncodingException | MalformedURLException exception) {
            throw new AuthRequestUrlBuilderException(); // TODO finish
        }

    }

    @Override
    public void verifyAuthenticationRequestResponse(HttpServletRequest request) {

        String googleStateToken = request.getParameter("state");
        String sessionStateToken = moeSoundsSessionBean.getGoogleStateToken();

        boolean notSameStateToken = !StringUtils.equals(googleStateToken, sessionStateToken);
        if (notSameStateToken) throw new InvalidStateTokenException();

        String code = request.getParameter("code");

        try {
            GoogleTokenResponse googleTokenResponse = new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), googleJacksonFactory, CLIENT_ID, CLIENT_SECRET, code, GOOGLE_OAUTH_REDIRECT_URI).execute();

            System.out.println("Access token: " + googleTokenResponse.getAccessToken());
        } catch (IOException e) {
            e.printStackTrace();// TODO finish
        }

    }

    private String getStateToken(Map<String, String> parameters) throws UnsupportedEncodingException {

        String securityTokenString = new BigInteger(130, new SecureRandom()).toString(32);

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(SECURITY_TOKEN_PARAMETER + "=" + securityTokenString);

        parameters.forEach((key, value) -> {
            stringBuffer.append("&" + key);
            stringBuffer.append("=" + value);
        });

        return URLEncoder.encode(stringBuffer.toString(), "UTF-8");

    }

    private Map<String, String> getStateTokenParameters(String statetoken) {

        String decodedStateToken;

        try {
            decodedStateToken = URLDecoder.decode(statetoken, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            throw new AuthRequestUrlBuilderException();
        }

        String[] parameters = decodedStateToken.split("&");

        Map<String, String> parameterMap = new HashMap<String, String>();

        for (String param : parameters) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];

            parameterMap.put(name, value);
        }

        parameterMap.remove(SECURITY_TOKEN_PARAMETER);

        return parameterMap;
    }

    @PostConstruct
    private void init() {

        Details webDetails = googleClientSecrets.getWeb();

        GOOGLE_OAUTH_URI = webDetails.getAuthUri();
        GOOGLE_OAUTH_REDIRECT_URI = webDetails.getRedirectUris().get(0);
        CLIENT_ID = webDetails.getClientId();
        CLIENT_SECRET = webDetails.getClientSecret();
    }

}
