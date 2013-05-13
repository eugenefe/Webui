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

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.session.PortfolioList;

@Name("portfolioTableBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioTableBean implements Serializable {
	@Logger
	private Log log;

	@In(create = true)
	private PortfolioList portfolioList;

//	 @In(required= false)
//	 @Out
	List<Portfolio> fullPort = new ArrayList<Portfolio>();

//	@Out(required = false)
	@In(required = false)
	private Portfolio selectedPortfolio;

	@Out
	List<Portfolio> subPorts = new ArrayList<Portfolio>();

	@Out
	List<Portfolio> portfolios = new ArrayList<Portfolio>();

	List<Portfolio> filterPorts ;

	public PortfolioTableBean() {
		// System.out.println("In the Creation CTRL");
	}

	// ------------------------Getter and Setter----------------------------

	public Portfolio getSelectedPortfolio() {
		return selectedPortfolio;
	}

	public void setSelectedPortfolio(Portfolio selectedPortfolio) {
		this.selectedPortfolio = selectedPortfolio;
	}
	

	public List<Portfolio> getPortfolios() {
//		if(selectedPortfolio == null){
//			portfolios = fullPort;
//			log.info("In the Get Portfolios at null selected ");
//		}
		log.info("In the Get Portfolios TableBean : #0", portfolios.size());
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

	public List<Portfolio> getFullPort() {
		return fullPort;
	}

	public void setFullPort(List<Portfolio> fullPort) {
		this.fullPort = fullPort;
	}

	// ---------------------End of Getter and Setter--------------------
	@Create
	public void init() {
		fullPort = portfolioList.getResultList();
//		 portfolios = portfolioList.getResultList();
//		 filterPorts = portfolioList.getResultList();
		log.info("Call after creation : #0", fullPort.size());
	}
	
	@Observer("changeTree")
	public void onChangeTree(Portfolio selectedPortfolio){
		portfolios = selectedPortfolio.getChildPortfolios();
	}

	@Factory(value = "portfolios" )
	public void loadFullPort() {
		if (selectedPortfolio == null) {
			portfolios = portfolioList.getResultList();
		} else {
			for (Portfolio aa : portfolioList.getResultList()) {
				if (aa.getParentPortfolio() != null
						&& selectedPortfolio.getPortId().equals(aa.getParentPortfolio().getPortId())) {
					portfolios.add(aa);
				}
			}
		}
	}

	// @Factory( value="selectedPortfolio", scope=ScopeType.CONVERSATION )
	// public void createRootPortfolio(){
	// if(selectedPortfolio ==null){
	// selectedPortfolio = new Portfolio("root", "Root");
	// }
	// }

	// -----------------Evnet Listener---------------------------------
	public void onNodeSelect(NodeSelectEvent event) {
		log.info("Call Node Select Event: #0,#1", 
					((IPortfolio) event.getTreeNode().getData()).getStringId(), selectedPortfolio.getPortId());
//		selectedPortfolio = (Portfolio) event.getTreeNode().getData();
//		List<Portfolio> temp  = new ArrayList<Portfolio>();
//		for(Portfolio aa : fullPort){
//			if(aa.getParentPortfolio()!=null 
//					&& aa.getParentPortfolio().getPortId().equals(selectedPortfolio.getPortId())){
//				temp.add(aa);
//				log.info("IN the Add in ");
//			}
//		}
//		portfolios = temp;
//		filterPorts  = temp;
		
		portfolios = selectedPortfolio.getChildPortfolios();
		filterPorts = selectedPortfolio.getChildPortfolios();
		log.info("Size of Portfolios : #0 #1 " , portfolios.size(), fullPort.size());
	}

	// ----------------------------- helper method----------------

	public void getSubPortfolios(Portfolio parentPort, List<Portfolio> port) {
		subPorts.clear();
		for (Portfolio k : port) {
			if (k.getParentPortfolio() != null && k.getParentPortfolio().getPortId().equals(parentPort.getPortId())) {
				subPorts.add(k);
			}
		}

	}

	@Factory("subPorts")
	private void getSubPortfolios() {
		subPorts.clear();
		for (Portfolio k : fullPort) {
			if (k.getParentPortfolio() != null
					&& k.getParentPortfolio().getPortId().equals(selectedPortfolio.getPortId())) {
				subPorts.add(k);
			}
		}

	}
}
