package com.andersen.dao;

import com.andersen.entity.User;


public interface UserDAO extends GenericDAO<User> {
	
	public User getCurrentUser(String username);

}
