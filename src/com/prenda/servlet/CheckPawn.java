/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;

import java.util.Date;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prenda.helper.PasswordGenerator;
import com.prenda.model.obj.prenda.Branch;
import com.prenda.model.obj.prenda.Customer;
import com.prenda.model.obj.prenda.Genkey;
import com.prenda.model.obj.prenda.Pawn;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.BranchService;
import com.prenda.service.CustomerService;
import com.prenda.service.UserService;
import com.prenda.services.data.DataLayerPrenda;
import com.prenda.services.data.DataLayerPrendaImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Servlet implementation class for Servlet: CheckPawn
 *
 */
@Controller
public class CheckPawn {
	private static Logger log = Logger.getLogger(CheckPawn.class);

	@RequestMapping(value = "CheckPawn.htm", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_OWNER", "ROLE_MANAGER", "ROLE_LIAISON", "ROLE_ENCODER" })
	@Transactional
	protected String pawnItem(HttpSession session, ModelMap map, @RequestParam("referer") String redirectUrl,
			@RequestParam("loandate") String loanDate, @RequestParam("lname") String lname,
			@RequestParam("fname") String fname, @RequestParam("mname") String mname,
			@RequestParam("address") String address, @RequestParam("loanamt") float loan,
			@RequestParam("appamt") float appraised, @RequestParam("desc") String description,
			@RequestParam("service") float serviceCharge, @RequestParam("interest") float interest) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String encoder = auth.getName();
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date loandate = new Date();
		try {
			loandate = sdf.parse(loanDate);
		} catch (ParseException e) {
			log.info(e.getMessage());
		}
		log.debug("loandate: " + loandate);
		long serial = loandate.getTime();
		Random generator = RandomUtils.JVM_RANDOM;
		int bcode = generator.nextInt(5) + 1;
		int nameid = -1;
		CustomerService cs = new CustomerService();
		Customer customer = cs.getCustomerbyNames(lname, fname, mname);
		if (customer.getId() > 0) {
			nameid = customer.getId().intValue();
		} else {
			customer.setId(0L);
			customer.setFirstName(fname);
			customer.setLastName(lname);
			customer.setMiddleName(mname);
			customer.setAddress(address);
			customer.setArchive(false);
			dataLayerPrenda.save(customer);
		}
		UserService us = new UserService();
		Users user = us.getUser(encoder);
		Branch branch;
		int branchId = 0;
		long bpid = 0;
		long pt = 0;
		branchId = user.getBranch();
			user.setLoanDate(new java.sql.Date(loandate.getTime()));
			dataLayerPrenda.update(user);
		BranchService bs = new BranchService();
			branch = bs.getBranchbyId(branchId);
			bpid = branch.getCounter() + 1;
			pt = branch.getPtNumber();
			branch.setBalance(branch.getBalance() - loan);
			branch.setCounter(bpid);
			branch.setPtNumber(pt + 1);
			dataLayerPrenda.update(branch);
		Pawn pawn = new Pawn();
		pawn.setSerial(serial);
		log.info("serial " + serial);
		pawn.setBcode((byte) (bcode & 0xFF));
		pawn.setLoanDate(loandate);
		pawn.setNameid(nameid*1L);
		pawn.setLoan(loan*1.0d);
		pawn.setAppraised(appraised*1.0d);
		pawn.setDescription(description);
		pawn.setAdvanceInterest(interest*1.0d);
		pawn.setServiceCharge(serviceCharge*1.0d);
		pawn.setEncoder(encoder);
		pawn.setBranch(branchId*1L);
		pawn.setBpid(bpid*1L);
		pawn.setPt(pt*1L);
		pawn.setCreateDate(loandate);
		pawn.setExtend((byte) 0);
		dataLayerPrenda.save(pawn);
		long pid = pawn.getId();
		log.info("pid: " + pid);
		Genkey gk = new Genkey();
		gk.setId(pid);
		gk.setPassword(PasswordGenerator.getPassword());
		dataLayerPrenda.save(gk);
		dataLayerPrenda.flushAndClearSession();
		map.addAttribute("pid", pid);
		map.addAttribute("msg", "Pawn with pid " + pid + " successfully stored");
		redirectUrl = "pawndetailpdf";
		return redirectUrl;
	}

}