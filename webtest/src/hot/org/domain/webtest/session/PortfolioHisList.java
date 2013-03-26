package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("portfolioHisList")
public class PortfolioHisList extends EntityQuery<PortfolioHis> {

	private static final String EJBQL = "select portfolioHis from PortfolioHis portfolioHis";

	private static final String[] RESTRICTIONS = {
			"lower(portfolioHis.id.bssd) like lower(concat(#{portfolioHisList.portfolioHis.id.bssd},'%'))",
			"lower(portfolioHis.id.portfolioId) like lower(concat(#{portfolioHisList.portfolioHis.id.portfolioId},'%'))", };

	private PortfolioHis portfolioHis;

	public PortfolioHisList() {
		portfolioHis = new PortfolioHis();
		portfolioHis.setId(new PortfolioHisId());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public PortfolioHis getPortfolioHis() {
		return portfolioHis;
	}
}
