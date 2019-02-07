package com.andersen.service;

import java.util.List;

import com.andersen.entity.Stock;

public interface StockService {

	Stock getStockFromSymbol(String theId);

	void addStockToPortfolio(String stockSymbol, int portfolioId);

	void deleteStockFromPortfolio(int stockportfolioId);

	Stock getStock(int stockId);

	List<Stock> getStocks();

	void addStockToPortfolioFromId(int stockId, int portfolioId);

		
}
