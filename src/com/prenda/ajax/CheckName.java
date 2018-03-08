/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.ajax;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;

import com.prenda.Mode;
import com.prenda.Name;
import com.prenda.service.NameService;

public class CheckName extends BaseAjaxServlet {

	private static final long serialVersionUID = -9122903253787134667L;

	public String getXmlContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String lname = request.getParameter("lname");
		String fname = request.getParameter("fname");
		String mname = request.getParameter("mname");
		if(lname==null){
			lname="";
		} 
		if(fname==null){
			fname="";
		}
		if(mname==null){
			mname="";
		}
		int ntype=new Integer(request.getParameter("ntype")).intValue();
		NameService service = new NameService();
		List<Name> list = service.getNamesByPrefix(lname,fname,mname);
		AjaxXmlBuilder xml=new AjaxXmlBuilder();
		if(ntype==Mode.LASTNAME){
			xml.addItems(list, "lname", "nid");
		}else if(ntype==Mode.FIRSTNAME){
			xml.addItems(list, "fname", "nid");
		}else if(ntype==Mode.MIDDLENAME){
			xml.addItems(list, "mname", "nid");
		}
		return xml.toString();
	}
}