package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("portfolioLossList")
public class PortfolioLossList extends EntityQuery<PortfolioLoss> {

	private static final String EJBQL = "select portfolioLoss from PortfolioLoss portfolioLoss";

	private static final String[] RESTRICTIONS = {
			"lower(portfolioLoss.id.bssd) like lower(concat(#{portfolioLossList.portfolioLoss.id.bssd},'%'))",
			"lower(portfolioLoss.id.portfolioId) like lower(concat(#{portfolioLossList.portfolioLoss.id.portfolioId},'%'))",
			"lower(portfolioLoss.id.lossId) like lower(concat(#{portfolioLossList.portfolioLoss.id.lossId},'%'))", };

	private PortfolioLoss portfolioLoss;

	public PortfolioLossList() {
		portfolioLoss = new PortfolioLoss();
		portfolioLoss.setId(new PortfolioLossId());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public PortfolioLoss getPortfolioLoss() {
		return portfolioLoss;
	}
}
