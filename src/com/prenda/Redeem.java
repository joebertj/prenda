/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda;

import java.util.Date;

public class Redeem extends Pawn{
	private Date redeemDate;
	
	public Redeem(){
		
	}
	
	public Redeem(int pid, long serial, int birCode, Date loanDate, Date expiryDate, int nameId, float loanAmount, float appraisedAmount, String description, float serviceCharge, float advanceInterest, String encoder, int extend, int branchId, int branchPid, int ptNumber, Date redeemDate) {
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
		this.redeemDate = redeemDate;
	}

	public Date getRedeemDate() {
		return redeemDate;
	}

	public void setRedeemDate(Date redeemDate) {
		this.redeemDate = redeemDate;
	}
	
	
}
