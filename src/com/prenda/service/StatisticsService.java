/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.prenda.Branch;
import com.prenda.Level;
import com.prenda.Mode;
import com.prenda.helper.DatabaseConnection;

import java.sql.ResultSet;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;

public class StatisticsService {
	
	private static Logger log = Logger.getLogger(StatisticsService.class);
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	private float rate;
	private int branchId;
	private int userId;
	private int mode;
	
	private int count;
	
	public StatisticsService(){
		conn = DatabaseConnection.getConnection();
	}
	
	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public int getRedeemCount(){
		count = getRedeemCount(branchId,userId,mode);
		return count;
	}
	
	public int getRedeemCount(int branchId,int userId,int mode){
		int count = 0;
		try {
			pstmt = conn.prepareStatement("select count(pid) from redeem");
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getRedeemCountByInterestRate(float rate,int branchId,int userId,int mode){
		int count = 0;
		UserService us = new UserService();
		int level = us.getLevelById(userId);
		String query = "select count(redeem.pid) from pawn " +
			"left join redeem on pawn.pid=redeem.pid " +
			"where 100*interest/loan = ? " +
			"and redeem.pid is not null";
		if(mode==Mode.DAILY){
			query += " and redeem_date=curdate()";
		}else if(mode==Mode.MONTHLY){
			query += " and year(redeem_date)=year(curdate()) and month(redeem_date)=month(curdate())";
		}else if(mode==Mode.YEARLY){
			query += " and year(redeem_date)=year(curdate())";
		}
		if(level==Level.ADMIN){
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setFloat(1,rate);
				ResultSet rs=pstmt.executeQuery();
				if(rs.first()){
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(level==Level.OWNER){
			String branches = "";
			List<Branch> list = new BranchService().getBranches(userId);
			ListIterator<Branch> li = list.listIterator();
			int [] id = new int[list.size()];
			int i = 0;
			while(li.hasNext()){
				Branch b = (Branch) li.next();
				id[i]=b.getId();
				i++;
			}
			i = 0;
			while(li.hasNext()){
				if(i>0){
					branches += " or ";
				}
				branches = "pawn.branch = ?";
				i++;
			}
			if(branches.length()>0){
				query += " and (" + branches + ")";
			}
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setFloat(1,rate);
				for(i=0;i<id.length;i++){
					pstmt.setInt(2+i, id[i]);
				}
				ResultSet rs=pstmt.executeQuery();
				if(rs.first()){
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(level==Level.ENCODER  || level==Level.MANAGER){
			query += " and pawn.branch=?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setFloat(1,rate);
				pstmt.setInt(2, branchId);
				ResultSet rs=pstmt.executeQuery();
				if(rs.first()){
					count = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		log.info("mode " + mode + "count " + count);
		return count;
	}
	
	public int getRedeemCountByInterestRate(){
		count = getRedeemCountByInterestRate(rate,branchId,userId,mode);
		return count;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
