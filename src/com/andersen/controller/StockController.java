package com.andersen.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.andersen.entity.Portfolio;
import com.andersen.entity.Stock;
import com.andersen.entity.Stockportfolio;
import com.andersen.service.PortfolioService;
import com.andersen.service.StockService;

@Controller
@RequestMapping("/stock")
public class StockController {

	@Autowired
	public StockService stockService;

	@Autowired
	private PortfolioService portfolioService;

	@RequestMapping("/list")
	public String getStocks(@RequestParam("portfolioId") int portfolioId, 
				            Model model) {

		List<Stock> stockList = stockService.getStocks();

		model.addAttribute("stockList", stockList);
		model.addAttribute("portfolioId", portfolioId);

		return "/stocks/list-stocks";
	}

	@RequestMapping("/addStockToPortfolio")
	public ModelAndView addStockToPortfolio(@RequestParam("symbol") String symbol,
									  @RequestParam("portfolioId") int portfolioId, 
									  ModelMap model) 
									  throws IndexOutOfBoundsException {

		stockService.addStockToPortfolio(symbol, portfolioId);

		model.addAttribute("portfolioId", portfolioId);
			
		return new ModelAndView("redirect:/portfolio/listPortfolioStocks", model);
	}

	// TODO find out if this one is needed at all
	@ExceptionHandler({java.lang.IndexOutOfBoundsException.class })
	public ModelAndView HandleExcpetion(Exception ex) {
		ModelAndView model = new ModelAndView("exception");

		model.addObject("exception", ex.getMessage());
		model.addObject("info", "The stock symbol does not exist. Go to 'select from list' for all available options.");

		return model;
	}

	@ExceptionHandler({java.sql.SQLIntegrityConstraintViolationException.class})
	public ModelAndView HandleConstraintViolationException(Exception ex)  {
		ModelAndView model = new ModelAndView("exception");
		
		model.addObject("exception", ex.getMessage());
		model.addObject("info", "the chosen stock has already been inserted in the portfolio");
		
		return model;
	}
	
	@ExceptionHandler({javax.persistence.NoResultException.class})
	public ModelAndView HandleNoResultException(Exception ex)  {
		ModelAndView model = new ModelAndView("exception");
		
		model.addObject("exception", ex.getMessage());
		model.addObject("info", "The stock symbol does not exist. Go to 'select from list' for all available options.");
		
		return model;
	}
	
	
	@RequestMapping("/addStockToPortfolioFromId")
	public ModelAndView addStockToPortfolioFromId(@RequestParam("stockId") int stockId,
										        @RequestParam("portfolioId") int portfolioId, 
											    ModelMap model) {

		stockService.addStockToPortfolioFromId(stockId, portfolioId);

		model.addAttribute("portfolioId", portfolioId);
		
		return new ModelAndView("redirect:/portfolio/listPortfolioStocks", model);
	}

	@RequestMapping("/deleteStockFromPortfolio")
	public ModelAndView deleteStockFromPortfolio(@RequestParam("stockportfolioId") int stockportfolioId,
										   @RequestParam("portfolioId") int portfolioId, 
										   ModelMap model) {

		stockService.deleteStockFromPortfolio(stockportfolioId);

		model.addAttribute("portfolioId", portfolioId);
		
		return new ModelAndView("redirect:/portfolio/listPortfolioStocks", model);
	}

}
