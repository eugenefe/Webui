package org.domain.webtest.session;

import org.domain.webtest.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("accountMstList")
public class AccountMstList extends EntityQuery<AccountMst> {

	private static final String EJBQL = "select accountMst from AccountMst accountMst";

	private static final String[] RESTRICTIONS = {
			"lower(accountMst.id) like lower(concat(#{accountMstList.accountMst.id},'%'))",
			"lower(accountMst.name) like lower(concat(#{accountMstList.accountMst.name},'%'))", };

	private AccountMst accountMst = new AccountMst();

	public AccountMstList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AccountMst getAccountMst() {
		return accountMst;
	}
}
