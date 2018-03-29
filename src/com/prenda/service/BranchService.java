/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.util.List;
import java.util.ListIterator;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.model.obj.prenda.Branch;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

public class BranchService {

	private int id;
	private int userId;
	private String name;
	private String address;
	private long counter;
	private long pawnTicket;
	private double advanceInterest;
	private double serviceCharge;
	private int minDaysToExtend;
	private int reserveDuration;
	private double balance;
	private Branch branch;
	private List<Branch> branches;
	private List<Branch> sisterBranches;
	private List<Branch> allBranches;
	private int ownerId;
	
	public BranchService() {
		
	}

	public void setAllBranches(List<Branch> allBranches) {
		this.allBranches = allBranches;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Branch> getSisterBranches() {
		sisterBranches = getSisterBranches(userId);
		return sisterBranches;
	}

	public void setSisterBranches(List<Branch> sisterBranches) {
		this.sisterBranches = sisterBranches;
	}

	@Transactional
	public List<Branch> getAllBranches() {
		allBranches = HibernatePrendaDaoFactory.getBranchDao().findAll();
		return allBranches;
	}
	
	@Transactional
	public List<Branch> getSisterBranches(int userId) { // branches with same owner
		UserService us = new UserService();
		Users user = us.getUser(userId);
		int branchId = user.getBranch();
		Branch branch = getBranch(branchId);
		int ownerId = branch.getOwner();
		List<Branch> list = getBranches(ownerId);
		return list;
	}
	
	@Transactional
	public List<Branch> getBranches() {
		ownerId = branch.getOwner();
		branches = this.getBranches(ownerId);
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

	public void setPawnTicket(long pawnTicket) {
		this.pawnTicket = pawnTicket;
	}

	public void setAdvanceInterest(double advanceInterest) {
		this.advanceInterest = advanceInterest;
	}

	@Transactional
	public Branch getBranch() {
		branch = this.getBranch(id);
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Transactional
	public Branch saveBranch(Branch branch) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.save(branch);
		dataLayerPrenda.flushAndClearSession();
		return branch;
	}
	
	@Transactional
	protected Branch deleteBranch(Branch branch) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.delete(branch);
		dataLayerPrenda.flushAndClearSession();
		return branch;
	}
	
	@Transactional
	protected Branch updateBranch(Branch branch) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.update(branch);
		dataLayerPrenda.flushAndClearSession();
		return branch;
	}
	
	@Transactional
	public Branch saveBranch(String ownerName) {
		Branch branch = new Branch();
		branch.setId(0);
		branch.setOwner(0);
		branch.setArchive(false);
		branch.setAddress("Default Address of " + ownerName + "'s Default Pawnshop");
		branch.setAdvanceInterest(0.0d);
		branch.setBalance(0.0d);
		branch.setCounter(0L);
		branch.setExtend((byte) (15 & 0xff));
		branch.setName("Default Pawnshop of " + ownerName);
		branch.setPtNumber(0L);
		branch.setReserve((byte) (15 & 0xff));
		branch.setServiceCharge(0.0d);
		branch.setAppraisedMargin(100d);
		branch.setAuctionMarkup((byte) (10 & 0xff));
		branch.setEditMinute((byte) (15 & 0xff));
		branch.setExpiry((byte) (15 & 0xff));
		branch.setMaturity((byte) (15 & 0xff));
		saveBranch(branch);
		return branch;
	}
	
	@Transactional
	public int getOwnerId(Integer branchId) {
		return getBranch(branchId).getOwner();
	}

	public int getOwnerId() {
		ownerId = branch.getOwner();
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		name = branch.getName();
		return name;
	}

	public String getAddress() {
		address = branch.getAddress();
		return address;
	}

	public int getId() {
		return id;
	}

	@Transactional
	public void setId(int id) {
		branch = getBranch(id);
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCounter() {
		counter = branch.getCounter();
		return counter;
	}

	public long getPawnTicket() {
		pawnTicket = branch.getPtNumber();
		return pawnTicket;
	}

	public void setPawnTicket(int pawnTicket) {
		this.pawnTicket = pawnTicket;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public double getAdvanceInterest() {
		advanceInterest = branch.getAdvanceInterest();
		return advanceInterest;
	}

	public void setAdvanceInterest(float advanceInterest) {
		this.advanceInterest = advanceInterest;
	}

	public int getMinDaysToExtend() {
		minDaysToExtend = branch.getExtend();
		return minDaysToExtend;
	}

	public void setMinDaysToExtend(int minDaysToExtend) {
		this.minDaysToExtend = minDaysToExtend;
	}

	public int getReserveDuration() {
		reserveDuration = branch.getReserve();
		return reserveDuration;
	}

	public void setReserveDuration(int reserveDuration) {
		this.reserveDuration = reserveDuration;
	}

	public double getServiceCharge() {
		serviceCharge = branch.getServiceCharge();
		return serviceCharge;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public double getBalance() {
		balance = branch.getBalance();
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Transactional
	public List<Branch> getBranches(int ownerId) {
		List<Branch> list = HibernatePrendaDaoFactory.getBranchDao().findByCriteria(Restrictions.eq("owner", ownerId));
		return list;
	}

	@Transactional
	public Branch getBranch(int branchId) {
		Branch branch = null;
		ListIterator<Branch> li = HibernatePrendaDaoFactory.getBranchDao()
				.findByCriteria(Restrictions.eq("id", branchId)).listIterator();
		if (li.hasNext()) {
			branch = (Branch) li.next();
		}
		return branch;
	}

}