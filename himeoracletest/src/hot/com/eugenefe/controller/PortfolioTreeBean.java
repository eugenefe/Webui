package com.eugenefe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;

import com.eugenefe.entity.IPortfolio;
import com.eugenefe.entity.Portfolio;
import com.eugenefe.session.PortfolioList;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

@Name("portfolioTreeBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioTreeBean {
	@Logger
	private Log log;

	@In(create = true)
	private PortfolioList portfolioList;
	
//	@In(required= false)
	List<Portfolio> fullPort = new ArrayList<Portfolio>();

//	@DataModelSelection 
	@Out(required=false)
	private Portfolio selectedPortfolio;
	
	@Out(required=false , scope=ScopeType.EVENT)
	private TreeNode selectedNode;
	
	
	@Out
	private TreeNode portfolioRoot;
	private TreeNode portfolioSuperRoot;



	public PortfolioTreeBean() {
		System.out.println("In the Creation CTRL");
	}
	
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
	
	
	
	
//-----------------------End of Getter and Setter--------------------------------
	

	public void displaySelectedSingle() {
		if (selectedNode != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected"
					, ((Portfolio)selectedNode.getData()).getPortId()	);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}


	@Create
	public void init(){
		fullPort = portfolioList.getResultList();
//		portfolios = portfolioList.getResultList();
		log.info("Call after creation : #0" , fullPort.size());
	}
	
//	@Factory(value="portfolioRoot" , autoCreate=true)
	@Factory(value="portfolioRoot" )
	public void initTree() {
		log.info("Start Init portfolio Tree");

		IPortfolio superRoot = new Portfolio("superRoot", "SuperRoot");
		IPortfolio root = new Portfolio("root", "Root");

		portfolioSuperRoot = new DefaultTreeNode(superRoot, null);
		portfolioRoot = new DefaultTreeNode(root, portfolioSuperRoot);
		// portfolioRoot.setSelectable(false);
		portfolioRoot.setExpanded(true);
//		portfolioRoot.setSelected(true);
//		log.info("Creation PortfolioTreeBean");

//		fullPort = portfolioList.getResultList();
		// Hierarchy º° Root Portfolio
		List<IPortfolio> rootPort = new ArrayList<IPortfolio>();

		for (Portfolio aa : fullPort) {
			if (aa.getParentPortfolio() == null) {
				rootPort.add(aa);
			}
		}

		for (IPortfolio bb : rootPort) {
			TreeNode childNode = new DefaultTreeNode(bb, portfolioRoot);
			childNode.setExpanded(true);
			recursive(fullPort, childNode);
			
		}
		log.info("Init portfolio Tree :#0" , portfolioRoot.getChildCount());
	}
	

	// ----------------------------- helper method----------------------------------------

	private void recursive(List<Portfolio> port, TreeNode node) {
		List<IPortfolio> tempList = new ArrayList<IPortfolio>();
		String parentId = ((Portfolio)node.getData()).getPortId();
		tempList = getSubPortfolios(parentId, port);
		log.info("ParentId in SubPort : #0,#1,#2", parentId, tempList.size(), node);

		for (IPortfolio k : tempList) {
			TreeNode childNode = new DefaultTreeNode(k, node);
			childNode.setExpanded(true);
			recursive(port, childNode);
		}
	}

	private List<IPortfolio> getSubPortfolios(String parentId, List<Portfolio> port) {
		List<IPortfolio> returnList = new ArrayList<IPortfolio>();
		for (Portfolio k : port) {
			if (k.getParentPortfolio() != null 
					&& k.getParentPortfolio().getPortId().equals(parentId)) {
				returnList.add(k);
			}
		}
		
		return returnList;
	}
}
