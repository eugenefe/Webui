package com.eugenefe.entity;

// Generated Apr 16, 2013 7:33:55 PM by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Employee generated by hbm2java
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements java.io.Serializable {

	private String memberId;
	private Bizunit bizunit;
	private String memberName;
	private String memberType;

	public Employee() {
	}

	public Employee(String memberId) {
		this.memberId = memberId;
	}

	public Employee(String memberId, Bizunit bizunit, String memberName, String memberType) {
		this.memberId = memberId;
		this.bizunit = bizunit;
		this.memberName = memberName;
		this.memberType = memberType;
	}

	@Id
	@Column(name = "MEMBER_ID", nullable = false, length = 20)
	public String getMemberId() {
		return this.memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ORG_ID")
	public Bizunit getBizunit() {
		return this.bizunit;
	}

	public void setBizunit(Bizunit bizunit) {
		this.bizunit = bizunit;
	}

	@Column(name = "MEMBER_NAME", length = 50)
	public String getMemberName() {
		return this.memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Column(name = "MEMBER_TYPE", length = 10)
	public String getMemberType() {
		return this.memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

}