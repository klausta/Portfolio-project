package com.andersen.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andersen.entity.Portfolio;
import com.andersen.entity.Stock;
import com.andersen.entity.Stockportfolio;
import com.andersen.service.PortfolioService;

@Controller
@RequestMapping("/reporting")
public class ReportingController {

	@Autowired
	private PortfolioService portfolioService;

	@RequestMapping("/showMenu")
	public String showMenu() {

		return "/reporting/index-reporting";
	}

	@RequestMapping("/selectportfolio")
	public String selectPortortfolio(Model model) {

		List<Portfolio> portfolioList = portfolioService.getPortfolios();
		model.addAttribute("portfolios", portfolioList);

		return "/reporting/list-portfolios";
	}

	@RequestMapping("/viewportfolioreport")
	public String viewPortfolioReport(
			Model model, 
			@RequestParam("portfolioId") int portfolioId) {

		Portfolio portfolio = portfolioService.getPortfolioJoinFetchTransactions(portfolioId); //TODO- transactions don't need to be (lazy) loaded here
		Set<Stockportfolio> stockportfolioSet = portfolio.getStockportfolio();
		portfolioService.addCalculatedValues(stockportfolioSet);
		
		model.addAttribute("portfolioStocks", stockportfolioSet);

		return "/reporting/list-portfoliostock";
	}
}
