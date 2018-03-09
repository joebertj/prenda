package com.prenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.helper.DatabaseConnection;
import com.prenda.model.obj.prenda.Jewelry;
import com.prenda.model.obj.prenda.JewelryPK;

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
	
	@Transactional
	public Jewelry getJewelryById(int branchId, int caratId) {
		Jewelry jewelry = new Jewelry();
		JewelryPK jpk = new JewelryPK();
		jpk.setBranchid(branchId);
		int[] cid = {10, 14, 18, 22, 24};
		jpk.setCaratid((byte) (cid[0] & 0xff));
		ListIterator<Jewelry> list = HibernatePrendaDaoFactory.getJewelryDao().findByCriteria(Restrictions.eq("id", jpk)).listIterator();
		if(list.hasNext()) {
			jewelry = (Jewelry) list.next();
		}
		return jewelry;
	}
	
	@Transactional
	public List<Jewelry> getJewelryById(int branchId) {
		JewelryPK jpk = new JewelryPK();
		jpk.setBranchid(branchId);
		List<Jewelry> list = new ArrayList<Jewelry>();
		int[] cid = {10, 14, 18, 22, 24};
		for(int c: cid) {
			jpk.setCaratid((byte) (c & 0xff));
			list.addAll(HibernatePrendaDaoFactory.getJewelryDao().findByCriteria(Restrictions.eq("id", jpk)));
		}
		return list;
	}
	
	public List<Jewelry> getJewelryById(){
		return getJewelryById(branchId);
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
