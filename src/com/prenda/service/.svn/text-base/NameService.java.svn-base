/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.prenda.Name;
import com.prenda.helper.DatabaseConnection;

public class NameService {
	private static Logger log =Logger.getLogger(NameService.class);
	private int id;
	private String lastName;
	private String firstName;
	private String middleName;

	public NameService() {

	}

	public List<Name> getAddressById(int id) {
		List<Name> l = new ArrayList<Name>();
		try{
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("SELECT address FROM customer WHERE id=?");
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			String a=null;
			if(rs.first()){
				a=rs.getString(1);
			}
			Name address=new Name(a,id);
			l.add(address);
		} catch (SQLException ex) {
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		} 
		return l;
	}

	public String getLastName(int id) {
		String lastName="";
		try{
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("SELECT last_name FROM customer WHERE id=?");
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.first()){
				lastName=rs.getString(1);
			}
		} catch (SQLException ex) {
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		} 
		return lastName;
	}
	
	public String getFirstName(int id) {
		String firstName="";
		try{
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("SELECT first_name FROM customer WHERE id=?");
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.first()){
				firstName=rs.getString(1);
			}
		} catch (SQLException ex) {
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		} 
		return firstName;
	}
	
	public String getMiddleName(int id) {
		String middleName="";
		try{
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("SELECT middle_name FROM customer WHERE id=?");
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.first()){
				middleName=rs.getString(1);
			}
		} catch (SQLException ex) {
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		} 
		return middleName;
	}

	public List<Name> getNamesByPrefix(String lname,String fname,String mname) {
		List<Name> l = new ArrayList<Name>();
		try{
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement("SELECT last_name,first_name,middle_name,id FROM customer WHERE last_name LIKE ? AND first_name LIKE ? and middle_name LIKE ? AND archive=0");
			pstmt.setString(1, lname+"%");
			pstmt.setString(2, fname+"%");
			pstmt.setString(3, mname+"%");
			rs=pstmt.executeQuery();
			String ln=null;
			String fn=null;
			String mn=null;
			int id=0;
			while(rs.next()){
				ln=rs.getString(1);
				fn=rs.getString(2);
				mn=rs.getString(3);
				id=rs.getInt(4);
				Name name=new Name(ln,fn,mn,id);
				l.add(name);
			}
		} catch (SQLException ex) {
			log.info("SQLException: " + ex.getMessage());
			log.info("SQLState: " + ex.getSQLState());
			log.info("VendorError: " + ex.getErrorCode());
		} 
		return l;
	}

	public String getFirstName() {
		firstName = getFirstName(id);
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		lastName = getLastName(id);
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		middleName =getMiddleName(id);
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

 }
