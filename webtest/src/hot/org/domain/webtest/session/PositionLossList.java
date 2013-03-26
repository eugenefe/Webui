package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("positionLossList")
public class PositionLossList extends EntityQuery<PositionLoss> {

	private static final String EJBQL = "select positionLoss from PositionLoss positionLoss";

	private static final String[] RESTRICTIONS = {
			"lower(positionLoss.id.bssd) like lower(concat(#{positionLossList.positionLoss.id.bssd},'%'))",
			"lower(positionLoss.id.positionId) like lower(concat(#{positionLossList.positionLoss.id.positionId},'%'))",
			"lower(positionLoss.id.lossNo) like lower(concat(#{positionLossList.positionLoss.id.lossNo},'%'))", };

	private PositionLoss positionLoss;

	public PositionLossList() {
		positionLoss = new PositionLoss();
		positionLoss.setId(new PositionLossId());
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public PositionLoss getPositionLoss() {
		return positionLoss;
	}
}
