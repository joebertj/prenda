/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.pdf;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.prenda.helper.DatabaseConnection;
import com.prenda.helper.EnglishDecimalFormat;
import com.prenda.servlet.CheckPawn;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 * Servlet implementation class for Servlet: PawnTicketPdf
 *
 */
 public class PawnTicketPdf extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static Logger log =Logger.getLogger(PawnTicketPdf.class);
	private static final long serialVersionUID = 688550874715746378L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public PawnTicketPdf() {
		super();
	}   	 	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,Object> param=new HashMap<String,Object>();
		SimpleDateFormat sdf=new SimpleDateFormat("MMM dd, yyyy");
		Date pawn=null;
		Date maturity=null;
		Date expire=null;
		try {
			pawn = sdf.parse(request.getParameter("pawn"));
			maturity=sdf.parse(request.getParameter("maturity"));
			expire=sdf.parse(request.getParameter("expire"));
		} catch (ParseException e) {
			log.info(e.getMessage());
		}
		Integer pid=new Integer(request.getParameter("pid"));
		Integer bpid=new Integer(request.getParameter("bpid"));
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		Float appraised=new Float(request.getParameter("appraised"));
		Float loan=new Float(request.getParameter("loan"));
		Integer rate=new Integer(request.getParameter("rate"));
		EnglishDecimalFormat edf=new EnglishDecimalFormat();
		String appraisedw=edf.convert(appraised.intValue()) + " pesos only";
		String loanw=edf.convert(loan.intValue()) + " pesos only";
		String ratew=edf.convert(rate.intValue());
		Float interest=new Float(request.getParameter("interest"));
		Float sc=new Float(request.getParameter("sc"));
		Float net=new Float(request.getParameter("net"));
		String description=request.getParameter("description");
		String password=request.getParameter("password");
		String encoder=request.getParameter("encoder");
		Integer branch=new Integer(request.getParameter("branch"));
		param.put("pid",pid);
		param.put("bpid",bpid);
		param.put("pawn",pawn);
		param.put("maturity",maturity);
		param.put("expire",expire);
		param.put("name",name);
		param.put("address",address);
		param.put("loan",loan);
		param.put("loanw",loanw);
		param.put("appraised",appraised);
		param.put("appraisedw",appraisedw);
		param.put("rate",rate);
		param.put("ratew",ratew);
		param.put("interest",interest);
		param.put("sc",sc);
		param.put("net",net);
		param.put("description",description);
		param.put("password",password);
		param.put("encoder",encoder);
		param.put("branch",branch);
		try {
			Connection conn = DatabaseConnection.getConnection();
			String jasper = request.getSession().getServletContext().getRealPath("/common");
			JasperPrint jprint=JasperFillManager.fillReport(jasper+"/jasper/pawnticket.jasper",param,conn);
			OutputStream out=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jprint,out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}   	  	    
}