package com.andersen.dao;

import com.andersen.entity.Portfolio;


public interface PortfolioDAO extends GenericDAO<Portfolio>  {

	Portfolio getPortfolioJoinFetchTransactions(int portfolioId);

}
