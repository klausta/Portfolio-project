package com.andersen.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andersen.entity.Stock;

@Repository
public class StockDAOimpl extends GenericDAOimpl<Stock> implements StockDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Stock getStockFromSymbol(String id) {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Stock where symbol = :symbol");
		query.setParameter("symbol", id);
		Stock stock = (Stock) query.getSingleResult();
		
		return stock;
	}
}
