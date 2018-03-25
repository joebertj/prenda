/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet.pdf;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.prenda.helper.DatabaseConnection;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Servlet implementation class for Servlet: RedeemDailyPdf
 *
 */
 public class RedeemDailyPdf extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3026327077102669108L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RedeemDailyPdf() {
		super();
	}   	 	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> param=new HashMap<String,Object>();
		Integer branch=new Integer(request.getParameter("branch"));
		String bname=request.getParameter("bname");
		String baddress=request.getParameter("baddress");
		param.put("branch",branch);
		param.put("name",bname);
		param.put("address",baddress);
		String path = request.getSession().getServletContext().getRealPath("/resources");
		param.put("logo", path+"/img/logo.png");
		try {
			Connection conn = DatabaseConnection.getConnection();
			JasperPrint jprint=JasperFillManager.fillReport(path+"/jasper/redeemitemsdaily.jasper",param,conn);
			OutputStream out=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jprint,out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}   	  	    
}