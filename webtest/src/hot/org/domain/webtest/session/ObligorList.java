package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("obligorList")
public class ObligorList extends EntityQuery<Obligor> {

	private static final String EJBQL = "select obligor from Obligor obligor";

	private static final String[] RESTRICTIONS = {
			"lower(obligor.id) like lower(concat(#{obligorList.obligor.id},'%'))",
			"lower(obligor.name) like lower(concat(#{obligorList.obligor.name},'%'))",
			"lower(obligor.rateCd) like lower(concat(#{obligorList.obligor.rateCd},'%'))",
			"lower(obligor.indCd) like lower(concat(#{obligorList.obligor.indCd},'%'))", };

	private Obligor obligor = new Obligor();

	public ObligorList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public Obligor getObligor() {
		return obligor;
	}
}
