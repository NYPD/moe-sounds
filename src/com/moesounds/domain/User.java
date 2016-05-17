package com.moesounds.domain;

/**
 * Represents a user for the Moe Sounds web application. Primary use is for admin verification. 
 * @author NYPD
 */
public class User {

	private int userId;
	private String nickname;
	private String googleId;
	
	public int getUserId() {
		return userId;
	}
	public String getNickname() {
		return nickname;
	}
	public String getGoogleId() {
		return googleId;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickname=" + nickname + ", googleId=" + googleId + "]";
	}
	
}
