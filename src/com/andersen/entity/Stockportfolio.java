package com.andersen.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "stock_portfolio")
public class Stockportfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stockportfolio")
	private int stockportfolioId;

	@ManyToOne (fetch = FetchType.EAGER)  
	@JoinColumn(name = "portfolio_portfolio")
	private Portfolio portfolio;

	@ManyToOne(fetch = FetchType.EAGER) //TODO change this relation to lazy - many stock entities and many use cases where its not needed to fetch associations.
	@JoinColumn(name = "stock_stock")
	private Stock stock;

	@OneToMany(mappedBy = "stockportfolio", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transaction;
	
	//Transient variables below are for temporarily calculated amounts historical transactions
	
	@Transient
	private long quantity;

	@Transient
	private double netAmount;

	@Transient
	private double transactionFee;
	
	@Transient
	private double currencyExchangeFee;
	
	@Transient
	private double grossAmount;
	
	@Transient
	private double avgUnitCost;
	
	@Transient
	private double price;
	
	@Transient
	private double profitLoss;
	
	@Transient
	private double profitLossPercentage;
	
	public Stockportfolio() {

	}

	public Stockportfolio(Portfolio portfolio, Stock stock) {
		this.portfolio = portfolio;
		this.stock = stock;
	}
	
	public void addTransaction(Transaction tempTransaction) {

		if (transaction == null) {
			transaction = new ArrayList<Transaction>();
		}
		transaction.add(tempTransaction);
	}
	
	public double getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss() {
		profitLoss = getGrossAmount() - (getQuantity() * getPrice());
	}

	public double getProfitLossPercentage() {
		return profitLossPercentage;
	}

	public void setProfitLossPercentage() {
		profitLossPercentage = getProfitLoss() / getGrossAmount();
	}

	public double getAvgUnitCost() {
		return avgUnitCost;
	}

	public void setAvgUnitCost() {
		avgUnitCost = getGrossAmount() / getQuantity();
	}

	public double getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(double transactionFee) {
		this.transactionFee = transactionFee;
	}

	public double getCurrencyExchangeFee() {
		return currencyExchangeFee;
	}

	public void setCurrencyExchangeFee(double currencyExchangeFee) {
		this.currencyExchangeFee = currencyExchangeFee;
	}

	public double getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(double price2) {
		this.price = price2;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long qty) {
		this.quantity = qty;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

	public int getStockportfolioId() {
		return stockportfolioId;
	}

	public void setStockportfolioId(int stockportfolioId) {
		this.stockportfolioId = stockportfolioId;
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Stockportfolio [stockportfolioId=" + stockportfolioId + ", portfolio=" + portfolio + ", stock=" + stock
				+ ", transaction=" + transaction + ", quantity=" + quantity + ", netAmount=" + netAmount
				+ ", transactionFee=" + transactionFee + ", currencyExchangeFee=" + currencyExchangeFee
				+ ", grossAmount=" + grossAmount + ", avgUnitCost=" + avgUnitCost + ", price=" + price + ", profitLoss="
				+ profitLoss + ", profitLossPercentage=" + profitLossPercentage + "]";
	}
}
