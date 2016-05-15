package com.moesounds.dao;

import java.util.List;

import com.moesounds.annotation.DefaultDatabase;
import com.moesounds.domain.User;

@DefaultDatabase
public interface UserDAO {

	public List<User> getAllUsers();
}
