package com.moesounds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.domain.User;
import com.moesounds.domain.enums.ApiType;

@MoeSoundsDatabase
public interface AdminDAO {

    public List<User> getAllUsers();

    public User getUserWithUserId(@Param("userId") int userId);
    public User getUserWithApiInfo(@Param("apiType") ApiType apiType, @Param("apiUserId") String apiUserId);

}
