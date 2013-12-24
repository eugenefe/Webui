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
import com.eugenefe.entity.Position;
import com.eugenefe.entity.PositionReturn;

@Name("positionAction")
public class PositionAction {
	@Logger
	private Log log;
	
	@In
	private EntityManager entityManager;
	
//	@Out(scope=ScopeType.CONVERSATION)
	private List<Position> posReturn;
	
	@Out(scope=ScopeType.CONVERSATION)
	private  LazyDataModel<Position> posModel;
	
	public LazyDataModel<Position> getModel() {
		return posModel;
	}
	public void setModel(LazyDataModel<Position> model) {
		this.posModel = model;
	}

	@Factory(value="posModel")
	public void initModel(){
		posReturn = entityManager.createQuery(NamedQuery.Position.getQuery()).getResultList();
		posModel = new LazyModelPosition(posReturn); 
	}

//	@Factory (value="posReturn")
	public void init(){
		posReturn = entityManager.createQuery(NamedQuery.Position.getQuery()).getResultList();
		
		
	}
	
	
}
