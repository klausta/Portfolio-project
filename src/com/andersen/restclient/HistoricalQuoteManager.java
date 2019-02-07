package com.andersen.restclient;

import org.springframework.web.client.RestTemplate;


import com.andersen.entity.HistoricalQuote;

public class HistoricalQuoteManager {

	public HistoricalQuote getHistoricalQuote(String stockId)  {
		
		String apiKey = "OjJiOTZiNzlhMDVmNGVkOWY4ZDdiOWRjZTg5NTgwYzVi";
		String stock = "AAPL";
		String url = "https://api-v2.intrinio.com/securities/"+stockId+"/prices?api_key="+apiKey;
		
		RestTemplate restTemplate = new RestTemplate();
		
		HistoricalQuote historicalQuote = restTemplate.getForObject(url, HistoricalQuote.class);
		
		System.out.println(historicalQuote);	
		
		return historicalQuote;
		
	}
	
}
