package org.domain.webtest.entity;

// Generated Mar 26, 2013 10:42:49 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Portfolio generated by hbm2java
 */
@Entity
@Table(name = "PORTFOLIO", schema = "TAKION77")
public class Portfolio implements java.io.Serializable {

	private String id;
	private Portfolio portfolio;
	private String name;
	private String groupId;
	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	private Set<PositionHis> positionHises = new HashSet<PositionHis>(0);
	private Set<Portfolio> portfolios = new HashSet<Portfolio>(0);
	private Set<PortfolioHis> portfolioHises = new HashSet<PortfolioHis>(0);

	public Portfolio() {
	}

	public Portfolio(String id) {
		this.id = id;
	}

	public Portfolio(String id, Portfolio portfolio, String name, String groupId, String level1, String level2,
			String level3, String level4, String level5, Set<PositionHis> positionHises, Set<Portfolio> portfolios,
			Set<PortfolioHis> portfolioHises) {
		this.id = id;
		this.portfolio = portfolio;
		this.name = name;
		this.groupId = groupId;
		this.level1 = level1;
		this.level2 = level2;
		this.level3 = level3;
		this.level4 = level4;
		this.level5 = level5;
		this.positionHises = positionHises;
		this.portfolios = portfolios;
		this.portfolioHises = portfolioHises;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 50)
	@NotNull
	@Size(max = 50)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_PORT_ID")
	public Portfolio getPortfolio() {
		return this.portfolio;
	}

	public void setPortfolio(Portfolio portfolio) {
		this.portfolio = portfolio;
	}

	@Column(name = "NAME", length = 50)
	@Size(max = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "GROUP_ID", length = 20)
	@Size(max = 20)
	public String getGroupId() {
		return this.groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Column(name = "LEVEL1", length = 20)
	@Size(max = 20)
	public String getLevel1() {
		return this.level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	@Column(name = "LEVEL2", length = 20)
	@Size(max = 20)
	public String getLevel2() {
		return this.level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

	@Column(name = "LEVEL3", length = 20)
	@Size(max = 20)
	public String getLevel3() {
		return this.level3;
	}

	public void setLevel3(String level3) {
		this.level3 = level3;
	}

	@Column(name = "LEVEL4", length = 20)
	@Size(max = 20)
	public String getLevel4() {
		return this.level4;
	}

	public void setLevel4(String level4) {
		this.level4 = level4;
	}

	@Column(name = "LEVEL5", length = 20)
	@Size(max = 20)
	public String getLevel5() {
		return this.level5;
	}

	public void setLevel5(String level5) {
		this.level5 = level5;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "portfolios")
	public Set<PositionHis> getPositionHises() {
		return this.positionHises;
	}

	public void setPositionHises(Set<PositionHis> positionHises) {
		this.positionHises = positionHises;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
	public Set<Portfolio> getPortfolios() {
		return this.portfolios;
	}

	public void setPortfolios(Set<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
	public Set<PortfolioHis> getPortfolioHises() {
		return this.portfolioHises;
	}

	public void setPortfolioHises(Set<PortfolioHis> portfolioHises) {
		this.portfolioHises = portfolioHises;
	}

}