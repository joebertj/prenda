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
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import com.prenda.helper.DatabaseConnection;
import com.prenda.helper.PasswordEncoderGenerator;

/**
 * Servlet implementation class for Servlet: CheckRedeem
 *
 */
 public class CheckPullout extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 963053242127001654L;
	private static Logger log =Logger.getLogger(CheckPullout.class);

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CheckPullout() {
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
			String username=request.getParameter("username");
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		pstmt = conn.prepareStatement("SELECT password,level FROM users WHERE username = ?");
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            String badLogin="No such user or bad password!";
            if(rs.first()){
            	String pass = request.getParameter("password");
            	String password = PasswordEncoderGenerator.getHash(pass);
                String password2 = rs.getString(1);
                int level = rs.getInt(2);
                if(!password.equals(password2)){
                	String redirectURL = "pullout.jsp?msg="+badLogin;
        			response.sendRedirect(redirectURL);
            	}else if(level!=3 && level<8){
            		String redirectURL = "pullout.jsp?msg=Access level not authorized";
        			response.sendRedirect(redirectURL);
            	}else{
            		int pid=new Integer(request.getParameter("pid")).intValue();
        			long pullout=new Long(request.getParameter("pulloutdate")).longValue();
        			Date pulloutDate=new Date(pullout);
        			HttpSession session=request.getSession(true);
            		String encoder=(String) session.getAttribute("authenticated");
            		pstmt = conn.prepareStatement("INSERT INTO pullout VALUES (?,null,?,?,?,0)");
            		pstmt.setLong(1,pid);
            		pstmt.setDate(2,pulloutDate);
            		pstmt.setString(3,username);
            		pstmt.setString(4,encoder);
            		pstmt.executeUpdate();
            		response.sendRedirect("pullout.jsp?msg=Pawn with id "+pid+" successfully pulled out");
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