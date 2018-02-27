/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.prenda.helper.DatabaseConnection;

public class PageService {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private int user;  
	private int customer;
	private int pawn;
	private int redeem;
	private int foreclose;
	private int pullout;
	private int outstanding;
	private int inventory;
	private int auction;
	
	private int branch;
	private int chart;
	private int disburse;
	
	private int branchId;
	
	public PageService() {
		conn = DatabaseConnection.getConnection();
	}

	public int getCustomer() {
		try {
			pstmt = conn.prepareStatement("SELECT customer FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				customer=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	public void setCustomer(int customer) {
		this.customer = customer;
	}
	public int getForeclose() {
		try {
			pstmt = conn.prepareStatement("SELECT foreclose FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				foreclose=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foreclose;
	}
	public void setForeclose(int foreclose) {
		this.foreclose = foreclose;
	}
	public int getInventory() {
		try {
			pstmt = conn.prepareStatement("SELECT inventory FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				inventory=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public int getOutstanding() {
		try {
			pstmt = conn.prepareStatement("SELECT outstanding FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				outstanding=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return outstanding;
	}
	public void setOutstanding(int outstanding) {
		this.outstanding = outstanding;
	}
	public int getPawn() {
		try {
			pstmt = conn.prepareStatement("SELECT pawn FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pawn=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pawn;
	}
	public void setPawn(int pawn) {
		this.pawn = pawn;
	}
	public int getPullout() {
		try {
			pstmt = conn.prepareStatement("SELECT pullout FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pullout=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pullout;
	}
	public void setPullout(int pullout) {
		this.pullout = pullout;
	}
	public int getRedeem() {
		try {
			pstmt = conn.prepareStatement("SELECT redeem FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				redeem=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return redeem;
	}
	public void setRedeem(int redeem) {
		this.redeem = redeem;
	}
	public int getUser() {
		try {
			pstmt = conn.prepareStatement("SELECT user FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				user=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getBranch() {
		branch=10;
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getChart() {
		chart=50;
		return chart;
	}

	public void setChart(int chart) {
		this.chart = chart;
	}

	public int getDisburse() {
		disburse=20;
		return disburse;
	}

	public void setDisburse(int disburse) {
		this.disburse = disburse;
	}

	public int getAuction() {
		try {
			pstmt = conn.prepareStatement("SELECT auction FROM page WHERE pageid=?");
			pstmt.setInt(1,branchId);
			rs=pstmt.executeQuery();
			if(rs.next()){
				auction=rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return auction;
	}

	public void setAuction(int auction) {
		this.auction = auction;
	}
	
	
}
