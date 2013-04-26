package com.eugenefe.entity;

// Generated Apr 16, 2013 7:33:55 PM by Hibernate Tools 4.0.0

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

/**
 * Bizunit generated by hbm2java
 */
@Entity
@Table(name = "BIZUNIT")
public class Bizunit implements java.io.Serializable {

	private String orgId;
	private Bizunit parentBizunit;
	private String orgName;
	private Set<Employee> employees = new HashSet<Employee>(0);
	private Set<Bizunit> childBizunits = new HashSet<Bizunit>(0);

	public Bizunit() {
	}

	public Bizunit(String orgId) {
		this.orgId = orgId;
	}

	public Bizunit(String orgId, Bizunit parentBizunit, String orgName, Set<Employee> employees,
			Set<Bizunit> childBizunits) {
		this.orgId = orgId;
		this.parentBizunit = parentBizunit;
		this.orgName = orgName;
		this.employees = employees;
		this.childBizunits = childBizunits;
	}

	@Id
	@Column(name = "ORG_ID", nullable = false, length = 20)
	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ORG_ID")
	public Bizunit getParentBizunit() {
		return this.parentBizunit;
	}

	public void setParentBizunit(Bizunit parentBizunit) {
		this.parentBizunit = parentBizunit;
	}

	@Column(name = "ORG_NAME", length = 50)
	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bizunit")
	public Set<Employee> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentBizunit")
	public Set<Bizunit> getChildBizunits() {
		return this.childBizunits;
	}

	public void setChildBizunits(Set<Bizunit> childBizunits) {
		this.childBizunits = childBizunits;
	}

}
