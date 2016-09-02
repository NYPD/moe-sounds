package com.moesounds.service;

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

import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;

@Service
public class DefaultGoogleApiService implements GoogleApiService {

	@Autowired
	private GoogleClientSecrets googleClientSecrets;
	
	public static final String GOOGLE_OAUTH_OPEN_ID_CONNECT_URI = "https://accounts.google.com/o/oauth2/v2/auth";
	public static final String GOOGLE_OAUTH_REDIRECT_URI = "http://casa.erikmunoz.me:8080/admin/google-oauth-verify";
	
	public static final String URL_ENCODED_EQUALS = "%3D";
	public static final String URL_ENCODED_AMPERSAND = "%26";
	public static final String URL_ENCODED_SPACE = "%20";
	
	private static final String SECURITY_TOKEN = "security_token";
	
	@Override
	public String getStateToken(Map<String, String> parameters) {

		String securityTokenString = new BigInteger(130, new SecureRandom()).toString(32);
		
		StringBuffer stringBuffer = new StringBuffer();
		
		stringBuffer.append(SECURITY_TOKEN + "=" + securityTokenString);
		
		parameters.forEach((key, value) -> {
			stringBuffer.append("&" + key);
			stringBuffer.append("=" + value);
		});
		
		
		try {
			return URLEncoder.encode(stringBuffer.toString(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}
	
	@Override
	public Map<String, String> getStateTokenParameters(String statetoken) {

		String decodedStateToken;
		
		try {
			decodedStateToken = URLDecoder.decode(statetoken, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
		String[] parameters = decodedStateToken.split("&");
		
		Map<String, String> parameterMap = new HashMap<String, String>();
		 
	    for (String param : parameters) {
	    	
	        String name  = param.split("=")[0];
	        String value = param.split("=")[1];
	        
	        parameterMap.put(name, value);
	    }
	    
	    parameterMap.remove(SECURITY_TOKEN);
	    
		return parameterMap;
	}

	@Override
	public URI getAuthenticationRequestUri(String stateToken) {

		String clientId = googleClientSecrets.getDetails().getClientId();
		
		String authenticationRequestUrl =   "client_id=" + clientId + 
											"&response_type=code" +
											"&scope=openid email profile" +
											"&redirect_uri=" + GOOGLE_OAUTH_REDIRECT_URI +
											"&state=" + stateToken;
		
		try {
			
			return new URI("https", "accounts.google.com", "/o/oauth2/v2/auth" , authenticationRequestUrl, null);
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
	}

}
