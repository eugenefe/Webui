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
import org.jboss.seam.annotations.Out;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.session.PortfolioList;

@Name("portfolioTableBean")
public class PortfolioTableBean implements Serializable {
	@Logger
	private Log log;

	@In(create = true)
	private PortfolioList portfolioList;
	
//	@In(required= false)
	List<Portfolio> fullPort = new ArrayList<Portfolio>();

	@Out(required=false)
	@In(create=true , required=false)
	private Portfolio selectedPortfolio;
	
	@Out
	List<Portfolio> subPorts = new ArrayList<Portfolio>();
	
	@Out
	List<Portfolio> portfolios = new ArrayList<Portfolio>();
	
	public PortfolioTableBean() {
//		System.out.println("In the Creation CTRL");
	}
	
	@Create
	public void init(){
		fullPort = portfolioList.getResultList();
//		portfolios = portfolioList.getResultList();
		log.info("Call after creation : #0" , fullPort.size());
	}
	
	@Factory( value="portfolios", scope=ScopeType.EVENT )
	public void loadFullPort(){
		if( selectedPortfolio==null){
			portfolios = portfolioList.getResultList();
		}
		else {
			for(Portfolio aa: portfolioList.getResultList()){
				if(aa.getParentPortfolio()!= null 
						&& selectedPortfolio.getPortId().equals(aa.getParentPortfolio().getPortId())){
					portfolios.add(aa);
				}
			}
		}
	}
//	@Factory( value="selectedPortfolio", scope=ScopeType.CONVERSATION )
//	public void createRootPortfolio(){
//		if(selectedPortfolio ==null){
//			selectedPortfolio = new Portfolio("root", "Root");
//		}
//	}
	
//	--------------------------Evnet Listener--------------------------------------------
	public void onNodeSelect(NodeSelectEvent event){
		log.info("Call Node Select Event: #0", ((IPortfolio)event.getTreeNode().getData()).getStringId());
		selectedPortfolio = (Portfolio)event.getTreeNode().getData();
		

	}
	// ----------------------------- helper method----------------------------------------

	public void getSubPortfolios(Portfolio parentPort, List<Portfolio> port) {
		subPorts.clear();
		for (Portfolio k : port) {
			if (k.getParentPortfolio() != null 
					&& k.getParentPortfolio().getPortId().equals(parentPort.getPortId())) {
				subPorts.add(k);
			}
		}
		
	}
	
	@Factory("subPorts" )
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
