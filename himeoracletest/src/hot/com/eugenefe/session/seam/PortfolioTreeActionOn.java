package com.eugenefe.session.seam;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.entity.PortfolioReturn;

@Name("portfolioTreeActionOn")
//@Scope(ScopeType.CONVERSATION)
public class PortfolioTreeActionOn {
	
	
	@Logger
	private Log log;

	private TreeNode selectedNode;
	
	@In
	@Out(scope=ScopeType.CONVERSATION)
	private Portfolio selectedPortfolio;


	public PortfolioTreeActionOn() {
	}


	

	public TreeNode getSelectedNode() {
		log.info("getSelectedNode");
		return selectedNode;
	}


	public void setSelectedNode(TreeNode selectedNode) {
		log.info("setSelectedNode");
		this.selectedNode = selectedNode;
	}


	public Portfolio getSelectedPortfolio() {
		log.info("getSelectedPortfolio");
		return selectedPortfolio;
	}


	public void setSelectedPortfolio(Portfolio selectedPortfolio) {
		log.info("setSelectedPortfolio");
		this.selectedPortfolio = selectedPortfolio;
	}

	// ----------------------Ajax Event Listener------------------
	public void onNodeSelect(NodeSelectEvent event) {
		selectedPortfolio = (Portfolio) selectedNode.getData();
		log.info("Call Node Select Event1: #0, #1, #2", selectedPortfolio.getPortId());
//		subPortfolios = selectedPortfolio.getChildPortfolios();
		Events.instance().raiseEvent("selectTreeNodeOn", selectedPortfolio);
		log.info("Call Node Select Event2: #0, #1, #2", selectedPortfolio.getPortName());
//		log.info("Call Node Select Event: #0, #1, #2", selectedPortfolio.getPortId());
//		log.info("Call Node Select Event: #0, #1, #2", selectedPortfolio.getPortId(), subPortfolios.size());
	}

}
