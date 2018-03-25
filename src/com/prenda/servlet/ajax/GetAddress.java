/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet.ajax;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;

import com.prenda.Name;
import com.prenda.service.NameService;

public class GetAddress extends BaseAjaxServlet {

	private static final long serialVersionUID = -9122903253787134667L;

	public String getXmlContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int nid = new Integer(request.getParameter("nid")).intValue();
		NameService service = new NameService();
		List<Name> list = service.getAddressById(nid);
		AjaxXmlBuilder xml=new AjaxXmlBuilder();
		xml.addItems(list, "aid", "address");
		return xml.toString();
	}

}