package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("portfolioList")
public class PortfolioList extends EntityQuery<Portfolio> {

	private static final String EJBQL = "select portfolio from Portfolio portfolio";

	private static final String[] RESTRICTIONS = {
			"lower(portfolio.id) like lower(concat(#{portfolioList.portfolio.id},'%'))",
			"lower(portfolio.name) like lower(concat(#{portfolioList.portfolio.name},'%'))",
			"lower(portfolio.groupId) like lower(concat(#{portfolioList.portfolio.groupId},'%'))",
			"lower(portfolio.level1) like lower(concat(#{portfolioList.portfolio.level1},'%'))",
			"lower(portfolio.level2) like lower(concat(#{portfolioList.portfolio.level2},'%'))",
			"lower(portfolio.level3) like lower(concat(#{portfolioList.portfolio.level3},'%'))",
			"lower(portfolio.level4) like lower(concat(#{portfolioList.portfolio.level4},'%'))",
			"lower(portfolio.level5) like lower(concat(#{portfolioList.portfolio.level5},'%'))",};
			

	private Portfolio portfolio = new Portfolio();

	public PortfolioList() {
		setEjbql(EJBQL);
		setOrderColumn("portfolio.id");
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Portfolio getPortfolio() {
		return portfolio;
	}
}
