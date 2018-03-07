/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;


import java.util.Date;
import java.util.ListIterator;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.helper.DatabaseConnection;
import com.prenda.model.obj.prenda.Branch;
import com.prenda.model.obj.prenda.Customer;
import com.prenda.model.obj.prenda.Pawn;
import com.prenda.model.obj.prenda.Users;
import com.prenda.services.data.DataLayerPrenda;
import com.prenda.services.data.DataLayerPrendaImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Servlet implementation class for Servlet: CheckPawn
 *
 */
@Controller
 public class CheckPawn  {
    private static Logger log =Logger.getLogger(CheckPawn.class);

	@RequestMapping(value = "CheckPawn.htm", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_OWNER", "ROLE_MANAGER", "ROLE_LIAISON", "ROLE_ENCODER" })
	@Transactional
	protected String pawnItem(HttpSession session, ModelMap map, 
			@RequestParam("referer") String redirectUrl,
			@RequestParam("loandate") String loanDate,
			@RequestParam("lname") String lname,
			@RequestParam("fname") String fname,
			@RequestParam("mname") String mname,
			@RequestParam("mname") String address,
			@RequestParam("loanamt") float loan,
			@RequestParam("appamt") float appraised,
			@RequestParam("desc") String description,
			@RequestParam("service") float serviceCharge,
			@RequestParam("interest") float interest) {
		
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String encoder = auth.getName();
			DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
    		Connection conn = DatabaseConnection.getConnection();
    		PreparedStatement pstmt = null;
    		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
    		Date loandate = sdf.parse(loanDate);
    		log.info("loandate:" +loandate);
    		long serial=loandate.getTime();
    		Random generator = new Random(serial);
			int bcode=generator.nextInt(5)+1;
			int nameid=-1;
			Customer customer = new Customer();
			ListIterator<Customer> li = HibernatePrendaDaoFactory.getCustomerDao().findByCriteria(Restrictions.and(Restrictions.eq("lastName",lname), Restrictions.and(Restrictions.eq("firstName",fname),Restrictions.eq("middleName",mname)))).listIterator();
			if(li.hasNext()){
				customer = (Customer) li.next();
				nameid = customer.getId().intValue();
			}else{
				customer.setId(0L);
				customer.setFirstName(fname);
				customer.setLastName(lname);
				customer.setMiddleName(mname);
				customer.setAddress(address);
				customer.setArchive(false);
				dataLayerPrenda.save(customer);
			}
			long pid=0L;
			ListIterator<Users> liu = HibernatePrendaDaoFactory.getUsersDao().findByCriteria(Restrictions.eq("username",encoder)).listIterator();
    		Users user;
    		Branch branch;
    		int branchId=0;
    		int bpid=0;
    		long pt=0;
    		if(liu.hasNext()) {
    			user = (Users) liu.next();
    			branchId=user.getBranch();
    		}
			ListIterator<Branch> lib = HibernatePrendaDaoFactory.getBranchDao().findByCriteria(Restrictions.eq("id",branchId)).listIterator();
			if(lib.hasNext()) {
    			branch = (Branch) lib.next();
    			// TODO remove bpid from pawn and counter from branch table
    			//bpid=branch.getCounter()+1;
    			pt=branch.getPtNumber();
    		}
    		Pawn pawn = new Pawn();
    		pawn.setId(pid);
    		pawn.setSerial(serial); log.info("serial " + serial);
    		pawn.setBcode((byte)(bcode & 0xFF));
    		pawn.setLoanDate(loandate);
    		pawn.setNameid((long) nameid);
    		pawn.setLoan((double) loan);
    		pawn.setAppraised((double) appraised);
    		pawn.setDescription(description);
    		pawn.setAdvanceInterest((double) interest);
    		pawn.setServiceCharge((double) serviceCharge);
    		pawn.setEncoder(encoder);
    		pawn.setBranch((long) branchId);
    		pawn.setBpid((long) bpid);
    		pawn.setPt((long) pt);
    		pawn.setCreateDate(loandate);
    		pawn.setExtend((byte)0);
    		dataLayerPrenda.save(pawn);
    		String genkey="";
    		generator = RandomUtils.JVM_RANDOM;
    		for(int i=0;i<10;i++){
    			boolean j=generator.nextBoolean();
    			if(j){
    				genkey+=(char) (generator.nextInt(26)+97);
    			}else{
    				genkey+=(char) (generator.nextInt(10)+48);
    			}
    		}
    		pstmt = conn.prepareStatement("UPDATE branch SET balance=balance-?,counter=counter+1,pt_number=pt_number+1 WHERE branchid=?");
    		pstmt.setFloat(1,loan);
    		pstmt.setInt(2,branchId);
    		pstmt.executeUpdate();
    		pstmt = conn.prepareStatement("INSERT INTO genkey VALUES (?,?)");
    		pstmt.setLong(1,pid);
    		pstmt.setString(2,genkey);
    		pstmt.executeUpdate();
    		pstmt = conn.prepareStatement("UPDATE users SET loan_date=? WHERE username=?");
    		pstmt.setDate(1,new java.sql.Date(loandate.getTime()));
    		pstmt.setString(2,encoder);
    		pstmt.executeUpdate();
    		dataLayerPrenda.flushAndClearSession();
    		map.addAttribute("pid",pid);
    		map.addAttribute("msg","Pawn with pid "+pid+" successfully stored");
    		redirectUrl="pawndetailpdf.jsp";
    	}catch (SQLException ex) {
    		ex.printStackTrace();
            log.debug("SQLException: " + ex.getMessage());
            log.debug("SQLState: " + ex.getSQLState());
            log.debug("VendorError: " + ex.getErrorCode());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return redirectUrl;	
    }
	
}