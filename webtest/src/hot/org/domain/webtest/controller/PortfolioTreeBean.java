package org.domain.webtest.controller;

import java.util.ArrayList;
import java.util.List;

import org.domain.webtest.entity.Portfolio;
import org.domain.webtest.session.PortfolioList;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;


@Name("portfolioTree")
@Scope(ScopeType.CONVERSATION)
public class PortfolioTreeBean {

	@Logger
	private Log log;
	// @In
	// private FacesMessage facesMessages;
	// @In
	// EntityManager entityManager;

	private TreeNode portfolioRoot;

	@In(create = true)
	private PortfolioList portfolioList;

	public PortfolioTreeBean() {
		portfolioRoot = new DefaultTreeNode("Root", null);
		// TreeNode childNode = new DefaultTreeNode("AAA", portfolioRoot);
		// TreeNode childNode2 = new DefaultTreeNode("BBB", portfolioRoot);
		// for (Portfolio aa : portfolioList.getResultList()) {
		// TreeNode childNode3 = new DefaultTreeNode(aa.getId(), portfolioRoot);
		// }
		// log.debug("TEst");
	}

	// @Factory(autoCreate= true, value ="portfolioRoot")
	public void initTree() {
		// log.debug("TEst2");
		portfolioRoot = new DefaultTreeNode("Root", null);
		// TreeNode childNode1 = new DefaultTreeNode("AAA", portfolioRoot);
		for (Portfolio aa : portfolioList.getResultList()) {
			// System.out.println(aa.getId());
			TreeNode childNode = new DefaultTreeNode(aa.getId(), portfolioRoot);
		}

	}

	public TreeNode getPortfolioRoot() {
		if (portfolioRoot.getChildCount() == 0) {
			for (Portfolio aa : portfolioList.getResultList()) {
				// System.out.println(aa.getId());
				TreeNode childNode = new DefaultTreeNode(aa.getId(), portfolioRoot);
//				log.warn(aa.getId(), aa.getParentPortId());
			}
		}
		log.warn("Test{}");
		return portfolioRoot;
	}

	public void recursive(List<Portfolio> port, String id, TreeNode node) {
		List<Portfolio> subList2 = new ArrayList<Portfolio>();
		subList2 = subKategori(id);
		for (Portfolio k : subList2) {
			TreeNode childNode = new DefaultTreeNode(k.getId(), node);
			recursive(subList2, k.getId(), childNode);
		}
	}

	public static List<Portfolio> subKategori(String id) {
		List<Portfolio> subList = new ArrayList<Portfolio>();
		for (Portfolio k : subList) {
			if (k.getId() == "") {
				subList.add(k);
			}
		}
		return subList;
	}

}
