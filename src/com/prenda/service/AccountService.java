/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.prenda.helper.DatabaseConnection;

public class AccountService {
	
	private static Logger log = Logger.getLogger(AccountService.class);
	 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public AccountService(){
		conn = DatabaseConnection.getConnection();
	}
	
	public String getNameById(int id){
		String name="";
		try {
			pstmt = conn.prepareStatement("SELECT accountname FROM accounts WHERE accountcode=?");
			pstmt.setInt(1,id);
			rs=pstmt.executeQuery();
			if(rs.first()){
				name=rs.getString(1);
			}
		} catch (SQLException e) {
			log.info("SQLException: " + e.getMessage());
            log.info("SQLState: " + e.getSQLState());
            log.info("VendorError: " + e.getErrorCode());
		}
		return name;
	}
}
