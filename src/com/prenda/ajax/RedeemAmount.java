package com.prenda.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;
import com.prenda.service.LoanService;

public class RedeemAmount extends BaseAjaxServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6557460585280889926L;

	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		double loan = new Double(request.getParameter("loan"));
		double interestRate = new Double(request.getParameter("interestRate")); 
		Double net;
		LoanService ls = new LoanService();
		net = ls.getRedeemAmount(loan, interestRate);
		AjaxXmlBuilder xml=new AjaxXmlBuilder();
		xml.addItem("redeem",net.toString());
		return xml.toString();
	}
}
	
