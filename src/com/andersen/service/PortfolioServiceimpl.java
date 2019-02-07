package com.andersen.service;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.andersen.dao.PortfolioDAO;
import com.andersen.dao.StockportfolioDAO;
import com.andersen.dao.TransactionDAO;

import com.andersen.entity.HistoricalQuote;
import com.andersen.entity.HistoricalQuoteDetail;
import com.andersen.entity.Portfolio;

import com.andersen.entity.Stockportfolio;
import com.andersen.entity.Transaction;
import com.andersen.entity.User;
import com.andersen.restclient.HistoricalQuoteManager;

@Service
public class PortfolioServiceimpl implements PortfolioService {

	@Autowired
	private PortfolioDAO portfolioDAO;

	@Autowired
	private StockportfolioDAO stockportfolioDAO;

	@Autowired
	private TransactionDAO transactionDAO;

	@PostConstruct
	public void setType() {

		portfolioDAO.setType(Portfolio.class);
		stockportfolioDAO.setType(Stockportfolio.class);
		transactionDAO.setType(Transaction.class);
	}

	@Override
	@Transactional
	public List<Portfolio> getPortfolios() {

		return portfolioDAO.read();
	}

	@Override
	@Transactional
	public void deletePortfolio(int id) {

		portfolioDAO.deleteById(id);
	}

	@Override
	@Transactional
	public Portfolio getPortfolio(int id) {

		return portfolioDAO.readById(id);
	}

	@Override
	@Transactional
	public Stockportfolio getStockportfolio(int id) {

		return stockportfolioDAO.readById(id);
	}

	@Override
	@Transactional
	public void addPortfolio(Portfolio portfolio, User user) {

		portfolio.setUser(user);
		portfolioDAO.create(portfolio);
	}

	@Override
	@Transactional
	public void updatePortfolio(Portfolio portfolio, User user) {

		portfolio.setUser(user);
		portfolioDAO.update(portfolio);
	}

	@Override
	@Transactional
	public void addCalculatedValues(Set<Stockportfolio> stockportfolioSet) {

		for (Stockportfolio stockportfolio : stockportfolioSet) {

			setCostAndQuantityFromHistTransactions(stockportfolio);
			setCurrentMarketPrice(stockportfolio);
			setProfitLoss(stockportfolio);
		}
	}

	
	@Override
	@Transactional
	public void setCostAndQuantityFromHistTransactions(Stockportfolio stockportfolio) {

		// TO-DO: poor solution as each iteration above triggers a new transaction. 

		List<Object[]> result = transactionDAO.getCostAndQuantity(stockportfolio);

		for (Object[] o : result) {
			if (o[0] != null) {
				stockportfolio.setQuantity((long) o[0]);
				stockportfolio.setNetAmount((double) o[1]);
				stockportfolio.setGrossAmount((double) o[2]);
				stockportfolio.setCurrencyExchangeFee((double) o[3]);
				stockportfolio.setTransactionFee((double) o[4]);
			}
		}
	}

	@Override
	public void setCurrentMarketPrice(Stockportfolio stockportfolio) {

		HistoricalQuoteManager historicalQuoteManager = new HistoricalQuoteManager();

		HistoricalQuote historicalQuote = historicalQuoteManager
				.getHistoricalQuote(stockportfolio.getStock().getSymbol());

		List<HistoricalQuoteDetail> quoteDetail = historicalQuote.getStock_prices();
		Float price = quoteDetail.get(0).getClose();

		stockportfolio.setPrice(price);
	}

	public void setProfitLoss(Stockportfolio stockportfolio) {

		stockportfolio.setAvgUnitCost();
		stockportfolio.setProfitLoss();
		stockportfolio.setProfitLossPercentage();
	}

	@Override
	@Transactional
	public Portfolio getPortfolioJoinFetchTransactions(int portfolioId) {

		Portfolio portfolio = portfolioDAO.getPortfolioJoinFetchTransactions(portfolioId);
		Hibernate.initialize(portfolio);

		return portfolio;
	}

}
