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
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Level;
import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.helper.DatabaseConnection;
import com.prenda.helper.PasswordEncoderGenerator;
import com.prenda.model.obj.prenda.Branch;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

public class UserService {
	
	private static Logger log = Logger.getLogger(UserService.class);
	
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
	
	@Transactional
	public Users saveUser(String targetUser, String newPassword, int targetUserLevel, int branchId, boolean archive) {
		Users user = new Users();
		String hashedNewPassword = PasswordEncoderGenerator.getHash(newPassword);
		user.setPassword(hashedNewPassword);
		user.setUsername(targetUser);
		user.setLevel((byte) (targetUserLevel & 0xff));
		user.setBranch(branchId);
		user.setArchive(archive);
		user = saveUser(user);
		BranchService bs = new BranchService();
		UserService us = new UserService();
		Branch branch;
		if(targetUserLevel == Level.OWNER) {
			branch = bs.saveBranch(targetUser); // Create new branch
			user.setBranch(branch.getId()); // Safely retrieve the generated branchId
			us.updateUser(user);
			branch.setOwner(user.getId()); // Update branch owner
			bs.updateBranch(branch);
		}else if(targetUserLevel < Level.OWNER) {
			user.setBranch(branchId); // Safely retrieve the generated branchId
			us.updateUser(user);
		}
		return user;
	}
	
	@Transactional
	public Users saveUser(Users user) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.save(user);
		dataLayerPrenda.flushAndClearSession();
		return user;
	}

	@Transactional
	public Users updateUser(Users user) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.update(user);
		dataLayerPrenda.flushAndClearSession();
		return user;
	}
	
	@Transactional
	public Users getUser(String username) {
		Users user = null;
		ListIterator <Users> li = HibernatePrendaDaoFactory.getUsersDao().findByCriteria(Restrictions.eq("username", username)).listIterator();
		if(li.hasNext()) {
			 user = (Users) li.next();
		}
		return user;
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
		log.info("name " + name + " level " + level);
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
			}else {
				branchId = -1; // indicates no match
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return branchId;
	}
	
}