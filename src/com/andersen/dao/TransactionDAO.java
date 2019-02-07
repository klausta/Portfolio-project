package com.andersen.dao;

import java.util.List;

import com.andersen.entity.Stockportfolio;
import com.andersen.entity.Transaction;

public interface TransactionDAO extends GenericDAO<Transaction> {

	List<Object[]> getCostAndQuantity(Stockportfolio stockportfolio);
	
}
