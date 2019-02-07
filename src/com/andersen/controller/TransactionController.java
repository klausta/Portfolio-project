package com.andersen.controller;

import java.util.Calendar;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.andersen.entity.Stockportfolio;
import com.andersen.entity.Transaction;
import com.andersen.service.PortfolioService;
import com.andersen.service.StockService;
import com.andersen.service.TransactionService;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private StockService stockService;

	@Autowired
	private PortfolioService portfolioService;

	@RequestMapping("/list")
	public String getTransactions(
			@RequestParam("stockportfolioId") int stockportfolioId,
			Model model) {

		List<Transaction> transactionList = transactionService.getTransactionsFromStockPortfolioId(stockportfolioId);
			
		model.addAttribute("listTransactions", transactionList);
	
		return "transaction/list-transactions";
	}
	

	@RequestMapping("/newtransactionform")
	public String newTransaction(@RequestParam("stockportfolioId") int stockportfolioId,
								 Model model) {

		Stockportfolio stockportfolio = portfolioService.getStockportfolio(stockportfolioId);
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		Transaction transaction = new Transaction(stockportfolio, date);

		model.addAttribute("transaction", transaction);
		
		return "transaction/newtransaction-form";
	}


	@RequestMapping("/addtransaction")
	public ModelAndView addTransaction(@Valid @ModelAttribute("transaction") Transaction transaction, 
			BindingResult theBindingResult,
			ModelMap model) {

		if (theBindingResult.hasErrors()) {
			return new ModelAndView("transaction/newtransaction-form");
		} 
		else {
		
			transactionService.addTransaction(transaction);
			
			model.addAttribute("stockportfolioId", transaction.getStockportfolio().getStockportfolioId());
						
			return new ModelAndView("redirect:/transactions/list", model);
		}
	}

	@RequestMapping("deletetransaction")
	public ModelAndView deleteTransaction(@RequestParam("transactionId") int transactionId,
						     				@RequestParam("stockportfolioId") int stockportfolioId,	
											ModelMap model)  {
		
		transactionService.deleteTransaction(transactionId);
		
		model.addAttribute("stockportfolioId", stockportfolioId);		
		
		return new ModelAndView("redirect:/transactions/list", model);
	}
}
