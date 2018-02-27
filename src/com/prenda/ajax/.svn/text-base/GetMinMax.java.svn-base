package com.prenda.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;

import com.prenda.service.JewelryService;

public class GetMinMax extends BaseAjaxServlet {

	private static final long serialVersionUID = -2697129944999106501L;

	@Override
	public String getXmlContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JewelryService service = new JewelryService();
		int branchId = new Integer(request.getParameter("branchid"));
		int caratId = new Integer(request.getParameter("carats"));
		service.setBranchId(branchId);
		service.setCaratId(caratId);
		AjaxXmlBuilder xml=new AjaxXmlBuilder();
		xml.addItem("min", new Double(service.getMinimum()).toString());
		xml.addItem("max", new Double(service.getMaximum()).toString());
		return xml.toString();
	}

}
