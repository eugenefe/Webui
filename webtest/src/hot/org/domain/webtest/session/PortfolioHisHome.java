package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("portfolioHisHome")
public class PortfolioHisHome extends EntityHome<PortfolioHis> {

	@In(create = true)
	BaseDateHome baseDateHome;
	@In(create = true)
	PortfolioHome portfolioHome;

	public void setPortfolioHisId(PortfolioHisId id) {
		setId(id);
	}

	public PortfolioHisId getPortfolioHisId() {
		return (PortfolioHisId) getId();
	}

	public PortfolioHisHome() {
		setPortfolioHisId(new PortfolioHisId());
	}

	@Override
	public boolean isIdDefined() {
		if (getPortfolioHisId().getBssd() == null || "".equals(getPortfolioHisId().getBssd()))
			return false;
		if (getPortfolioHisId().getPortfolioId() == null || "".equals(getPortfolioHisId().getPortfolioId()))
			return false;
		return true;
	}

	@Override
	protected PortfolioHis createInstance() {
		PortfolioHis portfolioHis = new PortfolioHis();
		portfolioHis.setId(new PortfolioHisId());
		return portfolioHis;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		BaseDate baseDate = baseDateHome.getDefinedInstance();
		if (baseDate != null) {
			getInstance().setBaseDate(baseDate);
		}
		Portfolio portfolio = portfolioHome.getDefinedInstance();
		if (portfolio != null) {
			getInstance().setPortfolio(portfolio);
		}
	}

	public boolean isWired() {
		if (getInstance().getBaseDate() == null)
			return false;
		if (getInstance().getPortfolio() == null)
			return false;
		return true;
	}

	public PortfolioHis getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<PortfolioLoss> getPortfolioLosses() {
		return getInstance() == null ? null : new ArrayList<PortfolioLoss>(getInstance().getPortfolioLosses());
	}

}
