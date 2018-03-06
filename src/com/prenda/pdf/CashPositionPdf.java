/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.pdf;


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
 * Servlet implementation class for Servlet: CashPosition
 *
 */
 public class CashPositionPdf extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -455239851284797271L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CashPositionPdf() {
		super();
	}  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> param=new HashMap<String,Object>();
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		Float bbalance=new Float(request.getParameter("bbalance"));
		Float pawn=new Float(request.getParameter("pawn"));
		Float redeem=new Float(request.getParameter("redeem"));
		Float principal=new Float(request.getParameter("principal"));
		Float interest=new Float(request.getParameter("interest"));
		Float receipt=new Float(request.getParameter("receipt"));
		Float disburse=new Float(request.getParameter("disburse"));
		Float inventory=new Float(request.getParameter("inventory"));
		Float asset=new Float(request.getParameter("asset"));
		Integer branch=new Integer(request.getParameter("branch"));
		param.put("name",name);
		param.put("address",address);
		param.put("bbalance",bbalance);
		param.put("pawn",pawn);
		param.put("redeem",redeem);
		param.put("principal",principal);
		param.put("interest",interest);
		param.put("receipt",receipt);
		param.put("disburse",disburse);
		param.put("inventory",inventory);
		param.put("asset",asset);
		param.put("branch",branch);
		String path = request.getSession().getServletContext().getRealPath("/common");
		param.put("logo", path+"/img/logo2.png");
		try {
			Connection conn = DatabaseConnection.getConnection();
			JasperPrint jprint=JasperFillManager.fillReport(path+"/jasper/cashposition.jasper",param,conn);
			OutputStream out=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jprint,out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}