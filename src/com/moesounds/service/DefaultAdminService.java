package com.moesounds.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moesounds.dao.AdminDAO;
import com.moesounds.domain.User;
import com.moesounds.domain.enums.ApiType;

@Service
public class DefaultAdminService implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Collection<User> getAllUsers() {
        return adminDAO.getAllUsers();
    }

    @Override
    public User getUser(int userId) {
        return adminDAO.getUserWithUserId(userId);
    }

    @Override
    public User getUser(ApiType apiType, String apiUserId) {
        return adminDAO.getUserWithApiInfo(apiType, apiUserId);
    }

}
