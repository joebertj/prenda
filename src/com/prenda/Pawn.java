/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda;

import java.sql.Timestamp;
import java.util.Date;

public class Pawn {
	protected int pid;
	protected long serial;
	protected int birCode;
	protected Date loanDate;
	protected Date expiryDate;
	protected int nameId;
	protected float loanAmount;
	protected float appraisedAmount;
	protected String description;
	protected float serviceCharge;
	protected float advanceInterest;
	protected String encoder;
	protected int extend;
	protected int branchId;
	protected int branchPid;
	protected int ptNumber;
	
	private float interestRate;
	private int redeem;
	private int pullout;
	private int foreclose;
	private Timestamp cdate;
	
	public Pawn(){
		
	}

	public Pawn(int pid, long serial, int birCode, Date loanDate, Date expiryDate, int nameId, float loanAmount, float appraisedAmount, String description, float serviceCharge, float advanceInterest, String encoder, int extend, int branchId, int branchPid, int ptNumber) {
		this.pid = pid;
		this.serial = serial;
		this.birCode = birCode;
		this.loanDate = loanDate;
		this.expiryDate = expiryDate;
		this.nameId = nameId;
		this.loanAmount = loanAmount;
		this.appraisedAmount = appraisedAmount;
		this.description = description;
		this.serviceCharge = serviceCharge;
		this.advanceInterest = advanceInterest;
		this.encoder = encoder;
		this.extend = extend;
		this.branchId = branchId;
		this.branchPid = branchPid;
		this.ptNumber = ptNumber;
	}

	public Timestamp getCdate() {
		return cdate;
	}

	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}

	public int getForeclose() {
		return foreclose;
	}

	public void setForeclose(int foreclose) {
		this.foreclose = foreclose;
	}

	public float getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(float interestRate) {
		this.interestRate = interestRate;
	}

	public int getPullout() {
		return pullout;
	}

	public void setPullout(int pullout) {
		this.pullout = pullout;
	}

	public int getRedeem() {
		return redeem;
	}

	public void setRedeem(int redeem) {
		this.redeem = redeem;
	}

	public float getAdvanceInterest() {
		return advanceInterest;
	}

	public void setAdvanceInterest(float advanceInterest) {
		this.advanceInterest = advanceInterest;
	}

	public float getAppraisedAmount() {
		return appraisedAmount;
	}

	public void setAppraisedAmount(float appraisedAmount) {
		this.appraisedAmount = appraisedAmount;
	}

	public int getBirCode() {
		return birCode;
	}

	public void setBirCode(int birCode) {
		this.birCode = birCode;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getBranchPid() {
		return branchPid;
	}

	public void setBranchPid(int branchPid) {
		this.branchPid = branchPid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEncoder() {
		return encoder;
	}

	public void setEncoder(String encoder) {
		this.encoder = encoder;
	}

	public int getExtend() {
		return extend;
	}

	public void setExtend(int extend) {
		this.extend = extend;
	}

	public float getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(float loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public int getNameId() {
		return nameId;
	}

	public void setNameId(int nameId) {
		this.nameId = nameId;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPtNumber() {
		return ptNumber;
	}

	public void setPtNumber(int ptNumber) {
		this.ptNumber = ptNumber;
	}

	public long getSerial() {
		return serial;
	}

	public void setSerial(long serial) {
		this.serial = serial;
	}

	public float getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(float serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
