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
import org.springframework.transaction.annotation.Transactional;

import com.prenda.helper.DatabaseConnection;
import com.prenda.service.UserService;

/**
 * Servlet implementation class for Servlet: TransferCash
 *
 */
 public class TransferCash extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5026356649506411640L;
	private static Logger log =Logger.getLogger(TransferCash.class);
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public TransferCash() {
		super();
	}   	 	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Transactional
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		String redirectURL;
		if(session.isNew()){
			redirectURL = "/public/login.jsp";
			response.sendRedirect(redirectURL);
		}else{ 
			String authenticated=(String) session.getAttribute("authenticated");
			UserService us = new UserService();
			int level= us.getUser(authenticated).getLevel();
			if(authenticated == null){
				redirectURL = "/public/login.jsp?msg=You have not logged in yet";
				response.sendRedirect(redirectURL);
			}else if(level==9){
				continuePost(request, response);
			}else if(level==8){
				try{
					Connection conn = DatabaseConnection.getConnection();
					PreparedStatement pstmt = null;
					ResultSet rs=null;
					int f=(new Integer(request.getParameter("frombranch"))).intValue();
					int t=(new Integer(request.getParameter("tobranch"))).intValue();
					pstmt = conn.prepareStatement("SELECT branchid FROM branch LEFT JOIN users ON branch.owner=users.uid WHERE users.username=? AND (branchid=? OR branchid=?)");
					pstmt.setString(1,authenticated);
					pstmt.setInt(2,f);
					pstmt.setInt(3,t);
					rs=pstmt.executeQuery();
					if(rs.first()){
						if(rs.next()){
							continuePost(request, response);
						}else{
							redirectURL = "/public/login.jsp?msg=You do not own either the sending or receiving branch";
							response.sendRedirect(redirectURL);
						}
					}else{
						redirectURL = "/public/login.jsp?msg=You do not own either the sending or receiving branch";
						response.sendRedirect(redirectURL);
					}
				}catch (SQLException ex) {
	            	log.info("SQLException: " + ex.getMessage());
	            	log.info("SQLState: " + ex.getSQLState());
	            	log.info("VendorError: " + ex.getErrorCode());
				}
			}else if(level<8){
				redirectURL = "/public/login.jsp?msg=You are not an owner or administrator";
				response.sendRedirect(redirectURL);
			}else{
				redirectURL = "/public/login.jsp?msg=You don't have access rights";
				response.sendRedirect(redirectURL);
			}
		}
	}
	protected void continuePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		ResultSet rs=null;
    		String frombranch="";
    		String tobranch="";
    		int f=(new Integer(request.getParameter("frombranch"))).intValue();
    		int t=(new Integer(request.getParameter("tobranch"))).intValue();
    		float amount=(new Float(request.getParameter("amount"))).floatValue();
    		pstmt = conn.prepareStatement("SELECT name from branch WHERE branchid=?");
    		pstmt.setInt(1,f);
    		rs=pstmt.executeQuery();
    		if(rs.first()){
    			frombranch=rs.getString(1);
    		}
    		pstmt = conn.prepareStatement("SELECT name from branch WHERE branchid=?");
    		pstmt.setInt(1,t);
    		rs=pstmt.executeQuery();
    		if(rs.first()){
    			tobranch=rs.getString(1);
    		}
    		pstmt = conn.prepareStatement("INSERT INTO journal VALUES (0,CURDATE(),12,?,?,?,0)");
    		String desc="Cash from " + frombranch;
    		pstmt.setInt(1,t);
    		pstmt.setString(2,desc);
    		pstmt.setFloat(3,amount);
    		pstmt.executeUpdate();
    		pstmt = conn.prepareStatement("INSERT INTO journal VALUES (0,CURDATE(),13,?,?,?,0)");
    		desc="Cash to " + tobranch;
    		pstmt.setInt(1,f);
    		pstmt.setString(2,desc);
    		pstmt.setFloat(3,amount);
    		pstmt.executeUpdate();
    		response.sendRedirect("admin/cashtransfer.jsp?msg=Cash amounting to Php "+amount+" successfully journalled");
    	}catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		}
    }
}