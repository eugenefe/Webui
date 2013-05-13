package com.eugenefe.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Events;
import org.jboss.seam.framework.CurrentDate;
import org.jboss.seam.log.Log;
import org.primefaces.event.SelectEvent;

import com.eugenefe.session.BasedateList;


@Name("basedateBean")
@Scope(ScopeType.SESSION)
//@Scope(ScopeType.CONVERSATION)
public class BaseDateBean implements Serializable {

	@Logger
	private Log log;
	
	@In(create = true)
	private BasedateList basedateList;

	private Date date1;
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
	
	@Out(scope=ScopeType.SESSION)
	private String bssd;
	
	public BaseDateBean() {
	}

	@Create
	public void init(){
		date1 = Calendar.getInstance().getTime();
//		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");  
	    bssd = format.format(date1);
//	    bssd =  "20130304";
					
	}
	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
		
	}
	
	public String getBssd() {
		return bssd;
	}

	public void setBssd(String bssd) {
		this.bssd = bssd;
	}

	public void handleDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();  
        bssd = format.format(event.getObject());
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", bssd));
        
        Events.instance().raiseEvent("changeBssd", bssd);
	}

	
}
