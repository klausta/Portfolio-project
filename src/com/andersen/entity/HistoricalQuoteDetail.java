package com.andersen.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoricalQuoteDetail {

	@JsonProperty("close")
	private Float endOfDayQuotePrice;
	
	@JsonProperty("date")
	private String dateOfQuote;
	
	
	public Float getClose() {
		return endOfDayQuotePrice;
	}

	public void setClose(Float endOfDayQuotePrice) {
		this.endOfDayQuotePrice = endOfDayQuotePrice;
	}

	public String getDate() {
		return dateOfQuote;
	}

	public void setDate(String dateOfQuote) {
		this.dateOfQuote = dateOfQuote;
	}

	public HistoricalQuoteDetail()  {
		
	}
	
	@Override
	public String toString() {
		return "HistoricalQuoteDetail [endOfDayQuotePrice=" + endOfDayQuotePrice + ", dateOfQuote=" + dateOfQuote + "]";
	}
}
