/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet.ajax;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.ajaxtags.servlets.BaseAjaxServlet;

import com.prenda.helper.DatabaseConnection;

/**
 * Servlet implementation class for Servlet: GetAccountName
 *
 */
 public class GetAccountName extends BaseAjaxServlet {
	 
	private static final long serialVersionUID = -5473672889097185199L;

	public String getXmlContent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int code=new Integer(request.getParameter("code")).intValue();
		int limit=new Integer(request.getParameter("limit")).intValue();
		AjaxXmlBuilder xml=new AjaxXmlBuilder();
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement pstmt=null;
		if(limit==0){
			pstmt=conn.prepareStatement("SELECT accountname FROM accounts WHERE accountcode=?");
		}else{
			pstmt=conn.prepareStatement("SELECT accountname FROM accounts WHERE accountcode=? AND (floor(accountcode/100)=5 OR accountid=13)");
		}
		pstmt.setInt(1,code);
		ResultSet rs=pstmt.executeQuery();
		if(rs.first()){
			xml.addItem(new Integer(code).toString(),rs.getString(1));
		}else{
			xml.addItem(new Integer(code).toString(),"Not a valid disbursement account!");
		}
		return xml.toString();
	}
}