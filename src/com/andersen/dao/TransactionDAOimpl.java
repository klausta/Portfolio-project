package com.andersen.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andersen.entity.Stockportfolio;
import com.andersen.entity.Transaction;

@Repository
public class TransactionDAOimpl extends GenericDAOimpl<Transaction> implements TransactionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Object[]> getCostAndQuantity(Stockportfolio stockportfolio) {

		Session session = sessionFactory.getCurrentSession();

		String hql = "select sum(t.quantity), " + 
				"sum(t.netAmount), " + 
				"sum(t.grossAmount), " + 
				"sum(t.currencyExchangeFee), " + 
				"sum(t.transactionFee) " + 
				"from Transaction t " + 
				"join t.stockportfolio s " + 
				"where s.stockportfolioId =: id";

		Query query = session.createQuery(hql);
		query.setParameter("id", stockportfolio.getStockportfolioId());

		return query.getResultList();
	}
}
