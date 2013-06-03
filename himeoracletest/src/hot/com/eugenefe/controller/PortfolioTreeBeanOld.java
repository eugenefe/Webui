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

@Name("portfolioTreeBeanOld")
@Scope(ScopeType.CONVERSATION)
public class PortfolioTreeBeanOld {
	@Logger
	private Log log;

	@In(create = true)
	private PortfolioReturnBssdList portfolioReturnBssdList;
	
	@In(create=true)
	private PortfolioTreeInitBean portfolioTreeInitBean;

//	@In
	// @In(create = true)
	// @Out(scope=ScopeType.SESSION)
	// @Out
	private List<Portfolio> fullPortfolios;
	// private List<Portfolio> fullPortfolios = new ArrayList<Portfolio>();

//	@In
	// @In(create = true)
	// @Out(scope=ScopeType.SESSION)
	// @Out
	private List<Portfolio> hierarchies;
	// private List<Portfolio> hierarchies = new ArrayList<Portfolio>();

	private List<Portfolio> selectedHierarchies;
	private List<Portfolio> appliedHierarchies;

	public List<Portfolio> getAppliedHierarchies() {
		return appliedHierarchies;
	}

	public void setAppliedHierarchies(List<Portfolio> appliedHierarchies) {
		this.appliedHierarchies = appliedHierarchies;
	}

//	@In
	// @In(create=true)
	// @Out
	private TreeNode portfolioRoot;
	private TreeNode portfolioSuperRoot;

	private TreeNode selectedNode;

	private Portfolio selectedPortfolio;

	private List<String> selectedHierarchyIds;

	private Boolean userDefined;

	public Boolean getUserDefined() {
		return userDefined;
	}

	public void setUserDefined(Boolean userDefined) {
		this.userDefined = userDefined;
	}

	private String activeAccordionTab;

	public String getActiveAccordionTab() {
		return activeAccordionTab;
	}

	public void setActiveAccordionTab(String activeAccordionTab) {
		this.activeAccordionTab = activeAccordionTab;
	}

	public PortfolioTreeBeanOld() {
	}

	// -----------------------Initalizer------------------------

	// --------------------------------Getter and Setter ---------------

	public PortfolioReturnBssdList getPortfolioReturnBssdList() {
		return portfolioReturnBssdList;
	}

	public void setPortfolioReturnBssdList(PortfolioReturnBssdList portfolioReturnBssdList) {
		this.portfolioReturnBssdList = portfolioReturnBssdList;
	}

	public TreeNode getPortfolioRoot() {
		return portfolioRoot;
	}

	public void setPortfolioRoot(TreeNode portfolioRoot) {
		this.portfolioRoot = portfolioRoot;
	}

	public TreeNode getPortfolioSuperRoot() {
		return portfolioSuperRoot;
	}

	public void setPortfolioSuperRoot(TreeNode portfolioSuperRoot) {
		this.portfolioSuperRoot = portfolioSuperRoot;
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

	public List<Portfolio> getFullPortfolios() {
		return fullPortfolios;
	}

	public void setFullPortfolios(List<Portfolio> fullPortfolios) {
		this.fullPortfolios = fullPortfolios;
	}

	public List<Portfolio> getSelectedHierarchies() {
		return selectedHierarchies;
	}

	public void setSelectedHierarchies(List<Portfolio> selectedHierarchies) {
		this.selectedHierarchies = selectedHierarchies;
	}

	public List<String> getSelectedHierarchyIds() {
		return selectedHierarchyIds;
	}

	public void setSelectedHierarchyIds(List<String> selectedHierarchyIds) {
		this.selectedHierarchyIds = selectedHierarchyIds;
	}

	public List<Portfolio> getHierarchies() {
		return hierarchies;
	}

	public void setHierarchies(List<Portfolio> hierarchies) {
		this.hierarchies = hierarchies;
	}

	// ----------------------Event Listener------------------
//	@Observer("changeBssd")
	public void initEvent() {
		log.info("BaseDate Change Event : #0");

		// Initialize properties
		fullPortfolios = new ArrayList<Portfolio>();
		hierarchies = new ArrayList<Portfolio>();

		selectedHierarchies = new ArrayList<Portfolio>();
		// appliedHierarchies = new ArrayList<Portfolio>();

		for (PortfolioReturn aa : portfolioReturnBssdList.getResultList()) {
			fullPortfolios.add(aa.getPortfolio());
			log.info("In Init 1: #0");
			if (aa.getPortfolio().getParentPortfolio() == null) {
				hierarchies.add(aa.getPortfolio());
				log.info("In Init 2: #0");
				if (selectedHierarchyIds != null && !selectedHierarchyIds.isEmpty()) {
					if (selectedHierarchyIds.contains(aa.getPortfolio().getPortId())) {
						selectedHierarchies.add(aa.getPortfolio());
					}
				} else {
					selectedHierarchies.add(aa.getPortfolio());
				}
			}
		}

		portfolioRoot.getChildren().clear();
		generateTree();

		if (portfolioRoot.getChildCount() != 0) {
			portfolioRoot.getChildren().get(0).setSelected(true);
			selectedNode = portfolioRoot.getChildren().get(0);
			selectedPortfolio = (Portfolio) (portfolioRoot.getChildren().get(0).getData());
		}

		log.info("End of Initalizing PortfolioBean Creation with Given Bssd: #0, #1, #2"
				,FacesContext.getCurrentInstance().getViewRoot().getViewId()
				,Conversation.instance().getViewId());
	}

	public void onNodeSelect(NodeSelectEvent event) {

		selectedPortfolio = (Portfolio) selectedNode.getData();
		log.info("Call Node Select Event: #0, #1, #2", selectedPortfolio.getPortId(), selectedPortfolio
				.getChildPortfolios().size());
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
		recursiveExpand(portfolioRoot.getChildren(), true);
	}

	public void collapseAll() {
		recursiveExpand(portfolioRoot.getChildren(), false);
	}

	// ----------------------------- helper method-------------------------

	private void recursive(List<Portfolio> port, TreeNode node) {
		List<IPortfolio> tempList = new ArrayList<IPortfolio>();
		// Portfolio tempPort = (Portfolio)node.getData();
		// tempList = tempPort.getChildren();

		String parentId = ((Portfolio) node.getData()).getPortId();
		tempList = getSubPortfolios(parentId, port);
		// log.info("ParentId in SubPort : #0,#1,#2", parentId, tempList.size(),
		// node);

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

	private void recursiveExpand(List<TreeNode> node, boolean isExpand) {
		for (TreeNode aa : node) {
			aa.setExpanded(isExpand);
			recursiveExpand(aa.getChildren(), isExpand);
		}
	}

	// @Factory(value = "portfolioRoot")
	public void generateTree() {
		Portfolio superRoot = new Portfolio("superRoot", "SuperRoot");
		Portfolio root = new Portfolio("root", "Root");

		portfolioSuperRoot = new DefaultTreeNode(superRoot, null);
		portfolioRoot = new DefaultTreeNode(root, portfolioSuperRoot);

		portfolioRoot.setExpanded(true);
		log.info("In the Tree1");
		for (Portfolio bb : hierarchies) {
			log.info("In the Tree2");
			TreeNode childNode = new DefaultTreeNode(bb, portfolioRoot);
			childNode.setExpanded(true);
			recursive(fullPortfolios, childNode);
			log.info("Creation of Tree : #0", bb.getPortId());
		}

		if (portfolioRoot.getChildCount() != 0) {
			portfolioRoot.getChildren().get(0).setSelected(true);
			selectedNode = portfolioRoot.getChildren().get(0);
			selectedPortfolio = (Portfolio) (portfolioRoot.getChildren().get(0).getData());
		}
	}

}
