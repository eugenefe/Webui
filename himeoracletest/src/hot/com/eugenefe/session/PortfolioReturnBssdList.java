package com.eugenefe.session;

import com.eugenefe.entity.*;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Name("portfolioReturnBssdList")
public class PortfolioReturnBssdList extends EntityQuery<PortfolioReturn> {

	private static final String EJBQL = "select aa from PortfolioReturn aa";

	private static final String[] RESTRICTIONS = {
			"aa.id.bssd = #{basedateBean.bssd}",	
			"lower(aa.id.bssd) like lower(concat(#{portfolioReturnBssdList.portfolioReturn.id.bssd},'%'))",
			"lower(aa.id.portId) like lower(concat(#{portfolioReturnBssdList.portfolioReturn.id.portId},'%'))", };

	private PortfolioReturn portfolioReturn;

	public PortfolioReturnBssdList() {
		portfolioReturn = new PortfolioReturn();
		portfolioReturn.setId(new PortfolioReturnId());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
//		setMaxResults(25);
//		setMaxResults(10);
	}

	public PortfolioReturn getPortfolioReturn() {
		return portfolioReturn;
	}
	
	
}
