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
import org.jboss.seam.core.Events;
import org.jboss.seam.log.Log;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;

@Name("portfolioInitBean")
//@Scope(ScopeType.CONVERSATION)
public class PortfolioInitBean {
	@Logger
	private Log log;

	@In(create = true)
	private PortfolioReturnBssdList portfolioReturnBssdList;

	// @Out(scope=ScopeType.SESSION)
	@Out
	private List<Portfolio> fullPortfolios;
//	private List<Portfolio> fullPortfolios = new ArrayList<Portfolio>();

	// @Out(scope=ScopeType.SESSION)
	@Out
	private List<Portfolio> hierarchies ;
//	private List<Portfolio> hierarchies = new ArrayList<Portfolio>();

	public PortfolioInitBean() {
	}

	@Factory(value = "fullPortfolios")
	public void loadFullPortfolios() {
		log.info("In the port Factory1");
		fullPortfolios = new ArrayList<Portfolio>();
		hierarchies = new ArrayList<Portfolio>();
		for (PortfolioReturn aa : portfolioReturnBssdList.getResultList()) {
			fullPortfolios.add(aa.getPortfolio());
			if(aa.getPortfolio().getParentPortfolio()==null){
				hierarchies.add(aa.getPortfolio());
			}
		}
	}
}
