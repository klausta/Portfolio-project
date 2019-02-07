package com.andersen.dao;

import java.util.List;

import org.hibernate.query.Query;

public interface GenericDAO<T> {

	void create(T entity);
	
	void update(T entity);

	void delete(T entity);
	
	void deleteById(int id);
	
	public void setType(Class<T> type);

	T readById(int id);
	
	List<T> read();
	
}
