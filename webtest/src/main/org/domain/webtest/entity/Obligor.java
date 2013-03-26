package org.domain.webtest.entity;

// Generated Mar 26, 2013 10:42:49 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Obligor generated by hbm2java
 */
@Entity
@Table(name = "OBLIGOR", schema = "TAKION77")
public class Obligor implements java.io.Serializable {

	private String id;
	private String name;
	private String rateCd;
	private String indCd;
	private Set<Position> positions = new HashSet<Position>(0);

	public Obligor() {
	}

	public Obligor(String id) {
		this.id = id;
	}

	public Obligor(String id, String name, String rateCd, String indCd, Set<Position> positions) {
		this.id = id;
		this.name = name;
		this.rateCd = rateCd;
		this.indCd = indCd;
		this.positions = positions;
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

	@Column(name = "NAME", length = 50)
	@Size(max = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "RATE_CD", length = 10)
	@Size(max = 10)
	public String getRateCd() {
		return this.rateCd;
	}

	public void setRateCd(String rateCd) {
		this.rateCd = rateCd;
	}

	@Column(name = "IND_CD", length = 20)
	@Size(max = 20)
	public String getIndCd() {
		return this.indCd;
	}

	public void setIndCd(String indCd) {
		this.indCd = indCd;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "obligor")
	public Set<Position> getPositions() {
		return this.positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

}
