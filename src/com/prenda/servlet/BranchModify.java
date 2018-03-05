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
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.prenda.Level;
import com.prenda.helper.DatabaseConnection;
import com.prenda.service.LevelService;

/**
 * Servlet implementation class for Servlet: UserModify
 *
 */
 public class BranchModify extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2820267674260545204L;
	private static Logger log =Logger.getLogger(BranchModify.class);

	/**
	 * 
	 */
	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public BranchModify() {
		super();
	}   	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		String redirectURL=null;
		if(session.isNew()){
			redirectURL = "common/login.jsp";
			response.sendRedirect(redirectURL);
		}else{ 
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String authenticated = auth.getName();
			Iterator<? extends GrantedAuthority> li = auth.getAuthorities()
					.iterator();
			String role = "ROLE_";
			if (li.hasNext()) {
				GrantedAuthority ga = li.next();
				role = ga.getAuthority();
			}
			LevelService ls = new LevelService();
			Integer level = ls.getId(role.replace("ROLE_", ""));
			log.info("role: " + role + " level: " + level);
			if(authenticated == null){
				redirectURL = "common/login.jsp?msg=You have not logged in yet";
				response.sendRedirect(redirectURL);
			}else if(level==Level.ADMIN){
				continuePost(request, response, authenticated, level);
			}else if(level>=Level.MANAGER){
				int modtype = new Integer(request.getParameter("modtype"))
						.intValue();
				if (modtype > 0) {
					Connection conn = DatabaseConnection.getConnection();
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						if (modtype == 1) {
							String bname = request.getParameter("bname");
							pstmt = conn
									.prepareStatement("SELECT branchid FROM branch LEFT JOIN users ON branch.owner=users.uid WHERE users.username=? AND name=?");
							pstmt.setString(1, authenticated);
							pstmt.setString(2, bname);
							rs = pstmt.executeQuery();
							if (rs.first()) {
								continuePost(request, response, authenticated, level);
							} else {
								redirectURL = "common/login.jsp?msg=You do not own the selected branch";
								response.sendRedirect(redirectURL);
							}
						} else if (modtype == 2 && level==Level.OWNER) {
							int branchid = new Integer(request.getParameter("branchid")).intValue();
							pstmt = conn
									.prepareStatement("SELECT branchid FROM branch LEFT JOIN users ON branch.owner=users.uid WHERE users.username=? AND branchid=?");
							pstmt.setString(1, authenticated);
							pstmt.setInt(2, branchid);
							rs = pstmt.executeQuery();
							if (rs.first()) {
								continuePost(request, response, authenticated, level);
							} else {
								redirectURL = "common/login.jsp?msg=You do not own the selected branch";
								response.sendRedirect(redirectURL);
							}

						} else if (modtype == 2 && level==Level.MANAGER) {
							int branchid = new Integer(request.getParameter("branchid")).intValue();
							pstmt = conn
									.prepareStatement("SELECT branch FROM users WHERE users.username=?");
							pstmt.setString(1, authenticated);
							rs = pstmt.executeQuery();
							if (rs.first()) {
								if(branchid==rs.getInt(1)){
									continuePost(request, response, authenticated, level);
								}else{
									redirectURL = "common/login.jsp?msg=You are not the manager of this branch";
									response.sendRedirect(redirectURL);
								}
							} else {
								redirectURL = "common/login.jsp?msg=You are not the manager of this branch";
								response.sendRedirect(redirectURL);
							}
						}
					} catch (SQLException ex) {
						log.info("SQLException: " + ex.getMessage());
						log.info("SQLState: " + ex.getSQLState());
						log.info("VendorError: " + ex.getErrorCode());
					}
				} else {
					continuePost(request, response, authenticated, level);
				}
			}else if(level<Level.ADMIN){
				redirectURL = "common/login.jsp?msg=You are not an administrator";
				response.sendRedirect(redirectURL);
			}else{
				redirectURL = "common/login.jsp?msg=You don't have access rights";
				response.sendRedirect(redirectURL);
			}
		}
	}
	protected void continuePost(HttpServletRequest request, HttpServletResponse response, String authenticated, Integer level) throws ServletException, IOException {
		try{
			Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		String modtype = request.getParameter("modtype");
			if(modtype.equals("0")){
    			String bname = request.getParameter("bname");
    			String address = request.getParameter("address");
    			float balance = new Float(request.getParameter("balance")).floatValue();
    			float ai = new Float(request.getParameter("ai")).floatValue();
    			float sc = new Float(request.getParameter("sc")).floatValue();
    			int extend = new Integer(request.getParameter("extend")).intValue();
    			int reserve = new Integer(request.getParameter("reserve")).intValue();
    			int pt = new Integer(request.getParameter("pt")).intValue();
    			int owner=0;
    			ResultSet rs;
    			if(level==Level.ADMIN){
    				owner = new Integer(request.getParameter("uid")).intValue();
    			}else{
    				pstmt = conn.prepareStatement("SELECT uid FROM users WHERE username=?");
    				pstmt.setString(1,authenticated);
    				rs=pstmt.executeQuery();
    				if(rs.first()){
    					owner=rs.getInt(1);
    				}else{
    					response.sendRedirect("owner/newbranch.jsp?msg=You are not the owner of "+bname);
    				}
    			}
    			pstmt = conn.prepareStatement("SELECT name FROM branch WHERE name=?");
    			pstmt.setString(1, bname);
    			rs=pstmt.executeQuery();
    			if(rs.first()){
    				if(request.getContextPath().toString().contains("owner")){
    					response.sendRedirect("owner/newbranch.jsp?msg=Branch "+bname+" already exists");
    				}else{
    					response.sendRedirect("newbranch.jsp?msg=Branch "+bname+" already exists");
    				}	
    			}
    			pstmt = conn.prepareStatement("INSERT INTO branch VALUES (0,?,?,?,?,0,?,?,?,0,?,?)");
    			pstmt.setString(1, bname);
    			pstmt.setString(2, address);
    			pstmt.setFloat(3, balance);
    			pstmt.setInt(4, extend);
    			pstmt.setFloat(5, ai);
    			pstmt.setFloat(6, sc);
    			pstmt.setInt(7, reserve);
    			pstmt.setInt(8, owner);
    			pstmt.setInt(9, pt);
    			pstmt.executeUpdate();
    			log.info("owner id "+owner);
    			pstmt = conn.prepareStatement("SELECT branchid FROM branch WHERE name=?");
    			pstmt.setString(1, bname);
    			rs=pstmt.executeQuery();
    			int branchid=0;
    			if(rs.first()){
    				branchid=rs.getInt(1);
    			}
    			pstmt = conn.prepareStatement("INSERT INTO interest VALUES(?,?,?)");
    			for(int i=0;i<35;i++){
    				pstmt.setInt(1, branchid);
    				pstmt.setInt(2, i);
    				pstmt.setInt(3, 0);
    				pstmt.executeUpdate();
    			}
    			pstmt = conn.prepareStatement("INSERT INTO page VALUES(?,10,10,10,10,10,10,10,10,10)");
    			pstmt.setInt(1, branchid);
    			pstmt.executeUpdate();
    			if(request.getContextPath().toString().contains("owner")){
    				response.sendRedirect("owner/newbranch.jsp?msg=Branch "+bname+" successfully added");
    			}else{
    				response.sendRedirect("newbranch.jsp?msg=Branch "+bname+" successfully added");
    			}
    		}else if(modtype.equals("1")){
    			int branchid = new Integer(request.getParameter("branchid")).intValue();
    			String bname = request.getParameter("bname");
    			String delresp=request.getParameter("delresp");
    			if(delresp.equals("Confirm")){
    				pstmt = conn.prepareStatement("DELETE FROM page WHERE pageid = ?");
    				pstmt.setInt(1, branchid);
    				pstmt.executeUpdate();
    				pstmt = conn.prepareStatement("DELETE FROM interest WHERE interestid = ?");
    				pstmt.setInt(1, branchid);
    				pstmt.executeUpdate();
    				pstmt = conn.prepareStatement("UPDATE branch SET archive=1 WHERE branchid = ?");
    				pstmt.setInt(1, branchid);
    				pstmt.executeUpdate();
    				if(request.getContextPath().toString().contains("owner")){
    					response.sendRedirect("owner/branchlist.jsp?msg=Branch "+bname+" archived");
    				}else{
    					response.sendRedirect("admin/branchlist.jsp?msg=Branch "+bname+" archived");
    				}
    			}else{
    				if(request.getContextPath().toString().contains("owner")){
    					response.sendRedirect("owner/branchlist.jsp?msg=Archive of branch "+bname+" cancelled");
    				}else{
    					response.sendRedirect("admin/branchlist.jsp?msg=Archive of branch "+bname+" cancelled");
    				}
    			}
    		}else if(modtype.equals("2")){
    			int branchid = new Integer(request.getParameter("branchid")).intValue();
    			String bname = request.getParameter("bname");
    			String address = request.getParameter("address");
    			float balance = new Float(request.getParameter("balance")).floatValue();
    			float ai = new Float(request.getParameter("ai")).floatValue();
    			float sc = new Float(request.getParameter("sc")).floatValue();
    			int extend = new Integer(request.getParameter("extend")).intValue();
    			int reserve = new Integer(request.getParameter("reserve")).intValue();
    			int uid=-1;
    			if(level==Level.ADMIN){
    				uid = new Integer(request.getParameter("uid")).intValue();
    				log.info("owner id "+uid);
    			}
    			int pt = new Integer(request.getParameter("pt")).intValue();
    			if(level==Level.ADMIN){
    				pstmt = conn.prepareStatement("UPDATE branch SET name = ?, address = ?, balance = ?, extend = ?, advance_interest=?, service_charge=?, reserve=?, owner=?, pt_number=? WHERE branchid = ?");
    				pstmt.setInt(8, uid);
    				pstmt.setInt(9, pt);
        			pstmt.setInt(10, branchid);
    			}else if(level>=Level.MANAGER){
    				pstmt = conn.prepareStatement("UPDATE branch SET name = ?, address = ?, balance = ?, extend = ?, advance_interest=?, service_charge=?, reserve=?, pt_number=? WHERE branchid = ?");
    				pstmt.setInt(8, pt);
        			pstmt.setInt(9, branchid);
    			}
    			pstmt.setString(1, bname);
    			pstmt.setString(2, address);
    			pstmt.setFloat(3, balance);
    			pstmt.setInt(4, extend);
    			pstmt.setFloat(5, ai);
    			pstmt.setFloat(6, sc);
    			pstmt.setInt(7, reserve);
    			pstmt.executeUpdate();
    			if(request.getContextPath().toString().contains("manager")){
    				response.sendRedirect("manager/changebranch.jsp?msg=Details for branch "+bname+" successfully changed");
    			}else if(request.getContextPath().toString().contains("owner")){
    				response.sendRedirect("owner/branchlist.jsp?msg=Details for branch "+bname+" successfully changed");
    			}else{
    				response.sendRedirect("admin/branchlist.jsp?msg=Details for branch "+bname+" successfully changed");
    			}
    		}
		} catch (SQLException ex) {
            log.info("SQLException: " + ex.getMessage());
            log.info("SQLState: " + ex.getSQLState());
            log.info("VendorError: " + ex.getErrorCode());
		}
	}   	  	    
}