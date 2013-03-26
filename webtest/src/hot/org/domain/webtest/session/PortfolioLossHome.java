package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("portfolioLossHome")
public class PortfolioLossHome extends EntityHome<PortfolioLoss> {

	@In(create = true)
	PortfolioHisHome portfolioHisHome;

	public void setPortfolioLossId(PortfolioLossId id) {
		setId(id);
	}

	public PortfolioLossId getPortfolioLossId() {
		return (PortfolioLossId) getId();
	}

	public PortfolioLossHome() {
		setPortfolioLossId(new PortfolioLossId());
	}

	@Override
	public boolean isIdDefined() {
		if (getPortfolioLossId().getBssd() == null || "".equals(getPortfolioLossId().getBssd()))
			return false;
		if (getPortfolioLossId().getPortfolioId() == null || "".equals(getPortfolioLossId().getPortfolioId()))
			return false;
		if (getPortfolioLossId().getLossId() == null || "".equals(getPortfolioLossId().getLossId()))
			return false;
		return true;
	}

	@Override
	protected PortfolioLoss createInstance() {
		PortfolioLoss portfolioLoss = new PortfolioLoss();
		portfolioLoss.setId(new PortfolioLossId());
		return portfolioLoss;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		PortfolioHis portfolioHis = portfolioHisHome.getDefinedInstance();
		if (portfolioHis != null) {
			getInstance().setPortfolioHis(portfolioHis);
		}
	}

	public boolean isWired() {
		if (getInstance().getPortfolioHis() == null)
			return false;
		return true;
	}

	public PortfolioLoss getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
