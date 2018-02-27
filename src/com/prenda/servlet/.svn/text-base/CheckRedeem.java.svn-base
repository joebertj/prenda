/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prenda.helper.DatabaseConnection;

/**
 * Servlet implementation class for Servlet: CheckRedeem
 *
 */
 public class CheckRedeem extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 963053242127001654L;
	private static Logger log =Logger.getLogger(CheckRedeem.class);

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CheckRedeem() {
		super();
	}   	 	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		if(session.isNew()){
			String redirectURL = "common/login.jsp";
			response.sendRedirect(redirectURL);
		}else{ 
			String authenticated=(String) session.getAttribute("authenticated");
			if(authenticated == null){
				String redirectURL = "common/login.jsp";
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
    		long pid=new Long(request.getParameter("pid")).intValue();
			int branch=new Integer(request.getParameter("branch")).intValue();
			SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
			Date redeemDate = new Date(0);
			try {
				redeemDate = new Date(sdf.parse(request.getParameter("redeemdate")).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			float interest=new Float(request.getParameter("interest")).floatValue();
			float net=new Float(request.getParameter("net")).floatValue();
			HttpSession session=request.getSession(true);
    		String encoder=(String) session.getAttribute("authenticated");
    		pstmt = conn.prepareStatement("INSERT INTO redeem VALUES (?,null,?,?,?)");
    		pstmt.setLong(1,pid);
    		pstmt.setDate(2,redeemDate);
    		pstmt.setString(3,encoder);
    		pstmt.setFloat(4,interest);
    		pstmt.executeUpdate();
    		pstmt = conn.prepareStatement("UPDATE branch SET balance=balance+? WHERE branchid=?");
    		pstmt.setFloat(1,net);
    		pstmt.setInt(2,branch);
    		pstmt.executeUpdate();
    		pstmt = conn.prepareStatement("UPDATE users SET redeem_date=? WHERE username=?");
    		pstmt.setDate(1,redeemDate);
    		pstmt.setString(2,encoder);
    		pstmt.executeUpdate();
    		response.sendRedirect("redeem.jsp?msg=Pawn with effective PID "+pid+" successfully redeemed");
    	}catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		}
	}   	  	    
}