package com.andersen.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andersen.entity.Portfolio;
import com.andersen.entity.Stock;
import com.andersen.entity.Stockportfolio;
import com.andersen.entity.Transaction;
import com.andersen.entity.User;


import com.andersen.service.PortfolioService;

@Repository
@Scope("prototype")
public class GenericDAOimpl<T> implements GenericDAO<T> {

	private Class<T> type;

	@Autowired
	private SessionFactory sessionFactory;

	
	public final void setType(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public void create(T entity) {
			
		getCurrentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		
		getCurrentSession().update(entity);
	}
	
	@Override
	public T readById(int id)  {

		T entity = getCurrentSession().get(type, id);
				
		return entity;
	}

	@Override
	public void delete(T entity) {
		
		getCurrentSession().delete(entity);
	}

	
	protected final Session getCurrentSession() {

		return sessionFactory.getCurrentSession();
	}


	@Override
	public void deleteById(int id) {
		
		Session session = getCurrentSession();
		T entity = session.load(type, id);
		session.delete(entity);
	}

	@Override
	public List<T> read() {
		
		Session session = sessionFactory.getCurrentSession();
		String typeToString = (""+type);
		String hql = "from "+typeToString.substring(6, typeToString.length());
		Query query = session.createQuery(hql);
			
		return query.getResultList();
	}
	
}

