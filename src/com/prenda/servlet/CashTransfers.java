/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prenda.helper.DatabaseConnection;

/**
 * Servlet implementation class for Servlet: CashTransfers
 *
 */
 public class CashTransfers extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8047276599854446297L;
	private static Logger log =Logger.getLogger(CashTransfers.class);
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CashTransfers() {
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
				continuePost(request, response);
			}
		}
	}
	protected void continuePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		int journalid=(new Integer(request.getParameter("journalid"))).intValue();
    		pstmt = conn.prepareStatement("SELECT * FROM journal WHERE journalid=?");
    		pstmt.setInt(1,journalid);
    		ResultSet rs=pstmt.executeQuery();
    		int accountid=0;
    		int branch=0;
    		float amount=0;
    		if(rs.first()){
    			accountid=rs.getInt(3);
    			branch=rs.getInt(4);
    			amount=rs.getFloat(6);
    		}else{
    			response.sendRedirect("transfercash.jsp?msg=No data exists");
    		}
    		PreparedStatement pstmt2;
    		if(accountid==13){
    			pstmt2 = conn.prepareStatement("UPDATE branch SET balance=balance-? WHERE branchid=?");
    			pstmt2.setFloat(1,amount);
    			pstmt2.setLong(2,branch);
    			pstmt2.executeUpdate();
    		}else if(accountid==12){
    			pstmt2 = conn.prepareStatement("UPDATE branch SET balance=balance+? WHERE branchid=?");
    			pstmt2.setFloat(1,amount);
    			pstmt2.setLong(2,branch);
    			pstmt2.executeUpdate();
    		}else{
    			response.sendRedirect("cashtransfer.jsp?msg=Invalid account");
    		}
    		HttpSession session=request.getSession(true);
    		String encoder=(String) session.getAttribute("authenticated");
    		pstmt = conn.prepareStatement("INSERT INTO ledger VALUES (?,CURDATE(),?)");
    		pstmt.setInt(1,journalid);
    		pstmt.setString(2,encoder);
    		pstmt.executeUpdate();
    		response.sendRedirect("cashtransfer.jsp?msg=Cash amounting to Php "+amount+" successfully transferred");
    	}catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		}
    }   	  	    
}