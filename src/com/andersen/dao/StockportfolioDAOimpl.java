package com.andersen.dao;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andersen.entity.Stockportfolio;

@Repository
public class StockportfolioDAOimpl extends GenericDAOimpl<Stockportfolio> implements StockportfolioDAO {

	@Autowired
	SessionFactory sessionFactory;
	
}
