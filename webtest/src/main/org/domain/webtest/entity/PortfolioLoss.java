package org.domain.webtest.entity;

// Generated Mar 26, 2013 10:42:49 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * PortfolioLoss generated by hbm2java
 */
@Entity
@Table(name = "PORTFOLIO_LOSS", schema = "TAKION77")
public class PortfolioLoss implements java.io.Serializable {

	private PortfolioLossId id;
	private PortfolioHis portfolioHis;
	private BigDecimal lossAmt;
	private BigDecimal lossProb;

	public PortfolioLoss() {
	}

	public PortfolioLoss(PortfolioLossId id, PortfolioHis portfolioHis) {
		this.id = id;
		this.portfolioHis = portfolioHis;
	}

	public PortfolioLoss(PortfolioLossId id, PortfolioHis portfolioHis, BigDecimal lossAmt, BigDecimal lossProb) {
		this.id = id;
		this.portfolioHis = portfolioHis;
		this.lossAmt = lossAmt;
		this.lossProb = lossProb;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "bssd", column = @Column(name = "BSSD", nullable = false, length = 8)),
			@AttributeOverride(name = "portfolioId", column = @Column(name = "PORTFOLIO_ID", nullable = false, length = 50)),
			@AttributeOverride(name = "lossId", column = @Column(name = "LOSS_ID", nullable = false, length = 20)) })
	@NotNull
	public PortfolioLossId getId() {
		return this.id;
	}

	public void setId(PortfolioLossId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "BSSD", referencedColumnName = "BSSD", nullable = false, insertable = false, updatable = false),
			@JoinColumn(name = "PORTFOLIO_ID", referencedColumnName = "PORTFOLIO_ID", nullable = false, insertable = false, updatable = false) })
	@NotNull
	public PortfolioHis getPortfolioHis() {
		return this.portfolioHis;
	}

	public void setPortfolioHis(PortfolioHis portfolioHis) {
		this.portfolioHis = portfolioHis;
	}

	@Column(name = "LOSS_AMT", precision = 20)
	public BigDecimal getLossAmt() {
		return this.lossAmt;
	}

	public void setLossAmt(BigDecimal lossAmt) {
		this.lossAmt = lossAmt;
	}

	@Column(name = "LOSS_PROB", precision = 10, scale = 8)
	public BigDecimal getLossProb() {
		return this.lossProb;
	}

	public void setLossProb(BigDecimal lossProb) {
		this.lossProb = lossProb;
	}

}
