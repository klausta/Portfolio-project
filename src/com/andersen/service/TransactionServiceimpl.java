package com.andersen.service;

import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersen.dao.GenericDAO;
import com.andersen.dao.PortfolioDAO;
import com.andersen.dao.StockDAO;
import com.andersen.dao.StockportfolioDAO;
import com.andersen.dao.TransactionDAO;
import com.andersen.entity.Portfolio;
import com.andersen.entity.Stock;
import com.andersen.entity.Stockportfolio;
import com.andersen.entity.Transaction;

@Service
public class TransactionServiceimpl implements TransactionService {

	@Autowired
	private TransactionDAO transactionDAO;

	@Autowired
	private StockDAO stockDAO;

	@Autowired
	private PortfolioDAO portfolioDAO;
	
	@Autowired
	private StockportfolioDAO stockportfolioDAO;
	
	@PostConstruct
	public void setType()  {
		transactionDAO.setType(Transaction.class);
		stockportfolioDAO.setType(Stockportfolio.class);
	}
	
	@Override
	@Transactional
	public List<Transaction> getTransactionsFromStockPortfolioId(int stockportfolioId) {

		Stockportfolio stockportfolio = stockportfolioDAO.readById(stockportfolioId);
 		List<Transaction> transactionList = stockportfolio.getTransaction();
 		Hibernate.initialize(transactionList);
 		
		return transactionList;
	}
	
	@Override
	@Transactional
	public void addTransaction(Transaction transaction) {

		transaction.setNetAmount();
		transaction.setGrossAmount();
		
		transactionDAO.create(transaction);
	}

	@Override
	@Transactional
	public void deleteTransaction(int transactionId) {
		
		transactionDAO.deleteById(transactionId);
		
	}
	
}
