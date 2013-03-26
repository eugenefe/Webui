package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("pdSegHome")
public class PdSegHome extends EntityHome<PdSeg> {

	public void setPdSegId(String id) {
		setId(id);
	}

	public String getPdSegId() {
		return (String) getId();
	}

	@Override
	protected PdSeg createInstance() {
		PdSeg pdSeg = new PdSeg();
		return pdSeg;
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

	public PdSeg getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
