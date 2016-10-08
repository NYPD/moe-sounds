package com.moesounds.dao;

import java.util.List;

import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.domain.User;
import com.moesounds.domain.enums.ApiType;

@MoeSoundsDatabase
public interface AdminDAO {

    public List<User> getAllUsers();

    public User getUserWithUserId(int userId);

    public User getUserWithApiInfo(ApiType apiType, String apiUserId);

}
