package com.moesounds.domain;

public class User {

	public int userId;
	public String nickname;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickname=" + nickname + "]";
	}
	
}
