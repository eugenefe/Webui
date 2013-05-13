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
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
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
	@In(create = true)
	private PortfolioReturnList portfolioReturnList;
	
//	@In(required= false)
	List<Portfolio> fullPort = new ArrayList<Portfolio>();
	

//	@DataModelSelection 
	@Out(required=false)
	private Portfolio selectedPortfolio;
	
	@Out(required=false)
	private TreeNode selectedNode;
	
	
	@Out
	private TreeNode portfolioRoot;
	private TreeNode portfolioSuperRoot;

	private List<IPortfolio> rootPort = new ArrayList<IPortfolio>();
	private List<String> rootPortString = new ArrayList<String>();
	private List<IPortfolio> selRootPort ;


	public List<IPortfolio> getSelRootPort() {
		return selRootPort;
	}

	public void setSelRootPort(List<IPortfolio> selRootPort) {
		this.selRootPort = selRootPort;
	}

	public List<String> getRootPortString() {
		return rootPortString;
	}

	public void setRootPortString(List<String> rootPortString) {
		this.rootPortString = rootPortString;
	}

	public PortfolioTreeBean() {
		System.out.println("In the Creation CTRL");
	}
	
//----------------------- Getter and Setter--------------------------------	
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
		this.selectedPortfolio = (Portfolio)selectedNode.getData();
		this.selectedNode = selectedNode;
	}
	
	public Portfolio getSelectedPortfolio() {
		return selectedPortfolio;
	}

	public void setSelectedPortfolio(Portfolio selectedPortfolio) {
		this.selectedPortfolio = selectedPortfolio;
	}
	
	public List<IPortfolio> getRootPort() {
		return rootPort;
	}

	public void setRootPort(List<IPortfolio> rootPort) {
		this.rootPort = rootPort;
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
//		fullPort = portfolioList.getResultList();
		for(PortfolioReturn aa: portfolioReturnList.getResultList()){
			log.info("After Portfolio Tree Creation 01: #0" , aa.getBasedate().getBssd());
			fullPort.add(aa.getPortfolio());
		}
//		portfolios = portfolioList.getResultList();
		log.info("After Portfolio Tree Creation 02: #0" );
	}
	
	@Observer("changeBssd")
	public void onDateSelect(String bssd){
//		fullPort = portfolioList.getResultList();
		fullPort.clear();
		log.info("Change BaseDate Event 1: #0, #1");
		for(PortfolioReturn aa: portfolioReturnList.getResultList()){
			fullPort.add(aa.getPortfolio());
			log.info("Change BaseDate Event 2: #0, #1");
		}
//		portfolios = portfolioList.getResultList();
		
		log.info("Change BaseDate Event 3 : #0, #1" , fullPort.size());
		initTree();
		
		
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
//		List<IPortfolio> rootPort = new ArrayList<IPortfolio>();
		rootPort.clear();

		for (Portfolio aa : fullPort) {
			if (aa.getParentPortfolio() == null) {
				rootPort.add(aa);
				rootPortString.add(aa.getPortName());
			}
		}

		for (IPortfolio bb : rootPort) {
			TreeNode childNode = new DefaultTreeNode(bb, portfolioRoot);
			childNode.setExpanded(true);
			recursive(fullPort, childNode);
		}
		log.info("Init portfolio Tree :#0" , portfolioRoot.getChildCount());
		
		if(portfolioRoot.getChildCount()!=0){
			portfolioRoot.getChildren().get(0).setSelected(true);
			selectedPortfolio= (Portfolio)(portfolioRoot.getChildren().get(0).getData());
			Events.instance().raiseEvent("changeTree", selectedPortfolio);
		}
	}
	
	// -----------------Evnet Listener---------------------------------
		public void onNodeSelect(NodeSelectEvent event) {
			log.info("Call Node Select Event: #0", ((IPortfolio) event.getTreeNode().getData()).getStringId());
			selectedPortfolio = (Portfolio) event.getTreeNode().getData();
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
