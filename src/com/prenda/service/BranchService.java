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
import java.util.ListIterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.helper.DatabaseConnection;
import com.prenda.model.obj.prenda.Branch;
import com.prenda.services.data.DataLayerPrenda;
import com.prenda.services.data.DataLayerPrendaImpl;

public class BranchService {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private int id;

	private String name;
	private String address;
	private int counter;
	private int pawnTicket;
	private float advanceInterest;
	private float serviceCharge;
	private int minDaysToExtend;
	private int reserveDuration;
	private float balance;

	private int ownerId;

	public int getOwnerId() {
		try {
			pstmt = conn.prepareStatement("SELECT owner FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				ownerId = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public BranchService() {
		conn = DatabaseConnection.getConnection();
	}

	public String getName() {
		try {
			pstmt = conn.prepareStatement("SELECT name FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	public String getAddress() {
		try {
			pstmt = conn.prepareStatement("SELECT address FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				address = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCounter() {
		try {
			pstmt = conn.prepareStatement("SELECT counter FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				counter = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return counter;
	}

	public int getPawnTicket() {
		try {
			pstmt = conn.prepareStatement("SELECT pt_number FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				pawnTicket = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pawnTicket;
	}

	public void setPawnTicket(int pawnTicket) {
		this.pawnTicket = pawnTicket;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public float getAdvanceInterest() {
		try {
			pstmt = conn.prepareStatement("SELECT advance_interest FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				advanceInterest = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return advanceInterest;
	}

	public void setAdvanceInterest(float advanceInterest) {
		this.advanceInterest = advanceInterest;
	}

	public int getMinDaysToExtend() {
		try {
			pstmt = conn.prepareStatement("SELECT extend FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				minDaysToExtend = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return minDaysToExtend;
	}

	public void setMinDaysToExtend(int minDaysToExtend) {
		this.minDaysToExtend = minDaysToExtend;
	}

	public int getReserveDuration() {
		try {
			pstmt = conn.prepareStatement("SELECT reserve FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				reserveDuration = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reserveDuration;
	}

	public void setReserveDuration(int reserveDuration) {
		this.reserveDuration = reserveDuration;
	}

	public float getServiceCharge() {
		try {
			pstmt = conn.prepareStatement("SELECT service_charge FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				serviceCharge = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return serviceCharge;
	}

	public void setServiceCharge(float serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public float getBalance() {
		try {
			pstmt = conn.prepareStatement("SELECT balance FROM branch WHERE branchid=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.first()) {
				balance = rs.getFloat(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public List<com.prenda.Branch> getBranches() {
		List<com.prenda.Branch> list = new ArrayList<com.prenda.Branch>();
		list = getBranches(ownerId);
		return list;
	}

	@Deprecated
	public List<com.prenda.Branch> getBranches(int ownerId) {
		List<com.prenda.Branch> list = new ArrayList<com.prenda.Branch>();
		try {
			pstmt = conn.prepareStatement("SELECT branchid,name,address FROM branch WHERE owner=?");
			pstmt.setInt(1, ownerId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				address = rs.getString(3);
				com.prenda.Branch b = new com.prenda.Branch(id, name, address);
				list.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	public List<Branch> getBranchesByOwnerId(int ownerId) {
		List<Branch> list = HibernatePrendaDaoFactory.getBranchDao().findByCriteria(Restrictions.eq("owner", ownerId));
		return list;
	}

	@Transactional
	public Branch getBranchbyId(int branchId) {
		Branch branch = new Branch();
		ListIterator<Branch> li = HibernatePrendaDaoFactory.getBranchDao()
				.findByCriteria(Restrictions.eq("id", branchId)).listIterator();
		if (li.hasNext()) {
			branch = (Branch) li.next();
		}
		return branch;
	}
	
	@Transactional
	public int getNextBranchId() {
		DataLayerPrenda instance = DataLayerPrendaImpl.getInstance();
		Session s = instance.getCurrentSession();
		Criteria c = s.createCriteria(Branch.class).setProjection(Projections.max("id"));
		Integer id = (Integer) c.list().listIterator().next();
		return id;
	}

}