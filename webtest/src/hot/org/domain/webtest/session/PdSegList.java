package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("pdSegList")
public class PdSegList extends EntityQuery<PdSeg> {

	private static final String EJBQL = "select pdSeg from PdSeg pdSeg";

	private static final String[] RESTRICTIONS = { "lower(pdSeg.id) like lower(concat(#{pdSegList.pdSeg.id},'%'))",
			"lower(pdSeg.name) like lower(concat(#{pdSegList.pdSeg.name},'%'))",
			"lower(pdSeg.groupId) like lower(concat(#{pdSegList.pdSeg.groupId},'%'))",
			"lower(pdSeg.rateCd) like lower(concat(#{pdSegList.pdSeg.rateCd},'%'))",
			"lower(pdSeg.indCd) like lower(concat(#{pdSegList.pdSeg.indCd},'%'))", };

	private PdSeg pdSeg = new PdSeg();

	public PdSegList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public PdSeg getPdSeg() {
		return pdSeg;
	}
}
