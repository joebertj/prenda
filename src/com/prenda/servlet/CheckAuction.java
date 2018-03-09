/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prenda.helper.DatabaseConnection;
import com.prenda.service.UserService;

/**
 * Servlet implementation class for Servlet: CheckAuction
 *
 */
public class CheckAuction extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	private static Logger log =Logger.getLogger(CheckAuction.class);

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CheckAuction() {
		super();
	}   	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		if(session.isNew()){
			String redirectURL = "/common/login.jsp";
			response.sendRedirect(redirectURL);
		}else{ 
			String authenticated=(String) session.getAttribute("authenticated");
			if(authenticated == null){
				String redirectURL = "/common/login.jsp";
				response.sendRedirect(redirectURL);
			}else{
				continuePost(request, response);
			}
		}
	}
	protected void continuePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			HttpSession session=request.getSession(true);
			String authenticated = session.getAttribute("authenticated").toString();
    		UserService us = new UserService();
			int level= us.getLevelByUsername(authenticated);
			if(level>7){
				Connection conn = DatabaseConnection.getConnection();
				String[] pid=(String []) request.getParameterValues("pid");
				int i=0;
				if(new Long(pid[0]).longValue()==-1){
					i=1;
				}
				PreparedStatement pstmt = conn.prepareStatement("UPDATE pullout SET auction=1 WHERE pid=?");
				for(;i<pid.length;i++){
					pstmt.setLong(1,new Long(pid[i]).longValue());
					pstmt.executeUpdate();
				}
				if(level==9){
					response.sendRedirect("/admin/auctionselect.jsp?msg=Auction items successful.");
				}else if(level==8){
					response.sendRedirect("/owner/auctionselect.jsp?msg=Auction items successful.");
				}
			}else{
				if(level==9){
					response.sendRedirect("/admin/auctionselect.jsp?msg=You are not authorized to auction items.");
				}else if(level==8){
					response.sendRedirect("/owner/auctionselect.jsp?msg=You are not authorized to auction items.");
				}
			}
		}catch (SQLException ex) {
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		}
	}
}