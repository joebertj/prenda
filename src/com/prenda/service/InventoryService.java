/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.prenda.Branch;
import com.prenda.Level;
import com.prenda.Pawn;
import com.prenda.SortOrder;
import com.prenda.helper.DatabaseConnection;

public class InventoryService extends GenericService {
	
	public InventoryService(){
		conn = DatabaseConnection.getConnection();
	}
	
	public int getAllInventoryCount(){
		return getAllInventory(level,branchId,userId,sort,order,1,Integer.MAX_VALUE).size();
	}
	
	public List<Pawn> getAllInventory(){
		List<Pawn> list = new ArrayList<Pawn>();
		list = getAllInventory(level,branchId,userId,sort,order,page,pageSize);
		return list;
	}
	
	public List<Pawn> getAllInventory(int level,int branchId,int userId,String sort,int order,int page,int pageSize){
		List<Pawn> list = new ArrayList<Pawn>();
		String query = "SELECT pawn.pid,branch,loan_date,nameid,loan,bpid,rate,pawn.service_charge,pt,bcode,"+
		"pawn.create_date AS cdate,"+
		"ADDDATE(loan_date,120+15*pawn.extend) AS expire,"+
		"(ADDDATE(pawn.loan_date,120+15*pawn.extend) <= NOW()) AS foreclose "+
		"FROM pawn "+
		"LEFT JOIN customer ON pawn.nameid=customer.id "+
		"LEFT JOIN redeem ON pawn.pid=redeem.pid "+
		"LEFT JOIN pullout ON pawn.pid=pullout.pid "+
		"LEFT JOIN interest ON pawn.branch=interest.interestid "+
		"LEFT JOIN branch ON pawn.branch=branch.branchid "+
		"WHERE (day=DATEDIFF(NOW(),loan_date) OR (day=34 AND DATEDIFF(NOW(),loan_date)>34)) "+
		"AND redeem.pid IS NULL " +
		"AND pullout.pid IS NULL";
		if(level==Level.ADMIN){
			query += " ORDER BY " + sort + " " + (order==SortOrder.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1,(page-1)*pageSize);
				pstmt.setInt(2, pageSize);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()){
					int pid = rs.getInt(1);
					int branch = rs.getInt(2);
					Date loanDate = rs.getDate(3);
					int nameId = rs.getInt(4);
					float amount = rs.getFloat(5);
					int bpid = rs.getInt(6);
					float rate = rs.getFloat(7);
					float sc = rs.getFloat(8);
					int pt = rs.getInt(9);
					int bcode = rs.getInt(10);
					Timestamp cdate = rs.getTimestamp(11);
					Date expire = rs.getDate(12);
					int foreclose = rs.getInt(13);
					Pawn p = new Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
					p.setInterestRate(rate);
					p.setForeclose(foreclose);
					p.setCdate(cdate);
					list.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(level==Level.OWNER){
			String branches = "";
			List<Branch> listB = new BranchService().getBranches(userId);
			ListIterator<Branch> li = listB.listIterator();
			int [] id = new int[listB.size()];
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
			query += " ORDER BY " + sort + " " + (order==SortOrder.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
			try {
				pstmt = conn.prepareStatement(query);
				for(i=0;i<id.length;i++){
					pstmt.setInt(1+i, id[i]);
				}
				pstmt.setInt(i+1,(page-1)*pageSize);
				pstmt.setInt(i+2, pageSize);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()){
					int pid = rs.getInt(1);
					int branch = rs.getInt(2);
					Date loanDate = rs.getDate(3);
					int nameId = rs.getInt(4);
					float amount = rs.getFloat(5);
					int bpid = rs.getInt(6);
					float rate = rs.getFloat(7);
					float sc = rs.getFloat(8);
					int pt = rs.getInt(9);
					int bcode = rs.getInt(10);
					Timestamp cdate = rs.getTimestamp(11);
					Date expire = rs.getDate(12);
					int foreclose = rs.getInt(13);
					Pawn p = new Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
					p.setInterestRate(rate);
					p.setForeclose(foreclose);
					p.setCdate(cdate);
					list.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(level==Level.ENCODER || level==Level.MANAGER){
			query += " and pawn.branch=?";
			query += " ORDER BY " + sort + " " + (order==SortOrder.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setInt(1, branchId);
				pstmt.setInt(2,(page-1)*pageSize);
				pstmt.setInt(3, pageSize);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()){
					int pid = rs.getInt(1);
					int branch = rs.getInt(2);
					Date loanDate = rs.getDate(3);
					int nameId = rs.getInt(4);
					float amount = rs.getFloat(5);
					int bpid = rs.getInt(6);
					float rate = rs.getFloat(7);
					float sc = rs.getFloat(8);
					int pt = rs.getInt(9);
					int bcode = rs.getInt(10);
					Timestamp cdate = rs.getTimestamp(11);
					Date expire = rs.getDate(12);
					int foreclose = rs.getInt(13);
					Pawn p = new Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
					p.setInterestRate(rate);
					p.setForeclose(foreclose);
					p.setCdate(cdate);
					list.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

}
