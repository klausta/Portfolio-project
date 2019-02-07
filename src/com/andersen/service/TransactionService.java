package com.andersen.service;


import java.util.List;

import com.andersen.entity.Transaction;

public interface TransactionService {

	void addTransaction(Transaction transaction);

	List<Transaction> getTransactionsFromStockPortfolioId(int stockportfolioId);

	void deleteTransaction(int transactionId);

}
