package com.andersen.service;

import java.util.List;

import java.util.Set;

import javax.validation.Valid;

import com.andersen.entity.Portfolio;
import com.andersen.entity.Stock;
import com.andersen.entity.Stockportfolio;
import com.andersen.entity.User;

public interface PortfolioService {

	List<Portfolio> getPortfolios();

	void deletePortfolio(int portfolioId);

	Portfolio getPortfolio(int portfolioId);

	Stockportfolio getStockportfolio(int stockportfolioId);
	
	Portfolio getPortfolioJoinFetchTransactions(int portfolioId);

	void updatePortfolio(Portfolio portfolio, User user);

	void addPortfolio(Portfolio portfolio, User user); 

	void addCalculatedValues(Set<Stockportfolio> stockportfolio);

	public void setCostAndQuantityFromHistTransactions(Stockportfolio stockportfolio);

	public void setCurrentMarketPrice(Stockportfolio stockportfolio);
	
	public void setProfitLoss(Stockportfolio stockportfolio);
	
}
