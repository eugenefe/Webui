package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("positionHisHome")
public class PositionHisHome extends EntityHome<PositionHis> {

	@In(create = true)
	PositionHome positionHome;
	@In(create = true)
	BaseDateHome baseDateHome;

	public void setPositionHisId(PositionHisId id) {
		setId(id);
	}

	public PositionHisId getPositionHisId() {
		return (PositionHisId) getId();
	}

	public PositionHisHome() {
		setPositionHisId(new PositionHisId());
	}

	@Override
	public boolean isIdDefined() {
		if (getPositionHisId().getBssd() == null || "".equals(getPositionHisId().getBssd()))
			return false;
		if (getPositionHisId().getPositionId() == null || "".equals(getPositionHisId().getPositionId()))
			return false;
		return true;
	}

	@Override
	protected PositionHis createInstance() {
		PositionHis positionHis = new PositionHis();
		positionHis.setId(new PositionHisId());
		return positionHis;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Position position = positionHome.getDefinedInstance();
		if (position != null) {
			getInstance().setPosition(position);
		}
		BaseDate baseDate = baseDateHome.getDefinedInstance();
		if (baseDate != null) {
			getInstance().setBaseDate(baseDate);
		}
	}

	public boolean isWired() {
		if (getInstance().getPosition() == null)
			return false;
		if (getInstance().getBaseDate() == null)
			return false;
		return true;
	}

	public PositionHis getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<PositionLoss> getPositionLosses() {
		return getInstance() == null ? null : new ArrayList<PositionLoss>(getInstance().getPositionLosses());
	}

}
