package com.eugenefe.session.seam;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Out;

import com.eugenefe.entity.Portfolio;

@Name("fullPortfolioAction")
public class FullPortfolioAction {
	
	@In
	private EntityManager entityManager;

	@Out(scope=ScopeType.CONVERSATION)
	private List<Portfolio> fullPorts;
	
	@Out(scope=ScopeType.CONVERSATION)
	private List<Portfolio> fullHiers;
	
	public FullPortfolioAction(){
	}
	
	@Factory(value="fullPorts" )
//	@Factory(value="fullPorts" , autoCreate=true)
	@Observer("changeBssd")
	public void initFullPorts(){
		init();
	}
	
	@Factory(value="fullHiers")
//	@Factory(value="fullHiers", autoCreate=true)
	public void initFullHiers(){
		init();
	}
	
	private void init(){
//		String qr = NamedQuery.PortfolioJoinReturnBssd.getQuery();
		fullPorts = entityManager.createQuery(NamedQuery.PortfolioJoinReturnBssd.getQuery()).getResultList();
		fullHiers= new ArrayList<Portfolio>();
		for(Portfolio aa : fullPorts){
			if(aa.getParentPortfolio()== null){
				fullHiers.add(aa);
			}
		}
	}

}
