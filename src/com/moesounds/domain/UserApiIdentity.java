package com.moesounds.domain;

import com.moesounds.domain.enums.ApiType;

public class UserApiIdentity {

    private int userApiIdentityId;
    private int userId;
    private ApiType apiType;
    private String apiUserId;

    // Default Accessors **************
    public int getUserApiIdentityId() {
        return userApiIdentityId;
    }

    public int getUserId() {
        return userId;
    }

    public ApiType getApiType() {
        return apiType;
    }

    public String getApiUserId() {
        return apiUserId;
    }

    // For MyBatis ********************
    protected UserApiIdentity() {}

    @Override
    public String toString() {
        return "UserApiIdentity [userApiIdentityId=" + userApiIdentityId + ", userId=" + userId + ", apiType=" + apiType
                + ", apiUserId=" + apiUserId + "]";
    }

}
