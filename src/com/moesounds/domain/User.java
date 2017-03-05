package com.moesounds.domain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import com.moesounds.domain.enums.ApiType;
import com.moesounds.domain.enums.UserRole;

/**
 * Represents a user for the Moe Sounds web application. Primary use is for admin verification.
 * 
 * @author NYPD
 */
public class User {

    private int userId;
    private String nickname;
    private UserRole userRole;
    private String userProfilePicture;
    private Map<ApiType, UserApiIdentity> userApiIdentities;

    // Modified Accessors ********************************
    public UserApiIdentity getUserApiIdentity(ApiType apiType) {
        return userApiIdentities.get(apiType);
    }

    // Default Accessors *********************************
    public int getUserId() {
        return userId;
    }
    public String getNickname() {
        return nickname;
    }
    public UserRole getUserRole() {
        return userRole;
    }
    public Map<ApiType, UserApiIdentity> getUserApiIdentities() {
        return userApiIdentities;
    }
    public String getUserProfilePicture() {
        return userProfilePicture;
    }
    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    // For MyBatis ***************************************
    protected User() {}

    /**
     * Package-private setter - MyBatis only. Business logic should not call this method. This is
     * because MyBatis does not allow mapped fields to be of type java.util.Map
     */
    void setUserApiIdentities(Collection<UserApiIdentity> userApiIdentities) {
        this.userApiIdentities = (userApiIdentities.stream().collect(Collectors.toMap(UserApiIdentity::getApiType, (p) -> p)));
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", nickname=" + nickname + ", googleId=" + userRole + "]";
    }


}
