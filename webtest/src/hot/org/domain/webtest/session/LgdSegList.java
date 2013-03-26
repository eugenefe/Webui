package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("lgdSegList")
public class LgdSegList extends EntityQuery<LgdSeg> {

	private static final String EJBQL = "select lgdSeg from LgdSeg lgdSeg";

	private static final String[] RESTRICTIONS = { "lower(lgdSeg.id) like lower(concat(#{lgdSegList.lgdSeg.id},'%'))",
			"lower(lgdSeg.name) like lower(concat(#{lgdSegList.lgdSeg.name},'%'))",
			"lower(lgdSeg.groupId) like lower(concat(#{lgdSegList.lgdSeg.groupId},'%'))",
			"lower(lgdSeg.rateCd) like lower(concat(#{lgdSegList.lgdSeg.rateCd},'%'))",
			"lower(lgdSeg.collateralCd) like lower(concat(#{lgdSegList.lgdSeg.collateralCd},'%'))", };

	private LgdSeg lgdSeg = new LgdSeg();

	public LgdSegList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public LgdSeg getLgdSeg() {
		return lgdSeg;
	}
}
