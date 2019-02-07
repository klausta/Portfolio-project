package com.andersen.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.andersen.entity.Portfolio;

@Repository
public class PortfolioDAOimpl extends GenericDAOimpl<Portfolio> implements PortfolioDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Portfolio getPortfolioJoinFetchTransactions(int portfolioId) {

		Session session = sessionFactory.getCurrentSession();
		
		String hql = "select p " + "from Portfolio p " + 
					 "left join fetch p.stockportfolio s " + 
				     "left join fetch s.transaction " + 
					 "where p.portfolioId =: id";
		
		Query query = session.createQuery(hql);
		query.setParameter("id", portfolioId);
		Portfolio portfolio = (Portfolio) query.getSingleResult();

		return portfolio;
	}

}
