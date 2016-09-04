package com.moesounds.service;

import java.util.Collection;

import com.moesounds.domain.User;
import com.moesounds.domain.enums.ApiType;

public interface AdminService {

    public Collection<User> getAllUsers();
    public User getUser(ApiType apiType, String apiUserId);
}
