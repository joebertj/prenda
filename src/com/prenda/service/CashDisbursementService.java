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
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.prenda.Disbursement;
import com.prenda.helper.DatabaseConnection;

public class CashDisbursementService {
	private static Logger log =Logger.getLogger(CashDisbursementService.class);
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Date date;
	private List<Disbursement> disbursement;
	
	public CashDisbursementService() {
		conn = DatabaseConnection.getConnection();
		date=new Date();
	}
	
	public List<Disbursement> getDisbursement(){
		disbursement=new ArrayList<Disbursement>();
		try {
			pstmt = conn.prepareStatement("SELECT journal_group, accountid, description, amount FROM journal WHERE journal_date=?");
			pstmt.setDate(1,new java.sql.Date(date.getTime()));
			rs=pstmt.executeQuery();
			while(rs.next()){
				String g=rs.getString(1);
				int i=rs.getInt(2);
				log.info(i);
				String d=rs.getString(3);
				int c=new AccountService().getCodeById(i);
				String n=new AccountService().getNameById(i);
				float a=rs.getFloat(4);
				Disbursement dis=new Disbursement(g,c,n,d,a);
				disbursement.add(dis);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return disbursement;
	}
	
	public void setDisbursement(List<Disbursement> disbursement){
		this.disbursement=disbursement;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
