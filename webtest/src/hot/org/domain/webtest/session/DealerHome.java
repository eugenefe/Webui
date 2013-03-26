package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("dealerHome")
public class DealerHome extends EntityHome<Dealer> {

	public void setDealerId(String id) {
		setId(id);
	}

	public String getDealerId() {
		return (String) getId();
	}

	@Override
	protected Dealer createInstance() {
		Dealer dealer = new Dealer();
		return dealer;
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

	public Dealer getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Position> getPositions() {
		return getInstance() == null ? null : new ArrayList<Position>(getInstance().getPositions());
	}

}
