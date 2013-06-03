package com.eugenefe.converter;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.primefaces.component.wizard.Wizard;


@Name("myWizard")
@Scope(ScopeType.EVENT)
public class NewPortfolioWizard{
	
	private Wizard wizard ;
	
	public NewPortfolioWizard(){
	}

	public Wizard getWizard() {
		return wizard;
	}

	public void setWizard(Wizard wizard) {
		this.wizard = wizard;
	}

}
