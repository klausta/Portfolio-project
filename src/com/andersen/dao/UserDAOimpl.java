package com.andersen.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andersen.entity.User;

@Repository
public class UserDAOimpl extends GenericDAOimpl<User> implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public User getCurrentUser(String username) {

		Session session = sessionFactory.getCurrentSession();

		return session.get(User.class, username);
	}
}
