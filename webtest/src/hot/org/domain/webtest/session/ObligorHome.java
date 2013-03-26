package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import java.util.ArrayList;
import java.util.List;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

@Name("obligorHome")
public class ObligorHome extends EntityHome<Obligor> {

	public void setObligorId(String id) {
		setId(id);
	}

	public String getObligorId() {
		return (String) getId();
	}

	@Override
	protected Obligor createInstance() {
		Obligor obligor = new Obligor();
		return obligor;
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

	public Obligor getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<Position> getPositions() {
		return getInstance() == null ? null : new ArrayList<Position>(getInstance().getPositions());
	}

}
