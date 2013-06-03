package com.eugenefe.controller;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;
import org.primefaces.model.chart.PieChartModel;

import com.eugenefe.entity.Portfolio;

@Name("portfolioChartBean")
@Scope(ScopeType.CONVERSATION)
public class PortfolioChartBean {
	@Logger
	private Log log;
	
	@In
	private PortfolioBean portfolioBean;
	
	
	private PieChartModel pieModel;

	public PortfolioChartBean() {
		System.out.println("Creation Chart");
		pieModel = new PieChartModel();  
//        pieModel.set("Brand 1", 540);  
//        pieModel.set("Brand 2", 325);  
//        pieModel.set("Brand 3", 702);  
//        pieModel.set("Brand 4", 421); 
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}
	
	@Observer("enterChart")
	public void loadChart(Portfolio port){
		log.info("in the load chart Event ");
//		log.info("in the load chart : #0", port.getChildPortfolios().size());
		pieModel = new PieChartModel();
		int temp =0;
		for(Portfolio aa : port.getChildPortfolios()){
			log.info("in the load chart11 ");
			if(aa.getChildPortfolios().size() ==0){
				log.info("in the load chart12  : #0" );
				temp = 5;
			}
			else{
				temp =aa.getChildPortfolios().size() *10;
				log.info("in the load chart13 : #0" , temp);
			}
			log.info("in the load chart14 :#0", aa.getPortId());
			pieModel.set(aa.getPortId(), temp );
			log.info("in the load chart15 ");
		}
		if(port.getChildPortfolios().size()==0){
			pieModel.set("There's No Data", 0 );
		}
//		log.info("in the load chart16 : #0" , pieModel.getData().get("PORT11").doubleValue());
	}
	
//	public void loadChart(){
//		log.info("in the load chart1 ");
//		int temp =0;
//		for(Portfolio aa : portfolioBean.getParentPortfolio().getChildPortfolios()){
//			log.info("in the load chart11 ");
//			if(aa.getChildPortfolios().size() ==0){
//				temp = 5;
//				log.info("in the load chart12 ");
//			}
//			else{
//				temp =aa.getChildPortfolios().size() *10;
//				log.info("in the load chart13 ");
//			}
//			log.info("in the load chart14 ");
//			pieModel.set(aa.getPortId(), temp );
//			log.info("in the load chart15 ");
//		}
//	}
	
	

}
