/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Mode;
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
				continuePost(request, response,authenticated);
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
							continuePost(request, response, authenticated);
						}else{
							redirectURL = "/public/login.jsp?msg=You do not own either the sending or receiving branch";
							response.sendRedirect(redirectURL);
						}
					}else{
						redirectURL = "/public/login.jsp?msg=You do not own either the sending or receiving branch";
						response.sendRedirect(redirectURL);
					}
				}catch (Exception e) {
		            e.printStackTrace();
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
	protected void continuePost(HttpServletRequest request, HttpServletResponse response, String authenticated) throws ServletException, IOException {
		try{
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		ResultSet rs=null;
    		String frombranch=request.getParameter("frombranch");
    		String tobranch=request.getParameter("tobranch");
    		int fromBranch=(new Integer(frombranch)).intValue();
    		int toBranch=(new Integer(tobranch)).intValue();
    		double amount=(new Double(request.getParameter("amount"))).floatValue();
    		pstmt = conn.prepareStatement("SELECT name from branch WHERE branchid=?"); // get from branch id
    		pstmt.setInt(1,fromBranch);
    		rs=pstmt.executeQuery();
    		if(rs.first()){
    			frombranch=rs.getString(1);
    		}
    		pstmt = conn.prepareStatement("SELECT name from branch WHERE branchid=?"); // get to branch id
    		pstmt.setInt(1,toBranch);
    		rs=pstmt.executeQuery();
    		if(rs.first()){
    			tobranch=rs.getString(1);
    		}
    		pstmt = conn.prepareStatement("UPDATE branch set balance=balance-? WHERE branchid=?"); //update from balance 
    		pstmt.setDouble(1, amount);
    		pstmt.setInt(2,fromBranch);
    		pstmt.executeUpdate();
    		pstmt = conn.prepareStatement("UPDATE branch set balance=balance+? WHERE branchid=?"); //update from balance 
    		pstmt.setDouble(1, amount);
    		pstmt.setInt(2,toBranch);
    		pstmt.executeUpdate();
    		pstmt = conn.prepareStatement("INSERT INTO journal VALUES (0,CURDATE(),?,?,?,?,?,?,?)"); // generic journal sql 
    		Long date = new Long(new Date().getTime()/1000);
    		String journalGroup = fromBranch + date.toString(); // format branch id + date in epoch seconds
    		// add accounting entry for FROM branch credit AR 
    		pstmt.setInt(1, 103);
    		pstmt.setInt(2,fromBranch);
    		pstmt.setString(3,"Loaned cash to "+ tobranch);
    		pstmt.setDouble(4,amount);
    		pstmt.setBoolean(5, Mode.CREDIT);
    		pstmt.setString(6, journalGroup);
    		pstmt.setString(7, authenticated);
    		pstmt.executeUpdate();
    		// add accounting entry for FROM branch debit Cash
    		pstmt.setInt(1, 10101);
    		pstmt.setInt(2,fromBranch);
    		pstmt.setString(3,"Loaned cash to "+ tobranch);
    		pstmt.setDouble(4,amount);
    		pstmt.setBoolean(5, Mode.DEBIT);
    		pstmt.setString(6, journalGroup);
    		pstmt.setString(7, authenticated);
    		pstmt.executeUpdate();
    		journalGroup = toBranch + date.toString();
    		// add accounting entry for TO branch credit AP 
    		pstmt.setInt(1, 201);
    		pstmt.setInt(2,toBranch);
    		pstmt.setString(3,"Loaned cash from "+ frombranch);
    		pstmt.setDouble(4,amount);
    		pstmt.setBoolean(5, Mode.CREDIT);
    		pstmt.setString(6, journalGroup);
    		pstmt.setString(7, authenticated);
    		pstmt.executeUpdate();
    		// add accounting entry for TO branch credit Cash
    		pstmt.setInt(1, 10101);
    		pstmt.setInt(2,toBranch);
    		pstmt.setString(3,"Loaned cash from "+ frombranch);
    		pstmt.setDouble(4,amount);
    		pstmt.setBoolean(5, Mode.CREDIT);
    		pstmt.setString(6, journalGroup);
    		pstmt.setString(7, authenticated);
    		pstmt.executeUpdate();
    		response.sendRedirect("common/cashtransfer.jsp?msg=Cash amounting to Php "+amount+" successfully journalled");
    	}catch (Exception e) {
            e.printStackTrace();
		}
    }
}