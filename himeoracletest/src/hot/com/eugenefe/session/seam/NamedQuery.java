package com.eugenefe.session.seam;

public enum NamedQuery {
	PortfolioJoinReturnBssd(
			"select a from Portfolio a " 
			+ "join fetch a.portfolioReturns b "
			+ "where b.id.bssd = #{basedateBean.bssd} "),
			
	Position("select a from Position a "),

	PositionReturn("select a from PositionReturn a where a.id.bssd = #{basedateBean.bssd}")
	
	;
	
	private String query;
	
	private NamedQuery(String query) {
		this.query = query;
	}
	public String getQuery() {
		return this.query;
	}
}
