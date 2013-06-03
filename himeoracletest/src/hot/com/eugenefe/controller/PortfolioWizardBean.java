package com.eugenefe.controller;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.sound.midi.MidiDevice.Info;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.component.wizard.Wizard;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.DualListModel;

import com.eugenefe.entity.Portfolio;
import com.eugenefe.session.PortfolioHome;

@Name("portfolioWizardBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioWizardBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Logger
	private Log log;

	@In(create=true)
	private PortfolioBean portfolioBean;
	
	@In(create=true)
	private PortfolioHome portfolioHome;
	
//	@In(create=true)
//	private Wizard myWizard;

//	public Wizard getMyWizard() {
//		return myWizard;
//	}
//	public void setMyWizard(Wizard myWizard) {
//		this.myWizard = myWizard;
//	}

	private boolean ignoreCompare;
	
	@In(required=false)
	@Out(required=false, scope=ScopeType.CONVERSATION)
	private String newPortName;
	private DualListModel<String> ports;

	public PortfolioWizardBean() {
		System.out.println("Wizard Creation");

	}
//***************gettter and setter********************************
	public PortfolioBean getPortfolioBean() {
		return portfolioBean;
	}


	public void setPortfolioBean(PortfolioBean portfolioBean) {
		this.portfolioBean = portfolioBean;
	}


	public PortfolioHome getPortfolioHome() {
		log.info("get PortfolioHome");
		return portfolioHome;
	}


	public void setPortfolioHome(PortfolioHome portfolioHome) {
		log.info("Set PortfolioHome");
		this.portfolioHome = portfolioHome;
	}


	public boolean isIgnoreCompare() {
		return ignoreCompare;
	}


	public void setIgnoreCompare(boolean ignoreCompare) {
		this.ignoreCompare = ignoreCompare;
	}


	public String getNewPortName() {
		log.info("get PortfolioHome");
		return newPortName;
	}


	public void setNewPortName(String newPortName) {
		this.newPortName = newPortName;
	}


	public DualListModel<String> getPorts() {
		return ports;
	}


	public void setPorts(DualListModel<String> ports) {
		this.ports = ports;
	}

//*******************************************************************	
	
	public String onFlowProcess(FlowEvent event) {
		log.info("Current wizard step #0, #1:" ,event.getOldStep());
		log.info("Next step #0,#1 :" , event.getNewStep());
		log.info("Full Size: #0, #1" ,portfolioBean.getFullPortfolios().size(), newPortName);
		
		for (Portfolio aa : portfolioBean.getFullPortfolios()) {
			if (aa.getPortId().equals(newPortName)) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
//				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
//						"Error : Portfolio Name is Duplicated" + newPortName));
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Error : Portfolio Name is Duplicated" , newPortName));
				// FacesMessage msg = new FacesMessage("Portfolio Name Error",
				// "asdfadf" + newPortName);
				return event.getOldStep();
			}
		}
		if (event.getOldStep() != null && event.getOldStep().equals("loadPorts")) {
			loadPickList();
		}
		FacesMessage msg = new FacesMessage("Successful", "Welcome :" );  
	     FacesContext.getCurrentInstance().addMessage(null, msg);
		return event.getNewStep();
	}

//	@Begin(join=true)
	public void loadPickList() {
		log.info("Load Wizard");
		List<String> citiesSource = new ArrayList<String>();
		List<String> citiesTarget = new ArrayList<String>();
		if (!ignoreCompare) {
			for (Portfolio bb : portfolioBean.getParentPortfolio().getChildPortfolios()) {
				citiesTarget.add(bb.getPortName());
			}
		}
		for (Portfolio aa : portfolioBean.getFullPortfolios()) {
			if (!citiesTarget.contains(aa.getPortName())) {
				citiesSource.add(aa.getPortName());
			}
			// log.info("pickList:#0", aa.getPortName());
		}
		ports = new DualListModel<String>(citiesSource, citiesTarget);
		log.info("Load Wizard");
	}

//	@End
	public void save() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Save New Portfolio"));
		log.info("Save1 :");
		log.info("Save1 :#0",  newPortName);
		portfolioHome.getInstance().setPortId(newPortName);
		log.info("Save2 :#0", portfolioHome.getPortfolioPortId());
		portfolioHome.persist();
		log.info("Save3");

//		newPortName = null;

	}
	public void saveaction(ActionEvent actionEvent) {
		log.info("SaveAction3");
		 FacesMessage msg = new FacesMessage("Successful", "Welcome :" );  
	     FacesContext.getCurrentInstance().addMessage(null, msg);  

//		newPortName = null;

	}
}
