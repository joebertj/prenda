/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;

import com.prenda.helper.DateUtil;

public class GetDates extends BaseAjaxServlet {

	private static final long serialVersionUID = 10L;

	public String getXmlContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AjaxXmlBuilder xml=new AjaxXmlBuilder();
		String dateString=request.getParameter("dateString");
		String sdfIn=request.getParameter("sdfin");
		String sdfOut=request.getParameter("sdfout");
		DateUtil l=new DateUtil();
		l.setSdfIn(sdfIn);
		l.setSdfOut(sdfOut);
		l.setValue(dateString);
		xml.addItem("effective",l.getEffective());
		xml.addItem("maturity",l.getMaturity());
		xml.addItem("expiry",l.getExpiry());
		xml.addItem("month",l.getMonth());
		xml.addItem("year",l.getYear());
		return xml.toString();
	}
}