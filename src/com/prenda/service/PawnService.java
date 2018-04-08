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
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Level;
import com.prenda.Mode;
import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.model.obj.prenda.Pawn;
import com.prenda.model.obj.prenda.Branch;

public class PawnService extends GenericService { // Pagination variables are here
	
	private static Logger log = Logger.getLogger(PawnService.class);
	
	public PawnService(){
		super();
	}
	
	@Transactional
	public Pawn getPawnById(long id) {
		Pawn pawn = new Pawn();
		ListIterator<Pawn> li = HibernatePrendaDaoFactory.getPawnDao()
				.findByCriteria(Restrictions.eq("id", id)).listIterator();
		if (li.hasNext()) {
			pawn = (Pawn) li.next();
		}
		return pawn;
	}
	
	public int getAllPawnCount(){
		return getAllPawn(level,branchId,userId,mode,sort,order,1,Integer.MAX_VALUE,filterDate).size();
	}
	
	public List<com.prenda.Pawn> getAllPawn(){
		List<com.prenda.Pawn> list = new ArrayList<com.prenda.Pawn>();
		list = getAllPawn(level,branchId,userId,mode,sort,order,pageNum,pageSize,filterDate);
		return list;
	}
	
	@Transactional
	public List<com.prenda.Pawn> getAllPawn(int level,int branchId,int userId,int mode,String sort,int order,int page,int pageSize,Date filterDate){
		List<com.prenda.Pawn> list = new ArrayList<com.prenda.Pawn>();
		String query = "SELECT pawn.pid,branch,loan_date,nameid,loan,bpid,rate,pawn.service_charge,pt,bcode,"+
		"pawn.create_date AS cdate,"+
		"ADDDATE(loan_date,120+15*pawn.extend) AS expire,"+
		"(? > ADDDATE(pawn.loan_date,120+15*pawn.extend)) AS foreclose,"+
		"(pawn.pid=redeem.pid) AS redeem,"+
		"(pawn.pid=pullout.pid) AS pullout "+
		"FROM pawn "+
		"LEFT JOIN genkey ON pawn.pid=genkey.pid "+
		"LEFT JOIN interest ON pawn.branch=interest.interestid "+
		"LEFT JOIN redeem ON pawn.pid=redeem.pid "+
		"LEFT JOIN pullout ON pawn.pid=pullout.pid "+
		"LEFT JOIN branch ON pawn.branch=branch.branchid "+
		// set day to [0,34] all overflows stop at 34
		"WHERE ((day=DATEDIFF(?,loan_date) AND DATEDIFF(?,loan_date)<=34) OR (day=34 AND DATEDIFF(?,loan_date)>34)) ";
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
		int datesMinimum = 4;
		if(level==Level.ADMIN){
			query += " ORDER BY " + sort + " " + (order==Mode.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
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
					com.prenda.Pawn p = new com.prenda.Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
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
			query += " ORDER BY " + sort + " " + (order==Mode.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
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
					com.prenda.Pawn p = new com.prenda.Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
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
			query += " ORDER BY " + sort + " " + (order==Mode.DESC ? "DESC" : "ASC") + " LIMIT ?,?";
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
					com.prenda.Pawn p = new com.prenda.Pawn(pid, loanDate.getTime(), bcode, loanDate, expire, nameId, amount, amount+100, query, sc, pid, query, pid, branch, bpid, pt);
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
