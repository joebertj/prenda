/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prenda.Mode;
import com.prenda.helper.DatabaseConnection;

/**
 * Servlet implementation class for Servlet: CashDisbursement
 *
 */
 public class CashDisbursement extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8127470001729455855L;
	private static Logger log = Logger.getLogger(CashDisbursement.class);
	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CashDisbursement() {
		super();
	}   	 	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		String redirectURL;
		if(session.isNew()){
			redirectURL = "/public/login.jsp";
			response.sendRedirect(redirectURL);
		}else{ 
			String authenticated=(String) session.getAttribute("authenticated");
			if(authenticated == null){
				redirectURL = "/public/login.jsp?msg=You have not logged in yet";
				response.sendRedirect(redirectURL);
			}else{
				continuePost(request, response, authenticated);
			}
		}
	}
	protected void continuePost(HttpServletRequest request, HttpServletResponse response, String encoder) throws ServletException, IOException {
		try{
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		String[] accountcode=(String []) request.getParameterValues("code");
    		int branchId=(new Integer(request.getParameter("branch"))).intValue();
    		String[] description=(String []) request.getParameterValues("particulars");
    		String[] amount=(String[]) request.getParameterValues("amount");
    		String journalGroup=branchId + new Long(new Date().getTime()/1000).toString(); // format branch id + date in epoch seconds
            log.info(journalGroup);
            Double totalAmount = 0d;
            pstmt = conn.prepareStatement("INSERT INTO journal VALUES (0,CURDATE(),?,?,?,?,?,?,?)");
            pstmt.setInt(2,branchId);
            pstmt.setBoolean(5, Mode.CREDIT); // add Expenses
            pstmt.setString(6,journalGroup);
			pstmt.setString(7,encoder);
    		for(int i=0;i<accountcode.length;i++){
    			pstmt.setInt(1,Integer.parseInt(accountcode[i]));
    			pstmt.setString(3,description[i]);
    			Double a = new Double(amount[i]);
    			pstmt.setDouble(4,a);
    			totalAmount += a;
    			pstmt.executeUpdate();
    		}
    		// Debit cash on hand
    		pstmt.setInt(1,10101);
    		pstmt.setString(3,"Total expenses for JG "+journalGroup);
    		pstmt.setDouble(4,totalAmount);
    		pstmt.setBoolean(5, Mode.DEBIT);
    		pstmt.executeUpdate();
    		// Update balance on branch table
    		pstmt = conn.prepareStatement("UPDATE branch SET balance=balance-? WHERE branchid=?");
			pstmt.setDouble(1,totalAmount);
			pstmt.setLong(2,branchId);
			pstmt.executeUpdate();
    		String redirectUrl="manage/cashdisbursementpdf.jsp?group=" + journalGroup;
    		response.sendRedirect(redirectUrl);
    	}catch (Exception e) {
            e.printStackTrace();
		}
    }   	  	    
}