package com.andersen.entity;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="stock")
public class Stock {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="stock")
	private int stockId;
	
	@Column(name="symbol")
	@Size(min=3, max=5)
	private String ticker;
	
	@Column(name="name")
	private String name;
	
	@Column(name="currency")
	private String currency;
	
	@Column(name="sector")
	private String sector;
	
	@Column(name="stockexchange")
	private String stockExchange;
	
	public Stock() {
	}
	
	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getSymbol() {
		return ticker;
	}

	public void setSymbol(String symbol) {
		this.ticker = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getStockExchange() {
		return stockExchange;
	}

	public void setStockExchange(String stockExchange) {
		this.stockExchange = stockExchange;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", ticker=" + ticker + ", name=" + name + ", currency=" + currency
				+ ", sector=" + sector + ", stockExchange=" + stockExchange + "]";
	}

	
}
