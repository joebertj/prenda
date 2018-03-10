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

import com.prenda.Level;
import com.prenda.helper.DatabaseConnection;
import com.prenda.helper.PasswordEncoderGenerator;

/**
 * Servlet implementation class for Servlet: CheckMassPullout
 *
 */
 public class CheckMassPullout extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7133781336589365511L;
	private static Logger log =Logger.getLogger(CheckMassPullout.class);
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CheckMassPullout() {
		super();
	}   	 	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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
			String username=request.getParameter("username");
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		pstmt = conn.prepareStatement("SELECT password,level FROM users WHERE username = ?");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            String badLogin="No such user or bad password!";
            if(rs.first()){
            	String password = request.getParameter("password");
                String password2 = rs.getString(1);
                int level = rs.getInt(2);
                if(!PasswordEncoderGenerator.matches(password, password2)){
                	String redirectURL = "pullout.jsp?msg="+badLogin;
        			response.sendRedirect(redirectURL);
            	}else if(level<Level.LIAISON){
            		String redirectURL = "pullout.jsp?msg=Access level not authorized";
        			response.sendRedirect(redirectURL);
            	}else{
            		String[] pid=(String []) request.getParameterValues("pid");
        			int i=0;
        			if(new Long(pid[0]).longValue()==-1){
        				i=1;
        			}
        			HttpSession session=request.getSession(true);
        			String encoder=(String) session.getAttribute("authenticated");
        			pstmt = conn.prepareStatement("INSERT INTO pullout VALUES (?,null,NOW(),?,?,0)");
        			for(;i<pid.length;i++){
        				pstmt.setLong(1,new Long(pid[i]).longValue());
        				pstmt.setString(2,username);
        				pstmt.setString(3,encoder);
        				pstmt.executeUpdate();
        			}
        			response.sendRedirect("pullout.jsp?msg=Mass pullout successful");
            	}
            }else{
            	String redirectURL = "pullout.jsp?msg="+badLogin;
    			response.sendRedirect(redirectURL);
            }
		}catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		}
	}   	  	    
}