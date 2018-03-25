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

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Strings;
import com.prenda.helper.DatabaseConnection;
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
		String pawn=null;
		String maturity=null;
		String expire=null;
		pawn = request.getParameter("pawn");
		maturity=request.getParameter("maturity");
		expire=request.getParameter("expire");
		String pid=Strings.padStart(request.getParameter("pid"), 10, '0');
		String bpid=Strings.padStart(request.getParameter("bpid"), 6, '0');
		String name=WordUtils.capitalize(request.getParameter("name"));
		String address=WordUtils.capitalize(request.getParameter("address"));
		Float appraised=new Float(request.getParameter("appraised"));
		Float loan=new Float(request.getParameter("loan"));
		Float rate=new Float(request.getParameter("rate"));
		String appraisedw=WordUtils.capitalize(request.getParameter("appraisedw"));
		String loanw=WordUtils.capitalize(request.getParameter("loanw"));
		String ratew=WordUtils.capitalize(request.getParameter("ratew"));
		Float interest=new Float(request.getParameter("interest"));
		Float sc=new Float(request.getParameter("sc"));
		Float net=new Float(request.getParameter("net"));
		String description=request.getParameter("description");
		String password=request.getParameter("password");
		String encoder=request.getParameter("encoder");
		String branch=Strings.padStart(request.getParameter("branch"), 4, '0');
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
		log.info("pid "+pid);
		log.info("bpid "+bpid);
		log.info("pawn "+pawn);
		log.info("maturity "+maturity);
		log.info("expire "+expire);
		log.info("name "+name);
		log.info("address "+address);
		log.info("loan "+loan);
		log.info("loanw "+loanw);
		log.info("appraised "+appraised);
		log.info("appraisedw "+appraisedw);
		log.info("rate "+rate);
		log.info("ratew "+ratew);
		log.info("interest "+interest);
		log.info("sc "+sc);
		log.info("net "+net);
		log.info("description "+description);
		log.info("password "+password);
		log.info("encoder "+encoder);
		log.info("branch "+branch);
		try {
			Connection conn = DatabaseConnection.getConnection();
			String path = request.getSession().getServletContext().getRealPath("/resources");
			String print=request.getParameter("print");
			JasperPrint jprint;
			if(print.equals("Print on Empty Paper")) {
				param.put("logo", path+"/img/logo.png");
				jprint=JasperFillManager.fillReport(path+"/jasper/pawnticketverbose.jasper",param,conn);
			}else {
				jprint=JasperFillManager.fillReport(path+"/jasper/pawnticket.jasper",param,conn);
			}
			OutputStream out=response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jprint,out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}   	  	    
}