/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.base.Strings;
import com.prenda.Mode;
import com.prenda.Pawn;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.InventoryService;
import com.prenda.service.UserService;

/**
 * Servlet implementation class for Servlet: InventoryCheckList
 *
 */
public class InventoryCheckList extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = -5523891203994583741L;

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InventoryCheckList() {
		super();
	}   	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}  	

	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		if(session.isNew()){
			String redirectURL = "/public/login.jsp";
			response.sendRedirect(redirectURL);
		}else{ 
			String authenticated=(String) session.getAttribute("authenticated");
			if(authenticated == null){
				String redirectURL = "/public/login.jsp";
				response.sendRedirect(redirectURL);
			}else{
				continuePost(request, response);
			}
		}
	}

	protected void continuePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(true);
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		BufferedWriter bw = new BufferedWriter(pw);
		String authenticated=(String) session.getAttribute("authenticated");
		UserService us = new UserService();
		Users user = us.getUser(authenticated);
		int userId = user.getId();
		int level= user.getLevel();
		int branchId = user.getBranch();
		InventoryService is = new InventoryService();
		List<Pawn> list = is.getAllInventory(level, branchId, userId, "pawn.pid", Mode.ASC, 1, Integer.MAX_VALUE);
		int i = 0;
		for(Pawn p: list){
			String entry = "[ ] " + Strings.padStart(new Integer(p.getPid()).toString(), 10, '0') + " (" + Strings.padStart(new Integer(p.getPtNumber()).toString(), 10, '0')+ ") ";
			bw.write(Strings.padStart(entry, entry.length(), ' '));
			i++;
			if(i==5){
				i = 0;
				bw.write('\n');
			}
		}
		bw.flush();
		pw.close();
		bw.close();
	}   
	
}