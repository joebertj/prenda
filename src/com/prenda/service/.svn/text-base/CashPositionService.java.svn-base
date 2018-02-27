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

public class CashPositionService {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private int branchId;
	private int level;
	
	private float inventory;
	private float pawn;
	private float redeem;
	private float redeemPrincipal;
	private float redeemInterest;
	private float receipt;
	private float disburse;
	private float beginBalance;
	private float endBalance;
	private float asset;
	
	private int bcode;
	
	public CashPositionService() {
		conn = DatabaseConnection.getConnection();
	}
	
	public int getBcode() {
		return bcode;
	}
	public void setBcode(int bcode) {
		this.bcode = bcode;
	}
	
	public float getAsset() {
		asset=getEndBalance()+getInventory();
		return asset;
	}
	public void setAsset(float asset) {
		this.asset = asset;
	}
	public float getBeginBalance() {
		beginBalance=getEndBalance()+getPawn()-getRedeem()-getReceipt()+getDisburse();
		return beginBalance;
	}
	public void setBeginBalance(float beginBalance) {
		this.beginBalance = beginBalance;
	}
	public float getDisburse() {
		try {
			String sql="SELECT sum(amount) AS sumamt " +
			"FROM journal " +
			"LEFT JOIN ledger ON journal.journalid=ledger.ledgerid " +
			"LEFT JOIN accounts ON journal.accountid=accounts.accountid " +
			"WHERE ledger.ledgerid IS NOT NULL " +
			"AND journal_date=CURDATE() " +
			"AND (floor(accountcode/100)=5 " +
			"OR journal.accountid=13)";
			if(level<9){
				pstmt = conn.prepareStatement(sql + " AND branchid=?");
				pstmt.setInt(1, branchId);
			}else{
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				disburse=rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return disburse;
	}
	public void setDisburse(float disburse) {
		this.disburse = disburse;
	}
	public float getEndBalance() {
		try {
			if(level<9){
				pstmt = conn.prepareStatement("SELECT balance FROM branch WHERE branchid=?");
				pstmt.setInt(1, branchId);
			}else{
				pstmt = conn.prepareStatement("SELECT sum(balance) FROM branch");
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				endBalance=rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endBalance;
	}
	public void setEndBalance(float endBalance) {
		this.endBalance = endBalance;
	}
	public float getInventory() {
		try {
			String sql="SELECT sum(loan) as sumloan FROM pawn " +
			"LEFT JOIN redeem ON pawn.pid=redeem.pid " +
			"LEFT JOIN pullout ON pawn.pid=pullout.pid " +
			"WHERE redeem.pid IS NULL " +
			"AND pullout.pid IS NULL";
			if(level<9){
				if(bcode==1){
					sql=sql+" AND bcode=1";
				}
				pstmt = conn.prepareStatement(sql + " AND branch=?");
				pstmt.setInt(1, branchId);
			}else{
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				inventory=rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventory;
	}
	public void setInventory(float inventory) {
		this.inventory = inventory;
	}
	public float getPawn() {
		try {
			String sql="SELECT sum(loan) as sumloan " +
					"FROM pawn " +
					"WHERE loan_date=CURDATE()";
			if(level<9){
				if(bcode==1){
					sql=sql+" AND bcode=1";
				}
				pstmt = conn.prepareStatement(sql + " AND branch=?");
				pstmt.setInt(1, branchId);
			}else{
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				pawn=rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pawn;
	}
	public void setPawn(float pawn) {
		this.pawn = pawn;
	}
	public float getReceipt() {
		try {
			String sql="SELECT sum(amount) AS sumamt " +
					"FROM journal " +
					"LEFT JOIN ledger ON journal.journalid=ledger.ledgerid " +
					"LEFT JOIN accounts on journal.accountid=accounts.accountid " +
					"WHERE journal.journalid=ledger.ledgerid " +
					"AND journal_date=CURDATE() " +
					"AND journal.accountid=12";
			if(level<9){
				pstmt = conn.prepareStatement(sql + " AND branchid=?");
				pstmt.setInt(1, branchId);
			}else{
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				receipt=rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return receipt;
	}
	public void setReceipt(float receipt) {
		this.receipt = receipt;
	}
	public float getRedeem() {
		try {
			String sql="SELECT sum(loan+interest) as sumnet " +
					"FROM pawn " +
					"LEFT JOIN redeem ON pawn.pid=redeem.pid " +
					"WHERE pawn.pid=redeem.pid " +
					"AND redeem_date=CURDATE()";
			if(level<9){
				if(bcode==1){
					sql=sql+" AND bcode=1";
				}
				pstmt = conn.prepareStatement(sql + " AND branch=?");
				pstmt.setInt(1, branchId);
			}else{
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				redeem=rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return redeem;
	}
	public void setRedeem(float redeem) {
		this.redeem = redeem;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public float getRedeemInterest() {
		try {
			String sql="SELECT sum(interest) as sumnet " +
			"FROM pawn " +
			"LEFT JOIN redeem ON pawn.pid=redeem.pid " +
			"WHERE pawn.pid=redeem.pid " +
			"AND redeem_date=CURDATE()";
			if(level<9){
				if(bcode==1){
					sql=sql+" AND bcode=1";
				}
				pstmt = conn.prepareStatement(sql + " AND branch=?");
				pstmt.setInt(1, branchId);
			}else{
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				redeemInterest=rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return redeemInterest;
	}
	public void setRedeemInterest(float redeemInterest) {
		this.redeemInterest = redeemInterest;
	}
	public float getRedeemPrincipal() {
		try {
			String sql="SELECT sum(loan) as sumnet " +
			"FROM pawn " +
			"LEFT JOIN redeem ON pawn.pid=redeem.pid " +
			"WHERE pawn.pid=redeem.pid " +
			"AND redeem_date=CURDATE()";
			if(level<9){
				if(bcode==1){
					sql=sql+" AND bcode=1";
				}
				pstmt = conn.prepareStatement(sql + " AND branch=?");
				pstmt.setInt(1, branchId);
			}else{
				pstmt = conn.prepareStatement(sql);
			}
			ResultSet rs=pstmt.executeQuery();
			if(rs.first()){
				redeemPrincipal=rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return redeemPrincipal;
	}
	public void setRedeemPrincipal(float redeemPrincipal) {
		this.redeemPrincipal = redeemPrincipal;
	}
}
