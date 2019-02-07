package com.andersen.service;

import java.util.List;

import com.andersen.dao.GenericDAO;
import com.andersen.entity.User;

public interface UserService extends GenericDAO<User> {

	User getCurrentUser(String username);

	List<User> getUsers();

	void addUser(User newUser);

}
