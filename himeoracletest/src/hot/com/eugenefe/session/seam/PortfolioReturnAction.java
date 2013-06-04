package com.eugenefe.session.seam;

import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.log.Log;

import com.eugenefe.entity.Portfolio;

@Name("portfolioReturnAction")
public class PortfolioReturnAction {
	@Logger
	private Log log;
	
	@In(value="selectedPortfolio")
	@Out
	private Portfolio parentPortflio;
	
//	@In
//	private List<Portfolio> fullPorts;
	
	@Out(scope=ScopeType.CONVERSATION)
	private List<Portfolio> ports;
	
	
	@Factory(value="ports")
	public void init(){
		ports= parentPortflio.getChildPortfolios();
	}
	
	@Observer(value="selectTreeNodeOn")
	public void init( Portfolio pPort){
		ports= pPort.getChildPortfolios();
		log.info("In the ports : #0, #1" , ports.size(), pPort.getPortId());
	}
	
	
}
