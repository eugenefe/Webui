package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("positionLossHome")
public class PositionLossHome extends EntityHome<PositionLoss> {

	@In(create = true)
	PositionHisHome positionHisHome;

	public void setPositionLossId(PositionLossId id) {
		setId(id);
	}

	public PositionLossId getPositionLossId() {
		return (PositionLossId) getId();
	}

	public PositionLossHome() {
		setPositionLossId(new PositionLossId());
	}

	@Override
	public boolean isIdDefined() {
		if (getPositionLossId().getBssd() == null || "".equals(getPositionLossId().getBssd()))
			return false;
		if (getPositionLossId().getPositionId() == null || "".equals(getPositionLossId().getPositionId()))
			return false;
		if (getPositionLossId().getLossNo() == null || "".equals(getPositionLossId().getLossNo()))
			return false;
		return true;
	}

	@Override
	protected PositionLoss createInstance() {
		PositionLoss positionLoss = new PositionLoss();
		positionLoss.setId(new PositionLossId());
		return positionLoss;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		PositionHis positionHis = positionHisHome.getDefinedInstance();
		if (positionHis != null) {
			getInstance().setPositionHis(positionHis);
		}
	}

	public boolean isWired() {
		if (getInstance().getPositionHis() == null)
			return false;
		return true;
	}

	public PositionLoss getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
