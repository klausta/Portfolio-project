package com.andersen.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.dao.GenericDAOimpl;
import com.andersen.dao.UserDAO;
import com.andersen.entity.Stock;
import com.andersen.entity.User;

@Service
public class UserServiceimpl extends GenericDAOimpl<User> implements UserService {

	@Autowired
	UserDAO userDAO;

	@PostConstruct
	public void setType()  {
		userDAO.setType(User.class);
	}
	
	
	@Override
	@Transactional
	public User getCurrentUser(String username) {
		
		return userDAO.getCurrentUser(username);
	}

	
	@Transactional
	@Override
	public List<User> getUsers() {
				
		return userDAO.read();
	}

	
	@Transactional
	@Override
	public void addUser(User newUser) {
		
		userDAO.create(newUser);
	}
}
