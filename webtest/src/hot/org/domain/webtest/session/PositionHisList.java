package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("positionHisList")
public class PositionHisList extends EntityQuery<PositionHis> {

	private static final String EJBQL = "select positionHis from PositionHis positionHis";

	private static final String[] RESTRICTIONS = {
			"lower(positionHis.id.bssd) like lower(concat(#{positionHisList.positionHis.id.bssd},'%'))",
			"lower(positionHis.id.positionId) like lower(concat(#{positionHisList.positionHis.id.positionId},'%'))", };

	private PositionHis positionHis;

	public PositionHisList() {
		positionHis = new PositionHis();
		positionHis.setId(new PositionHisId());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public PositionHis getPositionHis() {
		return positionHis;
	}
}
