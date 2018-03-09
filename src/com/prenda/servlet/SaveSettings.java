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
import com.prenda.service.UserService;

/**
 * Servlet implementation class for Servlet: SaveSettings
 *
 */
public class SaveSettings extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9133262814576815926L;
	private static Logger log =Logger.getLogger(SaveSettings.class);
	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SaveSettings() {
		super();
	}   	 	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		String redirectURL = null;
		if(session.isNew()){
			redirectURL = "common/login.jsp";
			response.sendRedirect(redirectURL);
		}else{ 
			String authenticated=(String) session.getAttribute("authenticated");
			UserService us = new UserService();
			int level= us.getLevelByUsername(authenticated);
			if(authenticated == null){
				redirectURL = "common/login.jsp?msg=You have not logged in yet";
				response.sendRedirect(redirectURL);
			}else if(level==7){
				try{
					Connection conn = DatabaseConnection.getConnection();
					PreparedStatement pstmt = null;
					pstmt = conn.prepareStatement("SELECT branch FROM users WHERE username=?");
					String user=(String) session.getAttribute("authenticated");
					int branch=(new Integer(request.getParameter("branchid"))).intValue();
					String bname=request.getParameter("bname");
					pstmt.setString(1, user);
					ResultSet rs=pstmt.executeQuery();
					if(rs.first()){
						int branch2=rs.getInt(1);
						if(branch!=branch2){
							redirectURL="manage/settings.jsp?msg=You are not the manager of branch "+bname;
						}
					}else{
						redirectURL="manage/settings.jsp?msg=You are not the manager of branch "+bname;
					}
				} catch (SQLException ex) {
		            log.info("SQLException: " + ex.getMessage());
		            log.info("SQLState: " + ex.getSQLState());
		            log.info("VendorError: " + ex.getErrorCode());
				}
				if(redirectURL==null){
					continuePost(request, response);
				}else{
					response.sendRedirect(redirectURL);
				}
			} else if (level == 8) {
				Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					int branchid = new Integer(request.getParameter("branchid"))
							.intValue();
					pstmt = conn
							.prepareStatement("SELECT branchid FROM branch LEFT JOIN users ON branch.owner=users.uid WHERE users.username=? AND branchid=?");
					pstmt.setString(1, authenticated);
					pstmt.setInt(2, branchid);
					rs = pstmt.executeQuery();
					if (rs.first()) {
						continuePost(request, response);
					} else {
						redirectURL = "common/login.jsp?msg=You do not own the selected branch";
						response.sendRedirect(redirectURL);
					}
				} catch (SQLException ex) {
					log.info("SQLException: " + ex.getMessage());
					log.info("SQLState: " + ex.getSQLState());
					log.info("VendorError: " + ex.getErrorCode());
				}
			}else if(level==9){
				continuePost(request, response);
			}else if(level<7){
				redirectURL = "common/login.jsp?msg=You are not a manager or an administrator";
				response.sendRedirect(redirectURL);
			}else{
				redirectURL = "common/login.jsp?msg=You don't have access rights";
				response.sendRedirect(redirectURL);
			}
		}
	}
	protected void continuePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		try{
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
    		String authenticated = session.getAttribute("authenticated").toString();
    		UserService us = new UserService();
			int level= us.getLevelByUsername(authenticated);
    		String bname=request.getParameter("bname");
    		int branchId=new Integer(request.getParameter("branchid")).intValue();
    		if(level>=Level.MANAGER){
    			int rate;
    			pstmt = conn.prepareStatement("INSERT INTO interest VALUES (?,?,?) ON DUPLICATE KEY UPDATE rate = ?");
				for(int i=0;i<35;i++){
					String daynum = "day"+new Integer(i).toString();
					String dayval = request.getParameter(daynum);
					if(dayval.isEmpty()) {
						rate = 0;
					}else {
						rate=new Integer(dayval).intValue();
					}
    				pstmt.setInt(1, branchId);
    				pstmt.setInt(2, i);
    				pstmt.setInt(3, rate);
    				pstmt.setInt(4, rate);
    				pstmt.executeUpdate();
    				log.info("branch" + branchId + " " + daynum + " " + rate );
    			}
				int i=0;
				int [] caratId = {10,14,18,22,24};
				String [] mins = request.getParameterValues("minimum");
				String [] maxs = request.getParameterValues("maximum");
				double [] min = new double [caratId.length];
				double [] max = new double [caratId.length];
				for(;i<caratId.length;i++){
					min[i] = Double.parseDouble(mins[i]);
					max[i] = Double.parseDouble(maxs[i]);
				}
    			pstmt = conn.prepareStatement("UPDATE jewelry SET minimum=?,maximum=? WHERE branchid=? AND caratid=?");
    			for(i=0;i<caratId.length;i++){
    				pstmt.setDouble(1, min[i]);
					pstmt.setDouble(2, max[i]);
					pstmt.setInt(3, branchId);
					pstmt.setInt(4, caratId[i]);
					pstmt.executeUpdate();
    			}
    			int user=new Integer(request.getParameter("user")).intValue();
    			int customer=new Integer(request.getParameter("customer")).intValue();
    			int pawn=new Integer(request.getParameter("pawn")).intValue();
    			int redeem=new Integer(request.getParameter("redeem")).intValue();
    			int pullout=new Integer(request.getParameter("pullout")).intValue();
    			int inventory=new Integer(request.getParameter("inventory")).intValue();
    			int outstanding=new Integer(request.getParameter("outstanding")).intValue();
    			int foreclose=new Integer(request.getParameter("foreclose")).intValue();
    			int auction=new Integer(request.getParameter("auction")).intValue();
    			pstmt = conn.prepareStatement("INSERT INTO page VALUES (?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE user=?,customer=?,pawn=?,redeem=?,foreclose=?,pullout=?,outstanding=?,inventory=?,auction=?");
				pstmt.setInt(1, branchId);
				pstmt.setInt(2, user);
				pstmt.setInt(3, customer);
				pstmt.setInt(4, pawn);
				pstmt.setInt(5, redeem);
				pstmt.setInt(6, foreclose);
				pstmt.setInt(7, pullout);
				pstmt.setInt(8, outstanding);
				pstmt.setInt(9, inventory);
				pstmt.setInt(10, auction);
				pstmt.setInt(11, user);
				pstmt.setInt(12, customer);
				pstmt.setInt(13, pawn);
				pstmt.setInt(14, redeem);
				pstmt.setInt(15, foreclose);
				pstmt.setInt(16, pullout);
				pstmt.setInt(17, outstanding);
				pstmt.setInt(18, inventory);
				pstmt.setInt(19, auction);
				pstmt.executeUpdate();
				String referer=(String) request.getParameter("referer");
				response.sendRedirect(referer + "branchlist.jsp?msg=Settings for branch "+bname+" saved");
			}
    	} catch (SQLException ex) {
    		ex.printStackTrace();
        }
	}
}