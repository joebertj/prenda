/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.prenda.helper.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
public class CheckItem extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7904370882680674851L;
	private static Logger log = Logger.getLogger(CheckItem.class);

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public CheckItem() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			int pid = new Integer(request.getParameter("pid")).intValue();
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT password FROM genkey WHERE pid = ?");
			pstmt.setInt(1, pid);
			ResultSet rs = pstmt.executeQuery();
			String badLogin = "No such item with the provided serial or bad password!";
			String authenticated = (String) session.getAttribute("authenticated");
			String redirectURL;
			if (authenticated == null) {
				if (rs.next()) {
					String password = request.getParameter("pass");
					String password2 = rs.getString(1);
					if (password.equals(password2)) {
						session.setAttribute("pid", pid);
						redirectURL = "../customer/itemdetail.jsp?pid=" + pid;
					} else {
						redirectURL = "../customer/item.jsp?msg=" + badLogin;
					}
				} else {
					redirectURL = "../customer/item.jsp?msg=" + badLogin;
				}
			} else {
				session.setAttribute("pid", pid);
				redirectURL = "../encoder/itemdetail.jsp?pid=" + pid;
			}
			log.info("redirectURL " + redirectURL);
			response.sendRedirect(redirectURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}