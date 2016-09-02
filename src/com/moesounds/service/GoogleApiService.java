package com.moesounds.service;

import java.net.URI;
import java.util.Map;

public interface GoogleApiService {

	/**
	 * Creates an anti-forgery state token by using a random number generator to verify that the Moe Sounds
	 * user is making the google login request and not a malicious attacker.
	 * 
	 * This method also takes in a Map of <String, String> parameters. These parameters can be any
	 * information needed to recover the context when the login request comes back to the Moe 
	 * Sounds server. e.g. A "remember me" boolean or user id
	 * 
	 * @param parameters Any additional parameters to temporarily store
	 * @return anti-forgery state token
	 */
	public String getStateToken(Map<String, String> parameters);
	
	/**
	 * After a user has been verified, this method will retrieve the Map parameters (if any) that
	 * was used to store information needed to recover the context.
	 * 
	 * Returns an empty map if no parameters was used.
	 * 
	 * @param statetoken The state token returned from the Google authentication request
	 * @return Map of parameters used to store information in. Empty map if no parameters were found
	 */
	public Map<String, String> getStateTokenParameters(String statetoken);

	/**
	 * Creates the authentication request String URL needed to send to Google.
	 * 
	 * @param stateToken Token used to verify the return request
	 * @return The authentication request String URL
	 */
	public URI getAuthenticationRequestUri(String stateToken);
	
	
}
