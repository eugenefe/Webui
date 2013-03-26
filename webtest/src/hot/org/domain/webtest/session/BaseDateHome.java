package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("baseDateHome")
public class BaseDateHome extends EntityHome<BaseDate> {

	public void setBaseDateBssd(String id) {
		setId(id);
	}

	public String getBaseDateBssd() {
		return (String) getId();
	}

	@Override
	protected BaseDate createInstance() {
		BaseDate baseDate = new BaseDate();
		return baseDate;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public BaseDate getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<PositionHis> getPositionHises() {
		return getInstance() == null ? null : new ArrayList<PositionHis>(getInstance().getPositionHises());
	}

	public List<PortfolioHis> getPortfolioHises() {
		return getInstance() == null ? null : new ArrayList<PortfolioHis>(getInstance().getPortfolioHises());
	}

}
