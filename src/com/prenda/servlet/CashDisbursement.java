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
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

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
	private static Logger log =Logger.getLogger(CashDisbursement.class);
	

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
				continuePost(request, response);
			}
		}
	}
	protected void continuePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		String[] accountcode=(String []) request.getParameterValues("code");
    		int branchid=(new Integer(request.getParameter("branch"))).intValue();
    		String[] description=(String []) request.getParameterValues("particulars");
    		String[] amount=(String[]) request.getParameterValues("amount");
    		String[] accountname=new String[accountcode.length];
    		ResultSet rs=null;
    		String journalGroup=new Long(new Date().getTime()).toString()+new Integer(accountcode.length).toString();
            log.info(journalGroup);
    		for(int i=0;i<accountcode.length;i++){
    			pstmt = conn.prepareStatement("SELECT accountid,accountname from accounts where accountcode=?");
    			pstmt.setInt(1,new Integer(accountcode[i]).intValue());
    			int accountid=0;
    			rs=pstmt.executeQuery();
    			if(rs.first()){
    				accountid=rs.getInt(1);
    				accountname[i]=rs.getString(2);
    			}else{
    				log.info(accountcode+"--"+accountid);
    			}
    			pstmt = conn.prepareStatement("INSERT INTO journal VALUES (0,CURDATE(),?,?,?,?,?)");
    			pstmt.setInt(1,accountid);
    			pstmt.setInt(2,branchid);
    			pstmt.setString(3,description[i]);
    			pstmt.setFloat(4,new Float(amount[i]).floatValue());
    			pstmt.setString(5,journalGroup);
    			pstmt.executeUpdate();
    			pstmt = conn.prepareStatement("UPDATE branch SET balance=balance-? WHERE branchid=?");
    			pstmt.setFloat(1,new Float(amount[i]).floatValue());
    			pstmt.setLong(2,branchid);
    			pstmt.executeUpdate();
    		}
    		pstmt = conn.prepareStatement("SELECT journalid FROM journal WHERE journal_group=?");
    		pstmt.setString(1,journalGroup);
    		rs=pstmt.executeQuery();
    		int journalid=0;
    		while(rs.next()){
    			journalid=rs.getInt(1);
    			HttpSession session=request.getSession(true);
        		String encoder=(String) session.getAttribute("authenticated");
        		pstmt = conn.prepareStatement("INSERT INTO ledger VALUES (?,CURDATE(),?)");
        		pstmt.setInt(1,journalid);
        		pstmt.setString(2,encoder);
        		pstmt.executeUpdate();
        	}
    		String redirectUrl="cashdisbursementpdf.jsp?group=" + journalGroup;
    		response.sendRedirect(redirectUrl);
    	}catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		}
    }   	  	    
}