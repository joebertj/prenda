/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;


import java.io.IOException;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;

import com.prenda.helper.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Servlet implementation class for Servlet: CheckPawn
 *
 */
 public class CheckPawn extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7146499403963541026L;
	private static Logger log =Logger.getLogger(CheckPawn.class);

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CheckPawn() {
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
			HttpSession session=request.getSession(true);
    		String encoder=(String) session.getAttribute("authenticated");
    		Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		ResultSet rs = null;
    		//long pid=new Integer(request.getParameter("pid")).intValue();
    		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
    		Date loandate = sdf.parse(request.getParameter("loandate"));
    		log.info("loandate:" +loandate);
    		long serial=loandate.getTime();
    		Random generator = new Random(serial);
			int bcode=generator.nextInt(5)+1;
			int nameid=-1;
			pstmt = conn.prepareStatement("SELECT id FROM customer WHERE last_name=? AND first_name=? AND middle_name=?");
			String lname=request.getParameter("lname");
			String fname=request.getParameter("fname");
			String mname=request.getParameter("mname");
			pstmt.setString(1,lname);
			pstmt.setString(2,fname);
			pstmt.setString(3,mname);
			rs=pstmt.executeQuery();
			if(rs.first()){
				nameid=rs.getInt(1);
			}else{
				String address=request.getParameter("address");
				pstmt = conn.prepareStatement("INSERT INTO customer VALUES (0,?,?,?,?,0)");
				pstmt.setString(1,lname);
				pstmt.setString(2,fname);
				pstmt.setString(3,mname);
				pstmt.setString(4,address);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement("SELECT id FROM customer WHERE last_name=? AND first_name=? AND middle_name=?");
				pstmt.setString(1,lname);
				pstmt.setString(2,fname);
				pstmt.setString(3,mname);
				rs=pstmt.executeQuery();
				if(rs.first()){
					nameid=rs.getInt(1);
				}
			}
			float loan=new Float(request.getParameter("loanamt")).floatValue();
    		//float interest=new Integer(request.getParameter("interest")).intValue();
    		float appraised=new Integer(request.getParameter("appamt")).intValue();
    		String description=request.getParameter("desc");
    		float serviceCharge=new Float(request.getParameter("service")).floatValue();
    		float interest=new Float(request.getParameter("interest")).floatValue();
    		pstmt = conn.prepareStatement("SELECT MAX(pid)+1 FROM pawn");
    		rs = pstmt.executeQuery();
    		long pid=1;
    		if(rs.first()){
    			pid=rs.getInt(1);
    			if(pid==0){
    				pid=1;
    			}
    		}
    		log.info(pid);
    		pstmt = conn.prepareStatement("SELECT branch FROM users WHERE username=?");
    		pstmt.setString(1,encoder);
    		rs = pstmt.executeQuery();
    		int branch=0;
    		if(rs.first()){
    			branch=rs.getInt(1);
    		}
    		pstmt = conn.prepareStatement("SELECT counter+1 FROM branch WHERE branchid=?");
    		pstmt.setInt(1,branch);
    		rs = pstmt.executeQuery();
    		int bpid=0;
    		if(rs.first()){
    			bpid=rs.getInt(1);
    		}
    		pstmt = conn.prepareStatement("SELECT pt_number FROM branch WHERE branchid=?");
    		pstmt.setInt(1,branch);
    		rs = pstmt.executeQuery();
    		int pt=0;
    		if(rs.first()){
    			pt=rs.getInt(1);
    		}
    		pstmt = conn.prepareStatement("INSERT INTO pawn VALUES (?,?,?,NOW(),?,?,?,?,?,?,?,?,0,?,?,?)");
    		pstmt.setLong(1,pid);
    		pstmt.setLong(2,serial);
    		pstmt.setInt(3,bcode);
    		pstmt.setDate(4,new java.sql.Date(loandate.getTime()));
    		pstmt.setInt(5,nameid);
    		pstmt.setFloat(6,loan);
    		pstmt.setFloat(7,appraised);
    		pstmt.setString(8,description);
    		pstmt.setFloat(9,serviceCharge);
    		pstmt.setFloat(10,interest);
    		pstmt.setString(11,encoder);
    		pstmt.setInt(12,branch);
    		pstmt.setInt(13,bpid);
    		pstmt.setInt(14,pt);
    		pstmt.executeUpdate();
    		String genkey="";
    		generator = RandomUtils.JVM_RANDOM;
    		for(int i=0;i<10;i++){
    			boolean j=generator.nextBoolean();
    			if(j){
    				genkey+=(char) (generator.nextInt(26)+97);
    			}else{
    				genkey+=(char) (generator.nextInt(10)+48);
    			}
    		}
    		pstmt = conn.prepareStatement("UPDATE branch SET balance=balance-?,counter=counter+1,pt_number=pt_number+1 WHERE branchid=?");
    		pstmt.setFloat(1,loan);
    		pstmt.setInt(2,branch);
    		pstmt.executeUpdate();
    		/*pstmt = conn.prepareStatement("SELECT pid FROM pawn WHERE serial=? AND bpid=?");
    		pstmt.setLong(1,serial);
    		pstmt.setLong(2,bpid);
    		rs = pstmt.executeQuery();
    		rs.first();
    		pid=rs.getInt(1);*/
    		pstmt = conn.prepareStatement("INSERT INTO genkey VALUES (?,?)");
    		pstmt.setLong(1,pid);
    		pstmt.setString(2,genkey);
    		pstmt.executeUpdate();
    		pstmt = conn.prepareStatement("UPDATE users SET loan_date=? WHERE username=?");
    		pstmt.setDate(1,new java.sql.Date(loandate.getTime()));
    		pstmt.setString(2,encoder);
    		pstmt.executeUpdate();
    		response.sendRedirect("pawndetailpdf.jsp?pid="+pid+"&msg=Pawn with pid "+pid+" successfully stored");
    	}catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
		
}