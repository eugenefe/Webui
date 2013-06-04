package com.eugenefe.session.seam;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.log.Log;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.eugenefe.entity.Portfolio;
import com.eugenefe.entity.PositionReturn;

@Name("positionReturnAction")
public class PositionReturnAction {
	@Logger
	private Log log;
	
	@In
	private EntityManager entityManager;
	
//	@Out(scope=ScopeType.CONVERSATION)
	private List<PositionReturn> posReturn;
	
	@Out(scope=ScopeType.CONVERSATION)
	private  LazyDataModel<PositionReturn> model;
	
	public LazyDataModel<PositionReturn> getModel() {
		return model;
	}
	public void setModel(LazyDataModel<PositionReturn> model) {
		this.model = model;
	}

	@Factory(value="model")
	public void initModel(){
		posReturn = entityManager.createQuery(NamedQuery.Position.getQuery()).getResultList();
		model = new LazyModelPositionReturn(posReturn); 
	}

//	@Factory (value="posReturn")
	public void init(){
		posReturn = entityManager.createQuery(NamedQuery.Position.getQuery()).getResultList();
		
		
	}
	
	
}
