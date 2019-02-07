package com.andersen.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	@JsonIgnoreProperties(ignoreUnknown=true)
	public class HistoricalQuote {

		private List<HistoricalQuoteDetail> stock_prices;

		public HistoricalQuote() {

		}

		public List<HistoricalQuoteDetail> getStock_prices() {
			return stock_prices;
		}

		public void setStock_prices(List<HistoricalQuoteDetail> stock_prices) {
			this.stock_prices = stock_prices;
		}

		@Override
		public String toString() {
			return "HistoricalQuoteManager [stock_prices=" + stock_prices + "]";
		}
	
	}

