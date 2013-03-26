package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("accountMstHome")
public class AccountMstHome extends EntityHome<AccountMst> {

	@In(create = true)
	AccountMstHome accountMstHome;

	public void setAccountMstId(String id) {
		setId(id);
	}

	public String getAccountMstId() {
		return (String) getId();
	}

	@Override
	protected AccountMst createInstance() {
		AccountMst accountMst = new AccountMst();
		return accountMst;
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

	public AccountMst getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Position> getPositions() {
		return getInstance() == null ? null : new ArrayList<Position>(getInstance().getPositions());
	}

	public List<AccountMst> getAccountMsts() {
		return getInstance() == null ? null : new ArrayList<AccountMst>(getInstance().getAccountMsts());
	}

}
