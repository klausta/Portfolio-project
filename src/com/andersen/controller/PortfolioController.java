package com.andersen.controller;

import java.util.List;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andersen.entity.Portfolio;
import com.andersen.entity.Stock;
import com.andersen.entity.Stockportfolio;
import com.andersen.entity.User;
import com.andersen.service.PortfolioService;

@Controller
@RequestMapping("/portfolio")
public class PortfolioController {

	@Autowired
	private PortfolioService portfolioService;

	@Autowired
	private SecurityController securityController;

	@RequestMapping("/list")
	public String listPortfolio(Model model) {

		List<Portfolio> portfolioList = portfolioService.getPortfolios();
		model.addAttribute("portfolios", portfolioList);
		
		return "portfolio/list-portfolios";
	}

	
	@RequestMapping("/showFormToAddPortfolio")
	public String showFormToAddPortfolio(Model model) {

		model.addAttribute(new Portfolio());

		return "portfolio/addportfolio-form";
	}

	
	@RequestMapping("/showPrepopulatedFormToAddPortfolio")
	public String showPrepopulatedFormToAddPortfolio(@RequestParam("description") String description,
													 @RequestParam("name") String name, 
												     @RequestParam("portfolioId") int portfolioId, 
													 Model model) {

		model.addAttribute(new Portfolio(portfolioId, name, description));

		return "portfolio/updateportfolio-form";
	}

	
	@RequestMapping("/addPortfolio")
	public String addPortfolio(@Valid @ModelAttribute("portfolio") Portfolio portfolio, 
			                   BindingResult theBindingResult,
			                   Model model) {

		if (theBindingResult.hasErrors()) {
			return "addportfolio-form";
		} 
		else {
			portfolioService.addPortfolio(portfolio, securityController.getCurrentUser());
			
			return "redirect:/portfolio/list";
		}
	}
	
	
	@RequestMapping("/updatePortfolio")
	public String updatePortfolio(@Valid @ModelAttribute("portfolio") Portfolio portfolio, 
			                   BindingResult theBindingResult,
			                   Model model) {

		if (theBindingResult.hasErrors()) {
			return "updateportfolio-form";
		} 
		else {
			portfolioService.updatePortfolio(portfolio, securityController.getCurrentUser());
			return "redirect:/portfolio/list";
		}
	}


	@RequestMapping("/deletePortfolio")
	public String deletePortfolio(@RequestParam("portfolioId") int portfolioId, 
								  Model model) {

		portfolioService.deletePortfolio(portfolioId);

		return "redirect:/portfolio/list";
	}


	@RequestMapping("/listPortfolioStocks")
	public String listPortfolioStocks(@RequestParam("portfolioId") int portfolioId, 
									  Model model) {

		Portfolio portfolio = portfolioService.getPortfolio(portfolioId);
		Set<Stockportfolio> stockportfolioSet = portfolio.getStockportfolio();

		model.addAttribute("stock", new Stock());
		model.addAttribute("portfolio", portfolio);
		model.addAttribute("portfolioStocks", stockportfolioSet);
		
		return "portfolio/list-portfoliostock";
	}

}
