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
 * Servlet implementation class for Servlet: ExtendItem
 *
 */
 public class ExtendItem extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4001255343794665128L;
	private static Logger log =Logger.getLogger(ExtendItem.class);

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ExtendItem() {
		super();
	}   	 	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		if(session.isNew()){
			String redirectURL = "customer/item.jsp";
			response.sendRedirect(redirectURL);
		}else{ 
			Boolean allow=(Boolean) session.getAttribute("allow");
			if(allow==null){
				String redirectURL = "customer/item.jsp";
				response.sendRedirect(redirectURL);
			}else if(!allow.booleanValue()){
				String redirectURL = "customer/item.jsp?Item not yet qualified to be extended";
				response.sendRedirect(redirectURL);
			}else{
				continuePost(request, response);
			}
		}
	}
	protected void continuePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			int pid=new Integer(request.getParameter("pid")).intValue();
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		pstmt = conn.prepareStatement("SELECT extend FROM pawn WHERE pid=?");
    		pstmt.setInt(1,pid);
    		ResultSet rs=pstmt.executeQuery();
    		String redirectURL="customer/itemdetail.jsp?pid="+pid+"&msg=The expiration of pawn item with id "+pid+" was successfully extended";
    		int extend;
    		if(rs.next()){
    			extend=rs.getInt(1);
    			extend++;
    			pstmt = conn.prepareStatement("UPDATE pawn SET extend=? WHERE pid=?");
    			pstmt.setInt(1,extend);
    			pstmt.setLong(2,pid);
    			pstmt.executeUpdate();
    			response.sendRedirect(redirectURL);
    		}
    	}catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		}
	}   	  	    
}