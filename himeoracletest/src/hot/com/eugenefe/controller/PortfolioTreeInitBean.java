package com.eugenefe.controller;

import groovyjarjarantlr.debug.Event;

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
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

@Name("portfolioTreeInitBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioTreeInitBean {
	@Logger
	private Log log;

	@In(create = true)
	private PortfolioReturnBssdList portfolioReturnBssdList;

	private List<Portfolio> fullPortfolios;
	private List<Portfolio> hierarchies;
	private List<Portfolio> selectedHierarchies;
//	private Portfolio selHier;
//	
//	public Portfolio getSelHier() {
//		return selHier;
//	}
//	public void setSelHier(Portfolio selHier) {
//		this.selHier = selHier;
//	}

	private List<String> selectedHierarchyIds;
	
	private TreeNode portfolioRoot;
	private Portfolio parentPortfolio;
	private List<Portfolio> portfolios;
	
	private TreeNode selectedNode;

	private Boolean userDefined;
	private String activeAccordionTab;

	
	
	public PortfolioTreeInitBean() {
		System.out.println("PortfolioTreeInitBean Constructor");
	}
	
//--------------getter and setter------------------

	public List<Portfolio> getFullPortfolios() {
		return fullPortfolios;
	}
	public void setFullPortfolios(List<Portfolio> fullPortfolios) {
		this.fullPortfolios = fullPortfolios;
	}

	public List<Portfolio> getHierarchies() {
		return hierarchies;
	}
	public void setHierarchies(List<Portfolio> hierarchies) {
		this.hierarchies = hierarchies;
	}

	public TreeNode getPortfolioRoot() {
		return portfolioRoot;
	}
	public void setPortfolioRoot(TreeNode portfolioRoot) {
		this.portfolioRoot = portfolioRoot;
	}

	public Portfolio getParentPortfolio() {
		return parentPortfolio;
	}
	public void setParentPortfolio(Portfolio parentPortfolio) {
		this.parentPortfolio = parentPortfolio;
	}

	public List<Portfolio> getPortfolios() {
		return portfolios;
	}
	public void setPortfolios(List<Portfolio> portfolios) {
		this.portfolios = portfolios;
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

//***************************************************************
	@Begin(join=true)
	@Observer("changeBssd")
	public void init() {
	    log.info("In the Tree1:  #0", portfolioReturnBssdList.getEjbql());
	    
		loadFullPortfolios();
		generateTree(hierarchies);
		
		log.info("Event initPortfolioTree0");
		Events.instance().raiseEvent("initPortfolioTree", portfolioRoot, portfolios);
		
		log.info("Event initPortfolioTree1");
	}
	
	
//**************************Event Listener**************************************
	
	public void onChangeHierarchy() {
		log.info("Change Hierarchy : #0");

		selectedHierarchies = new ArrayList<Portfolio>();

		for (Portfolio aa : hierarchies) {
			if (selectedHierarchyIds != null && !selectedHierarchyIds.isEmpty()) {
				if (selectedHierarchyIds.contains(aa.getPortId())) {
					selectedHierarchies.add(aa);
				}
			} else {
				selectedHierarchies.add(aa);
			}
		}
		generateTree(selectedHierarchies);
		Events.instance().raiseEvent("initPortfolioTree", portfolioRoot, portfolios);
	}
	
	// ----------------------------- helper method-------------------------
	public void loadFullPortfolios() {
		log.info("In the port Factory1");
		fullPortfolios = new ArrayList<Portfolio>();
		hierarchies = new ArrayList<Portfolio>();
		for (PortfolioReturn aa : portfolioReturnBssdList.getResultList()) {
			fullPortfolios.add(aa.getPortfolio());
			log.info("In the port Factory2 :#0", fullPortfolios.size());
			if (aa.getPortfolio().getParentPortfolio() == null) {
				hierarchies.add(aa.getPortfolio());
			}
		}
		log.info("In the port Factory2");
	}

	
	public void generateTree(List<Portfolio> rootPorts) {
		portfolios = new ArrayList<Portfolio>();
		Portfolio root = new Portfolio("root", "Root");
		portfolioRoot = new DefaultTreeNode(root, null);
		portfolioRoot.setExpanded(true);

		for (Portfolio bb : rootPorts) {
			TreeNode childNode = new DefaultTreeNode(bb, portfolioRoot);
			childNode.setExpanded(true);
			recursive(fullPortfolios, childNode);
			log.info("Creation of Tree : #0", bb.getPortId());
		}
		if (portfolioRoot.getChildCount() != 0) {
			portfolioRoot.getChildren().get(0).setSelected(true);
			parentPortfolio = (Portfolio) (portfolioRoot.getChildren().get(0).getData());
			portfolios = parentPortfolio.getChildPortfolios();
			log.info("In the Tree Generation1:#0");
		}
		Events.instance().raiseEvent("changeTree", parentPortfolio);
//		log.info("In the Tree Generation:#0", subPortfolios.size());
		log.info("In the Tree Generation:#0");
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
