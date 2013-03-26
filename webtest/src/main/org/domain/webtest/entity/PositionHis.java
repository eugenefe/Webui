package org.domain.webtest.entity;

// Generated Mar 26, 2013 10:42:49 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * PositionHis generated by hbm2java
 */
@Entity
@Table(name = "POSITION_HIS", schema = "TAKION77")
public class PositionHis implements java.io.Serializable {

	private PositionHisId id;
	private Position position;
	private BaseDate baseDate;
	private BigDecimal holdQty;
	private BigDecimal posAmt;
	private Set<Portfolio> portfolios = new HashSet<Portfolio>(0);
	private Set<PositionLoss> positionLosses = new HashSet<PositionLoss>(0);

	public PositionHis() {
	}

	public PositionHis(PositionHisId id, Position position, BaseDate baseDate) {
		this.id = id;
		this.position = position;
		this.baseDate = baseDate;
	}

	public PositionHis(PositionHisId id, Position position, BaseDate baseDate, BigDecimal holdQty, BigDecimal posAmt,
			Set<Portfolio> portfolios, Set<PositionLoss> positionLosses) {
		this.id = id;
		this.position = position;
		this.baseDate = baseDate;
		this.holdQty = holdQty;
		this.posAmt = posAmt;
		this.portfolios = portfolios;
		this.positionLosses = positionLosses;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bssd", column = @Column(name = "BSSD", nullable = false, length = 8)),
			@AttributeOverride(name = "positionId", column = @Column(name = "POSITION_ID", nullable = false, length = 50)) })
	@NotNull
	public PositionHisId getId() {
		return this.id;
	}

	public void setId(PositionHisId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POSITION_ID", nullable = false, insertable = false, updatable = false)
	@NotNull
	public Position getPosition() {
		return this.position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BSSD", nullable = false, insertable = false, updatable = false)
	@NotNull
	public BaseDate getBaseDate() {
		return this.baseDate;
	}

	public void setBaseDate(BaseDate baseDate) {
		this.baseDate = baseDate;
	}

	@Column(name = "HOLD_QTY", scale = 4)
	public BigDecimal getHoldQty() {
		return this.holdQty;
	}

	public void setHoldQty(BigDecimal holdQty) {
		this.holdQty = holdQty;
	}

	@Column(name = "POS_AMT", precision = 20)
	public BigDecimal getPosAmt() {
		return this.posAmt;
	}

	public void setPosAmt(BigDecimal posAmt) {
		this.posAmt = posAmt;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PORTFOLIO_DETAIL", schema = "TAKION77", joinColumns = {
			@JoinColumn(name = "BSSD", nullable = false, updatable = false),
			@JoinColumn(name = "POSITION_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "PORTFOLIO_ID", nullable = false, updatable = false) })
	public Set<Portfolio> getPortfolios() {
		return this.portfolios;
	}

	public void setPortfolios(Set<Portfolio> portfolios) {
		this.portfolios = portfolios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "positionHis")
	public Set<PositionLoss> getPositionLosses() {
		return this.positionLosses;
	}

	public void setPositionLosses(Set<PositionLoss> positionLosses) {
		this.positionLosses = positionLosses;
	}

}
