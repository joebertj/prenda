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

import com.prenda.Level;
import com.prenda.Mode;
import com.prenda.Pullout;
import com.prenda.Branch;

public class PulloutService extends GenericService{

	public PulloutService(){
		super();
	}

	public int getAllPulloutCount(){
		return getAllPullout(level,branchId,userId,mode,sort,order,1,Integer.MAX_VALUE).size();
	}

	public List<Pullout> getAllPullout(){
		List<Pullout> list = new ArrayList<Pullout>();
		list = getAllPullout(level,branchId,userId,mode,sort,order,page,pageSize);
		return list;
	}

	public List<Pullout> getAllPullout(int level, int branchId, int userId, int mode, java.lang.String sort, int order, int page, int pageSize){
		List<Pullout> list = new ArrayList<Pullout>();
		String query = "SELECT pawn.pid,branch,loan_date,nameid,loan,bpid,rate,pawn.service_charge,pt,bcode,"+
		"pullout.create_date AS cdate,"+
		"ADDDATE(loan_date,120+15*pawn.extend) AS expire,"+
		"pullout_date " +
		"FROM pawn "+
		"LEFT JOIN customer ON pawn.nameid=customer.id " +
		"LEFT JOIN interest ON pawn.branch=interest.interestid " +
		"LEFT JOIN branch ON pawn.branch=branch.branchid " +
		"LEFT JOIN pullout ON pullout.pid=pawn.pid "+
		"WHERE pawn.pid=pullout.pid AND day=34";
		if(mode==Mode.DAILY){
			query += " and pullout_date=?";
		}else if(mode==Mode.MONTHLY){
			query += " and year(pullout_date)=year(?) and month(pullout_date)=month(?)";
		}else if(mode==Mode.YEARLY){
			query += " and year(pullout_date)=year(?)";
		}
		java.sql.Date sqlDate = new java.sql.Date(filterDate.getTime());
		if(level==Level.ADMIN){
			query += " ORDER BY " + sort + " " + (order==Mode.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
			try {
				pstmt = conn.prepareStatement(query);
				int i=1;
				if(mode!=Mode.ALL){
					pstmt.setDate(i++,sqlDate);
					if(mode==Mode.MONTHLY){
						pstmt.setDate(i++,sqlDate);
					}
				}
				pstmt.setInt(i++,(page-1)*pageSize);
				pstmt.setInt(i++, pageSize);
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
					Date pullout = rs.getDate(13);
					Pullout p = new Pullout(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt, pullout);
					p.setInterestRate(rate);
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
			int i = 0;
			while(li.hasNext()){
				Branch b = (Branch) li.next();
				if(i>0){
					branches += " or ";
				}
				branches += "pawn.branch = " + b.getId();
				i++;
			}
			if(branches.length()>0){
				query += " and (" + branches + ")";
			}
			query += " ORDER BY " + sort + " " + (order==Mode.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
			try {
				pstmt = conn.prepareStatement(query);
				i=1;
				if(mode!=Mode.ALL){
					pstmt.setDate(i++,sqlDate);
					if(mode==Mode.MONTHLY){
						pstmt.setDate(i++,sqlDate);
					}
				}
				pstmt.setInt(i++,(page-1)*pageSize);
				pstmt.setInt(i++, pageSize);
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
					Date pullout = rs.getDate(13);
					Pullout p = new Pullout(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt, pullout);
					p.setInterestRate(rate);
					p.setCdate(cdate);
					list.add(p);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(level==Level.ENCODER || level==Level.MANAGER){
			query += " and pawn.branch=?";
			query += " ORDER BY " + sort + " " + (order==Mode.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
			try {
				pstmt = conn.prepareStatement(query);
				int i=1;
				if(mode!=Mode.ALL){
					pstmt.setDate(i++,sqlDate);
					if(mode==Mode.MONTHLY){
						pstmt.setDate(i++,sqlDate);
					}
				}
				pstmt.setInt(i++, branchId);
				pstmt.setInt(i++,(page-1)*pageSize);
				pstmt.setInt(i++, pageSize);
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
					Date pullout = rs.getDate(13);
					Pullout p = new Pullout(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt, pullout);
					p.setInterestRate(rate);
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
