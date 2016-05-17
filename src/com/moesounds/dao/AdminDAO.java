package com.moesounds.dao;

import java.util.List;

import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.domain.User;

@MoeSoundsDatabase
public interface AdminDAO {

	public List<User> getAllUsers();
}
