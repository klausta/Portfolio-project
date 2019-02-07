package com.andersen.dao;

import com.andersen.entity.Stock;

public interface StockDAO extends GenericDAO<Stock> {

	Stock getStockFromSymbol(String theId);

}
