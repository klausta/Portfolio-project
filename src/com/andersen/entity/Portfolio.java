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
import javax.validation.constraints.Size;

@Entity
@Table(name = "portfolio")
public class Portfolio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "portfolio")
	private int portfolioId;

	@Size(min=3, max=45)
	@Column(name = "name")
	private String name;

	@Size(min=3, max = 45)
	@Column(name = "description")
	private String description;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "users_username")
	private User user;
	
	@OneToMany(mappedBy="portfolio", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<Stockportfolio> stockportfolio;

	public void addStockportfolio(Stockportfolio theStockportfolio) {
		if (stockportfolio == null) {
			stockportfolio = new HashSet<Stockportfolio>();
		}

		stockportfolio.add(theStockportfolio);
	}

	public void removeStockportfolio(Stockportfolio theStockportfolio) {

		stockportfolio.remove(theStockportfolio);
	}
	
	public Portfolio() {

	}
	
	public Portfolio(int portfolioId, @Size(min = 3, max = 45) String name,
			@Size(min = 3, max = 45) String description) {
		this.portfolioId = portfolioId;
		this.name = name;
		this.description = description;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Stockportfolio> getStockportfolio() {
		return stockportfolio;
	}

	public void setStockportfolio(Set<Stockportfolio> stockportfolio) {
		this.stockportfolio = stockportfolio;
	}

	@Override
	public String toString() {
		return "Portfolio [portfolioId=" + portfolioId + ", name=" + name + ", description=" + description + ", user="
				+ user + "]";
	}


}
