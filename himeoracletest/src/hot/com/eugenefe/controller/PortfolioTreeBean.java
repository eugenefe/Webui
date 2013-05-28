package com.eugenefe.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.entity.PortfolioReturn;
import com.eugenefe.session.PortfolioList;
import com.eugenefe.session.PortfolioReturnBssdList;
import com.eugenefe.session.PortfolioReturnList;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

@Name("portfolioTreeBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioTreeBean {
	@Logger
	private Log log;

	private TreeNode portfolioRoot;
	private TreeNode selectedNode;
	private Portfolio selectedPortfolio;

	private Boolean userDefined;
	private String activeAccordionTab;

	public PortfolioTreeBean() {
	}

	// --------------------------------Getter and Setter ---------------

	
	public TreeNode getPortfolioRoot() {
		return portfolioRoot;
	}

	public void setPortfolioRoot(TreeNode portfolioRoot) {
		this.portfolioRoot = portfolioRoot;
	}
	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public Portfolio getSelectedPortfolio() {
		return selectedPortfolio;
	}

	public void setSelectedPortfolio(Portfolio selectedPortfolio) {
		this.selectedPortfolio = selectedPortfolio;
	}
	
	public Boolean getUserDefined() {
		return userDefined;
	}

	public void setUserDefined(Boolean userDefined) {
		this.userDefined = userDefined;
	}
	public String getActiveAccordionTab() {
		return activeAccordionTab;
	}

	public void setActiveAccordionTab(String activeAccordionTab) {
		this.activeAccordionTab = activeAccordionTab;
	}
	
	// ----------------------Ajax Event Listener------------------

	public void onNodeSelect(NodeSelectEvent event) {
		selectedPortfolio = (Portfolio) selectedNode.getData();
//		subPortfolios = selectedPortfolio.getChildPortfolios();
		Events.instance().raiseEvent("selectTreeNode", selectedPortfolio);
		log.info("Call Node Select Event: #0, #1, #2");
//		log.info("Call Node Select Event: #0, #1, #2", selectedPortfolio.getPortId());
//		log.info("Call Node Select Event: #0, #1, #2", selectedPortfolio.getPortId(), subPortfolios.size());
	}

	public void onTabChange(TabChangeEvent event) {
		activeAccordionTab = event.getTab().getId();
		log.info("Active Accodion Tab : #0", activeAccordionTab);
		if (activeAccordionTab.equals("userDefined")) {
			userDefined = true;
		} else {
			userDefined = false;
		}

		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	// --------------------------Action Listener--------------------
	@Observer("initPortfolioTree")
	public void init(TreeNode portRoot, List<Portfolio> sub){
		log.info("Observer initPortfolioTree0");
		this.portfolioRoot = portRoot;
		
		if (portfolioRoot.getChildCount() != 0) {
			portfolioRoot.getChildren().get(0).setSelected(true);
			selectedPortfolio = (Portfolio) (portfolioRoot.getChildren().get(0).getData());
		}
		log.info("Observer initPortfolioTree1");
		
	}
	
	public void expandAll() {
		recursiveExpand(portfolioRoot.getChildren(), true);
	}

	public void collapseAll() {
		recursiveExpand(portfolioRoot.getChildren(), false);
	}

	// ----------------------------- helper method-------------------------

	private void recursiveExpand(List<TreeNode> node, boolean isExpand) {
		for (TreeNode aa : node) {
			aa.setExpanded(isExpand);
			recursiveExpand(aa.getChildren(), isExpand);
		}
	}
}
