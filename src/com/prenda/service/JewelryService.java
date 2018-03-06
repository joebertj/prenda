package com.prenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.prenda.helper.DatabaseConnection;

public class JewelryService {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private int branchId;
	private int caratId;
	private double minimum;
	private double maximum;
	
	public JewelryService(){
		conn = DatabaseConnection.getConnection();
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getCaratId() {
		return caratId;
	}

	public void setCaratId(int caratId) {
		this.caratId = caratId;
	}

	public double getMinimum() {
		try {
			String sql="SELECT minimum FROM jewelry WHERE branchid=? AND caratid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, branchId);
			pstmt.setInt(2, caratId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				minimum=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	public double getMaximum() {
		try {
			String sql="SELECT maximum FROM jewelry WHERE branchid=? AND caratid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, branchId);
			pstmt.setInt(2, caratId);
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				maximum=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}
	
}
