package com.prenda.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;
import com.prenda.service.LoanService;

public class NetProceeds extends BaseAjaxServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6557460585280889926L;

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		double appraised = new Double(request.getParameter("appraised"));
		double advanceInterest = new Double(request.getParameter("advanceInterest")); 
		double serviceCharge = new Double(request.getParameter("serviceCharge"));
		double margin = new Double(request.getParameter("margin"));
		Double net, interest;
		LoanService ls = new LoanService();
		net = ls.getNetProceeds(appraised, advanceInterest, serviceCharge, margin);
		interest = ls.getInterest(appraised, advanceInterest, margin);
		AjaxXmlBuilder xml=new AjaxXmlBuilder();
		xml.addItem("net",net.toString());
		xml.addItem("loaninterest",interest.toString());
		return xml.toString();
	}
}
	
