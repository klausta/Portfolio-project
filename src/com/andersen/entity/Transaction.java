package com.andersen.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction")
	private int transactionId;

	@Size(min = 3, message = "Transaction type must be chosen")
	@Column(name = "transactiontype")
	private String transactiontype;

	@DecimalMin(value = "0", inclusive = false)
	@Digits(integer = 5, fraction = 0)
	@Column(name = "quantity")
	private int quantity;

	@DecimalMin(value = "0", inclusive = false)
	@Column(name = "unitprice")
	private float unitPrice;

	@Column(name = "grossAmount")
	private float grossAmount;

	@Column(name = "transactionfee")
	private float transactionFee;

	@Column(name = "currencyexchangefee")
	private float currencyExchangeFee;

	@Column(name = "netamount")
	private float netAmount;

	@Size(min = 3, message = "Currency must be chosen")
	@Column(name = "currency")
	private String currency;

	@Column(name = "transactiondate")
	private Date transactionDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "stock_portfolio_stockportfolio")
	private Stockportfolio stockportfolio;

	public Transaction() {

	}
	
	public Transaction(Stockportfolio stockportfolio, Date transactionDate) {
		this.stockportfolio = stockportfolio;
		this.transactionDate = transactionDate;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactiontype() {
		return transactiontype;
	}

	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public float getGrossAmount() {
		return grossAmount;
	}

	public void setGrossAmount() {
		this.grossAmount = getNetAmount() + getCurrencyExchangeFee() + getTransactionFee();
	}

	public float getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(float transactionFee) {
		this.transactionFee = transactionFee;
	}

	public float getCurrencyExchangeFee() {
		return currencyExchangeFee;
	}

	public void setCurrencyExchangeFee(float currencyExchangeFee) {
		this.currencyExchangeFee = currencyExchangeFee;
	}

	public float getNetAmount() {
		return netAmount;
	}

	public void setNetAmount() {
		this.netAmount = getQuantity() * getUnitPrice();
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Stockportfolio getStockportfolio() {
		return stockportfolio;
	}

	public void setStockportfolio(Stockportfolio stockportfolio) {
		this.stockportfolio = stockportfolio;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactiontype=" + transactiontype + ", quantity="
				+ quantity + ", unitPrice=" + unitPrice + ", grossAmount=" + grossAmount + ", transactionFee="
				+ transactionFee + ", currencyExchangeFee=" + currencyExchangeFee + ", netAmount=" + netAmount
				+ ", currency=" + currency + ", transactionDate=" + transactionDate + ", stockportfolio="
				+ stockportfolio + "]";
	}

}
