package com.eugenefe.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.False;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.TreeNode;

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.session.PortfolioList;

@Name("portfolioTableBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioTableBean implements Serializable {
	@Logger
	private Log log;

	private Portfolio parentPortfolio;
	private Portfolio selectedPortfolio;
	
	private List<Portfolio> portfolios;
	private List<Portfolio> filterPorts ;

	private String searchString;
	
	public PortfolioTableBean() {
		 System.out.println("PortfolioTableBean Constructor" + this.getClass());
	}
	// ------------------------Getter and Setter----------------------------
	public Portfolio getParentPortfolio() {
		return parentPortfolio;
	}
	public void setParentPortfolio(Portfolio parentPortfolio) {
		this.parentPortfolio = parentPortfolio;
	}

	public Portfolio getSelectedPortfolio() {
		return selectedPortfolio;
	}
	public void setSelectedPortfolio(Portfolio selectedPortfolio) {
		this.selectedPortfolio = selectedPortfolio;
	}
	
	public List<Portfolio> getPortfolios() {
		return portfolios;
	}
	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public List<Portfolio> getFilterPorts() {
		return filterPorts;
	}
	public void setFilterPorts(List<Portfolio> filterPorts) {
		this.filterPorts = filterPorts;
	}

	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	// ---------------------End of Getter and Setter--------------------
	@Observer("initPortfolioTree")
	public void init(TreeNode portRoot, List<Portfolio> sub){
		log.info("Observer initPortfolioTree In Table0");
		searchString = null;
		filterPorts =null;
		this.portfolios = sub;
		log.info("Observer initPortfolioTree In Table1");
		
	}

	@Observer("selectTreeNode")
	public void init( Portfolio parentPortfolio){
		log.info("Observer selectTreeNode In Table0");
		this.portfolios = parentPortfolio.getChildPortfolios();
		log.info("Observer selectTreeNode In Table1 :#0,#1 ", parentPortfolio.getClass());
		
	}

	// -----------------Evnet Listener---------------------------------


	// ----------------------------- helper method----------------


}
