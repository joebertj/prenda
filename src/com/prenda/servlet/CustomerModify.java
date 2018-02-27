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

/**
 * Servlet implementation class for Servlet: CustomerModify
 *
 */
 public class CustomerModify extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7132059808379085795L;
	private static Logger log =Logger.getLogger(CustomerModify.class);

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CustomerModify() {
		super();
	}   	
	
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
			int cid;
			request.setCharacterEncoding("UTF-8");
			String clname = request.getParameter("clname");
			String cfname = request.getParameter("cfname");
			String cmname = request.getParameter("cmname");
			String cadd = request.getParameter("cadd");
    		String modtype = request.getParameter("modtype");
    		Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		if(modtype.equals("0")){
    			pstmt = conn.prepareStatement("INSERT INTO customer VALUES (0,?,?,?,?,0)");
    			pstmt.setString(1, clname);
    			pstmt.setString(2, cfname);
    			pstmt.setString(3, cmname);
    			pstmt.setString(4, cadd);
    			pstmt.executeUpdate();
    			response.sendRedirect("listcustomer.jsp?msg=Customer "+cfname+" "+cmname+" "+clname+" added");
    		}else if(modtype.equals("1")){
    			cid=new Integer(request.getParameter("cid")).intValue();
    			String delresp=request.getParameter("delresp");
    			if(delresp.equals("Confirm")){
    				pstmt = conn.prepareStatement("UPDATE customer SET archive=true WHERE id = ?");
    				pstmt.setInt(1, cid);
    				pstmt.executeUpdate();
    				response.sendRedirect("listcustomer.jsp?msg=Customer "+cfname+" "+cmname+" "+clname+" archived");
    			}else{
    				response.sendRedirect("listcustomer.jsp?msg=Archive of customer "+cfname+" "+cmname+" "+clname+" cancelled");
    			}
    		}else if(modtype.equals("2")){
    			cid=new Integer(request.getParameter("cid")).intValue();
    			pstmt = conn.prepareStatement("UPDATE customer SET last_name = ?,first_name = ?,middle_name = ?,address = ? WHERE id = ?");
    			pstmt.setString(1, clname);
    			pstmt.setString(2, cfname);
    			pstmt.setString(3, cmname);
    			pstmt.setString(4, cadd);
    		    pstmt.setInt(5, cid);
    			pstmt.executeUpdate();
    			response.sendRedirect("listcustomer.jsp?msg=Details for customer "+cfname+" "+cmname+" "+clname+" successfully changed");
    		}
		} catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		}
	}   	  	    
}