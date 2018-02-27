/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;

import com.prenda.helper.DateUtil;

public class GetDates extends BaseAjaxServlet {

	private static final long serialVersionUID = 10L;

	public String getXmlContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AjaxXmlBuilder xml=new AjaxXmlBuilder();
		String value=request.getParameter("date");
		String sdfIn=request.getParameter("sdfin");
		String sdfOut=request.getParameter("sdfout");
		DateUtil l=new DateUtil();
		l.setSdfIn(sdfIn);
		l.setSdfOut(sdfOut);
		l.setValue(value);
		xml.addItem("effective",l.getEffective());
		xml.addItem("maturity",l.getMaturity());
		xml.addItem("expiry",l.getExpiry());
		return xml.toString();
	}
}