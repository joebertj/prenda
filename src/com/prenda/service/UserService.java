/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.prenda.helper.DatabaseConnection;

public class UserService {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private int id;
	private String name;
	private int level;
	private int branchId;
	private Date loanDate;
	private Date redeemDate;
	
	public UserService(){
		conn = DatabaseConnection.getConnection();
	}
	
	public String getName(){
		return name;
	}
	
	public int getId() {
		try {
			pstmt = conn.prepareStatement("SELECT uid FROM users WHERE username=?");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.first()){
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		try {
			pstmt = conn.prepareStatement("SELECT level FROM users WHERE username=?");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.first()){
				level=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	public int getLevelByUsername(String name) {
		try {
			pstmt = conn.prepareStatement("SELECT level FROM users WHERE username=?");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.first()){
				level=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	public int getLevelById(int userId) {
		try {
			pstmt = conn.prepareStatement("SELECT level FROM users WHERE uid=?");
			pstmt.setInt(1, userId);
			rs=pstmt.executeQuery();
			if(rs.first()){
				level=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getBranchId() {
		try {
			pstmt = conn.prepareStatement("SELECT branch FROM users WHERE username=?");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.first()){
				branchId=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Date getLoanDate() {
		try {
			pstmt = conn.prepareStatement("SELECT loan_date FROM users WHERE username=?");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.first()){
				loanDate=rs.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	
	public Date getRedeemDate() {
		try {
			pstmt = conn.prepareStatement("SELECT redeem_date FROM users WHERE username=?");
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.first()){
				redeemDate=rs.getDate(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return redeemDate;
	}

	public void setRedeemDate(Date redeemDate) {
		this.redeemDate = redeemDate;
	}

	public int getIdByUsername(String authenticated) {
		try {
			pstmt = conn.prepareStatement("SELECT uid FROM users WHERE username=?");
			pstmt.setString(1, authenticated);
			rs=pstmt.executeQuery();
			if(rs.first()){
				id=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public int getBranchIdByUsername(String authenticated) {
		try {
			pstmt = conn.prepareStatement("SELECT branch FROM users WHERE username=?");
			pstmt.setString(1, authenticated);
			rs=pstmt.executeQuery();
			if(rs.first()){
				branchId=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branchId;
	}
	
}