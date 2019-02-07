package com.andersen.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.dao.PortfolioDAO;
import com.andersen.dao.StockDAO;
import com.andersen.dao.StockportfolioDAO;

import com.andersen.entity.Portfolio;
import com.andersen.entity.Stock;
import com.andersen.entity.Stockportfolio;

@Service
public class StockServiceimpl implements StockService {

	@Autowired
	private StockDAO stockDAO;

	@Autowired
	private PortfolioDAO portfolioDAO;

	@Autowired
	private StockportfolioDAO stockportfolioDAO;

	@PostConstruct
	public void setType() {
		stockDAO.setType(Stock.class);
	}

	@Override
	@Transactional
	public Stock getStockFromSymbol(String symbol) {

		return stockDAO.getStockFromSymbol(symbol);
	}

	@Override
	@Transactional
	public void addStockToPortfolio(String stockSymbol, int portfolioId) {

		Stock stock = stockDAO.getStockFromSymbol(stockSymbol);
		Portfolio portfolio = portfolioDAO.readById(portfolioId);

		stockportfolioDAO.create(new Stockportfolio(portfolio, stock));
	}

	@Override
	@Transactional
	public Stock getStock(int stockId) {

		return stockDAO.readById(stockId);
	}

	@Override
	@Transactional
	public List<Stock> getStocks() {

		return stockDAO.read();
	}

	@Override
	@Transactional
	public void addStockToPortfolioFromId(int stockId, int portfolioId) {

		Stock stock = stockDAO.readById(stockId);
		Portfolio portfolio = portfolioDAO.readById(portfolioId);
		stockportfolioDAO.create(new Stockportfolio(portfolio, stock));
	}

	@Override
	@Transactional
	public void deleteStockFromPortfolio(int stockPortfolioId) {
		
		Stockportfolio stockportfolio = stockportfolioDAO.readById(stockPortfolioId);
		stockportfolio.getPortfolio().removeStockportfolio(stockportfolio); //association needs to be removed from parent collection to prevent re-save
 		stockportfolioDAO.delete(stockportfolio);
	}
}
