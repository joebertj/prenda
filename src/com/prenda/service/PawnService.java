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

import org.apache.log4j.Logger;
import com.prenda.Branch;
import com.prenda.Level;
import com.prenda.Mode;
import com.prenda.Pawn;
import com.prenda.SortOrder;

public class PawnService extends GenericService {
	
	private static Logger log = Logger.getLogger(PawnService.class);
	
	public PawnService(){
		super();
	}
	
	public int getAllPawnCount(){
		return getAllPawn(level,branchId,userId,mode,sort,order,1,Integer.MAX_VALUE,filterDate).size();
	}
	
	public List<Pawn> getAllPawn(){
		List<Pawn> list = new ArrayList<Pawn>();
		list = getAllPawn(level,branchId,userId,mode,sort,order,page,pageSize,filterDate);
		return list;
	}
	
	public List<Pawn> getAllPawn(int level,int branchId,int userId,int mode,String sort,int order,int page,int pageSize,Date filterDate){
		List<Pawn> list = new ArrayList<Pawn>();
		String query = "SELECT pawn.pid,branch,loan_date,nameid,loan,bpid,rate,pawn.service_charge,pt,bcode,"+
		"pawn.create_date AS cdate,"+
		"ADDDATE(loan_date,120+15*pawn.extend) AS expire,"+
		"(ADDDATE(pawn.loan_date,120+15*pawn.extend) <= ?) AS foreclose,"+
		"(pawn.pid=redeem.pid) AS redeem,"+
		"(pawn.pid=pullout.pid) AS pullout "+
		"FROM pawn "+
		"LEFT JOIN genkey ON pawn.pid=genkey.pid "+
		"LEFT JOIN interest ON pawn.branch=interest.interestid "+
		"LEFT JOIN redeem ON redeem.pid=pawn.pid "+
		"LEFT JOIN pullout ON pullout.pid=pawn.pid "+
		"LEFT JOIN branch ON pawn.branch=branch.branchid "+
		"WHERE day=DATEDIFF(?,loan_date) ";
		if(mode==Mode.DAILY){
			query += "AND loan_date=?";
		}else if(mode==Mode.MONTHLY){
			query += "AND year(loan_date)=year(?) and month(loan_date)=month(?)";
		}else if(mode==Mode.YEARLY){
			query += "AND year(loan_date)=year(?)";
		}
		java.sql.Date sqlDate = new java.sql.Date(filterDate.getTime());
		log.info(sqlDate.toString());
		// Date is basically NOW() but there is a feature to use filterDate to override this for debugging
		int datesMinimum = 2;
		if(level==Level.ADMIN){
			query += " ORDER BY " + sort + " " + (order==SortOrder.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
			try {
				pstmt = conn.prepareStatement(query);
				int i=1;
				for(;i<=datesMinimum;i++){
					pstmt.setDate(i,sqlDate);
				}
				if(mode!=Mode.ALL){
					pstmt.setDate(i++,sqlDate);
					if(mode==Mode.MONTHLY){
						pstmt.setDate(i++,sqlDate);
					}
				}
				pstmt.setInt(i++,(page-1)*pageSize);
				pstmt.setInt(i++, pageSize);
				log.info(level + "level" + query);
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
					int redeem = rs.getInt(14);
					int pullout = rs.getInt(15);
					Pawn p = new Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
					p.setInterestRate(rate);
					p.setForeclose(foreclose);
					p.setRedeem(redeem);
					p.setPullout(pullout);
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
			query += " ORDER BY " + sort + " " + (order==SortOrder.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
			try {
				pstmt = conn.prepareStatement(query);
				i=1;
				for(;i<=datesMinimum;i++){
					pstmt.setDate(i,sqlDate);
				}
				if(mode!=Mode.ALL){
					pstmt.setDate(i++,sqlDate);
					if(mode==Mode.MONTHLY){
						pstmt.setDate(i++,sqlDate);
					}
				}
				pstmt.setInt(i++,(page-1)*pageSize);
				pstmt.setInt(i++, pageSize);
				log.info(level + "level" + query);
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
					int redeem = rs.getInt(14);
					int pullout = rs.getInt(15);
					Pawn p = new Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
					p.setInterestRate(rate);
					p.setForeclose(foreclose);
					p.setRedeem(redeem);
					p.setPullout(pullout);
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
				int i=1;
				for(;i<=datesMinimum;i++){
					pstmt.setDate(i,sqlDate);
				}
				if(mode!=Mode.ALL){
					pstmt.setDate(i++,sqlDate);
					if(mode==Mode.MONTHLY){
						pstmt.setDate(i++,sqlDate);
					}
				}
				pstmt.setInt(i++, branchId);
				pstmt.setInt(i++,(page-1)*pageSize);
				pstmt.setInt(i++, pageSize);
				log.info(level + "level" + query);
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
					int redeem = rs.getInt(14);
					int pullout = rs.getInt(15);
					Pawn p = new Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
					p.setInterestRate(rate);
					p.setForeclose(foreclose);
					p.setRedeem(redeem);
					p.setPullout(pullout);
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
