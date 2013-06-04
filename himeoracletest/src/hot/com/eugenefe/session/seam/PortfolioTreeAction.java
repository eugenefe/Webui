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

@Name("portfolioTreeAction")
//@Scope(ScopeType.CONVERSATION)
public class PortfolioTreeAction {
	
	
	@Logger
	private Log log;

	@In(create=true)
	private List<Portfolio> fullPorts;
	
	@In(create=true)
	private List<Portfolio> fullHiers;
	
	@Out(scope=ScopeType.CONVERSATION)
	private TreeNode portRoot;
	
//	@Out(scope=ScopeType.CONVERSATION)
	private TreeNode selectedNode;
	
	@Out(scope=ScopeType.CONVERSATION)
	private Portfolio selectedPortfolio;

	private Boolean userDefined;
	private String activeAccordionTab;

	public PortfolioTreeAction() {
	}

	// --------------------------------Getter and Setter ---------------

	
	public TreeNode getPortRoot() {
		return portRoot;
	}

	public void setPortRoot(TreeNode portfolioRoot) {
		this.portRoot = portfolioRoot;
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
	
	
	@Factory("portRoot")
	public void generateTree() {
		Portfolio root = new Portfolio("root", "Root");
		portRoot = new DefaultTreeNode(root, null);
		portRoot.setExpanded(true);

		for (Portfolio bb : fullHiers) {
			TreeNode childNode = new DefaultTreeNode(bb, portRoot);
			childNode.setExpanded(true);
			recursive(fullPorts, childNode);
			log.info("Creation of Tree : #0", bb.getPortId());
		}
		if (portRoot.getChildCount() != 0) {
			portRoot.getChildren().get(0).setSelected(true);
			selectedNode = portRoot.getChildren().get(0);
			selectedPortfolio = (Portfolio) (portRoot.getChildren().get(0).getData());
//			portfolios = parentPortfolio.getChildPortfolios();
			log.info("In the Tree Generation1:#0");
		}
		Events.instance().raiseEvent("changeTree", selectedPortfolio);
//		log.info("In the Tree Generation:#0", subPortfolios.size());
		log.info("In the Tree Generation:#0");
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

	public void expandAll() {
		recursiveExpand(portRoot.getChildren(), true);
	}

	public void collapseAll() {
		recursiveExpand(portRoot.getChildren(), false);
	}

	// ----------------------------- helper method-------------------------

	private void recursiveExpand(List<TreeNode> node, boolean isExpand) {
		for (TreeNode aa : node) {
			aa.setExpanded(isExpand);
			recursiveExpand(aa.getChildren(), isExpand);
		}
	}



	private void recursive(List<Portfolio> port, TreeNode node) {
		List<IPortfolio> tempList = new ArrayList<IPortfolio>();

		String parentId = ((Portfolio) node.getData()).getPortId();
		tempList = getSubPortfolios(parentId, port);
		// log.info("ParentId in SubPort : #0,#1,#2", parentId, tempList.size(),node);

		for (IPortfolio k : tempList) {
			TreeNode childNode = new DefaultTreeNode(k, node);
			childNode.setExpanded(true);
			recursive(port, childNode);
		}
	}

	private List<IPortfolio> getSubPortfolios(String parentId, List<Portfolio> port) {
		List<IPortfolio> returnList = new ArrayList<IPortfolio>();
		for (Portfolio k : port) {
			
			if (k.getParentPortfolio() != null && k.getParentPortfolio().getPortId().equals(parentId)) {
				returnList.add(k);
			}
		}
		return returnList;
	}
}
