package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("lgdSegHome")
public class LgdSegHome extends EntityHome<LgdSeg> {

	public void setLgdSegId(String id) {
		setId(id);
	}

	public String getLgdSegId() {
		return (String) getId();
	}

	@Override
	protected LgdSeg createInstance() {
		LgdSeg lgdSeg = new LgdSeg();
		return lgdSeg;
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

	public LgdSeg getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
