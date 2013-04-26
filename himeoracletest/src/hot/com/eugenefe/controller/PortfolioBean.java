package com.eugenefe.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.session.PortfolioList;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

@Name("portfolioBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioBean implements Serializable {

	@Logger
	private Log log;
	//
	@In(create = true)
	private PortfolioList portfolioList;
	
	private Portfolio selectedPort;
	
	private Portfolio portfolio;

	private List<Portfolio> portfolios = new ArrayList<Portfolio>();
	private List<Portfolio> subPortfolios = new ArrayList<Portfolio>();
	// private List<Portfolio> portfolios;
	private List<Portfolio> filterPorts;

	public PortfolioBean() {
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	public List<Portfolio> getPortfolios() {
//		if (this.portfolios.size() == 0) {
//			portfolios = portfolioList.getResultList();
//			log.info("Call Portfolio Result from DB : #0", portfolios.size());
//		}
		log.info("After Call Portfolio Result from DB : #0", portfolios.size());
		return portfolios;
	}

	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	public List<Portfolio> getFilterPorts() {
		// if (this.filteredPorts.size() == 0) {
		// filteredPorts = portfolioList.getResultList();
		// Portfolio temp = new Portfolio("test", "Test");
		// filteredPorts.add(temp);
		// log.info("Call filtered Result from DB : #0", filterPorts.size());
		// }
		// log.info("After Call get Filtered List : #0", filteredPorts.size());
		return filterPorts;

	}

	public void setFilterPorts(List<Portfolio> filterPorts) {
		this.filterPorts = filterPorts;
		// log.info("After Call Set Filtered List : #0", filteredPorts.size());
	}

	public Portfolio getSelectedPort() {
		return selectedPort;
	}

	public void setSelectedPort(Portfolio selectedPort) {
		this.selectedPort = selectedPort;
	}

	
	
	public List<Portfolio> getSubPortfolios() {
		return subPortfolios;
	}

	public void setSubPortfolios(List<Portfolio> subPortfolios) {
		this.subPortfolios = subPortfolios;
	}

	@Create
	public void init(){
		portfolios = portfolioList.getResultList();
		for( Portfolio aa : portfolios){
			subPortfolios.add(aa);
		}
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Car Selected", ((Portfolio) event.getObject()).getPortId());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Car Unselected", ((Portfolio) event.getObject()).getPortId());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	//--------------------------------EVENT Method------------------------
		public void onNodeSelect(NodeSelectEvent event){
			log.info("Call Node Select Event in Portfolio Bean: #0", ((IPortfolio)event.getTreeNode().getData()).getStringId());
			String compId = ((IPortfolio)event.getTreeNode().getData()).getStringId();
			subPortfolios.clear();
			int cnt=0;
//			for(Portfolio aa : portfolioList.getResultList()){
				for(Portfolio aa : portfolios){	
				cnt=cnt+1;
				if(aa.getParentPortfolio()!=null 
						&& aa.getParentPortfolio().getPortId().equals(compId)){
					subPortfolios.add(aa);
				}
			}
			log.info("Event on Portfolio Bean:#0", cnt);
		}	
}
