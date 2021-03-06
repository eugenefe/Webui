package org.domain.webtest.entity;

// Generated Mar 26, 2013 10:42:49 PM by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Position generated by hbm2java
 */
@Entity
@Table(name = "POSITION", schema = "TAKION77")
public class Position implements java.io.Serializable {

	private String id;
	private Obligor obligor;
	private Dealer dealer;
	private Product product;
	private AccountMst accountMst;
	private String name;
	private String colCd;
	private BigDecimal posAmt;
	private Set<PositionHis> positionHises = new HashSet<PositionHis>(0);

	public Position() {
	}

	public Position(String id) {
		this.id = id;
	}

	public Position(String id, Obligor obligor, Dealer dealer, Product product, AccountMst accountMst, String name,
			String colCd, BigDecimal posAmt, Set<PositionHis> positionHises) {
		this.id = id;
		this.obligor = obligor;
		this.dealer = dealer;
		this.product = product;
		this.accountMst = accountMst;
		this.name = name;
		this.colCd = colCd;
		this.posAmt = posAmt;
		this.positionHises = positionHises;
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
	@JoinColumn(name = "OBLIGOR_ID")
	public Obligor getObligor() {
		return this.obligor;
	}

	public void setObligor(Obligor obligor) {
		this.obligor = obligor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DEALER_ID")
	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	public AccountMst getAccountMst() {
		return this.accountMst;
	}

	public void setAccountMst(AccountMst accountMst) {
		this.accountMst = accountMst;
	}

	@Column(name = "NAME", length = 50)
	@Size(max = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "COL_CD", length = 20)
	@Size(max = 20)
	public String getColCd() {
		return this.colCd;
	}

	public void setColCd(String colCd) {
		this.colCd = colCd;
	}

	@Column(name = "POS_AMT", precision = 20)
	public BigDecimal getPosAmt() {
		return this.posAmt;
	}

	public void setPosAmt(BigDecimal posAmt) {
		this.posAmt = posAmt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "position")
	public Set<PositionHis> getPositionHises() {
		return this.positionHises;
	}

	public void setPositionHises(Set<PositionHis> positionHises) {
		this.positionHises = positionHises;
	}

}
