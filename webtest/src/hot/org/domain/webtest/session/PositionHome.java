package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("positionHome")
public class PositionHome extends EntityHome<Position> {

	@In(create = true)
	ObligorHome obligorHome;
	@In(create = true)
	DealerHome dealerHome;
	@In(create = true)
	ProductHome productHome;
	@In(create = true)
	AccountMstHome accountMstHome;

	public void setPositionId(String id) {
		setId(id);
	}

	public String getPositionId() {
		return (String) getId();
	}

	@Override
	protected Position createInstance() {
		Position position = new Position();
		return position;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		Obligor obligor = obligorHome.getDefinedInstance();
		if (obligor != null) {
			getInstance().setObligor(obligor);
		}
		Dealer dealer = dealerHome.getDefinedInstance();
		if (dealer != null) {
			getInstance().setDealer(dealer);
		}
		Product product = productHome.getDefinedInstance();
		if (product != null) {
			getInstance().setProduct(product);
		}
		AccountMst accountMst = accountMstHome.getDefinedInstance();
		if (accountMst != null) {
			getInstance().setAccountMst(accountMst);
		}
	}

	public boolean isWired() {
		return true;
	}

	public Position getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<PositionHis> getPositionHises() {
		return getInstance() == null ? null : new ArrayList<PositionHis>(getInstance().getPositionHises());
	}

}
