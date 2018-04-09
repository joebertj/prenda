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

import com.prenda.Journal;
import com.prenda.helper.DatabaseConnection;

public class JournalService {
	private static Logger log =Logger.getLogger(JournalService.class);
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Date date;
	private List<Journal> journal;
	private List<Journal> expenses;
	
	public JournalService() {
		conn = DatabaseConnection.getConnection();
		date=new Date();
	}
	
	public List<Journal> getExpenses() {
		expenses=new ArrayList<Journal>();
		try {
			pstmt = conn.prepareStatement("SELECT journal_group, accountid, description, amount, drcr FROM journal WHERE journal_date=? AND SUBSTRING(CAST(accountid as char(5)),1,1)='5'");
			pstmt.setDate(1,new java.sql.Date(date.getTime()));
			rs=pstmt.executeQuery();
			while(rs.next()){
				String g=rs.getString(1);
				int i=rs.getInt(2);
				log.info("account code "+i);
				String d=rs.getString(3);
				String n=new AccountService().getNameById(i);
				double a=rs.getDouble(4);
				boolean drcr = rs.getBoolean(5);
				Journal dis=new Journal(g,i,n,d,a,drcr);
				expenses.add(dis);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return expenses;
	}

	public void setExpenses(List<Journal> expenses) {
		this.expenses = expenses;
	}

	public List<Journal> getJournal(){
		journal=new ArrayList<Journal>();
		try {
			pstmt = conn.prepareStatement("SELECT journal_group, accountid, description, amount, drcr FROM journal WHERE journal_date=?");
			pstmt.setDate(1,new java.sql.Date(date.getTime()));
			rs=pstmt.executeQuery();
			while(rs.next()){
				String g=rs.getString(1);
				int i=rs.getInt(2);
				log.info("account code "+i);
				String d=rs.getString(3);
				String n=new AccountService().getNameById(i);
				double a=rs.getDouble(4);
				boolean drcr = rs.getBoolean(5);
				Journal dis=new Journal(g,i,n,d,a,drcr);
				journal.add(dis);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return journal;
	}
	
	public void setJournal(List<Journal> journal){
		this.journal=journal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
