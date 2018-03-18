/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda;

import java.util.Date;

public class Pullout extends Pawn {
	private Date pulloutDate;

	public Pullout(){
		
	}

	public Pullout(int pid, long serial, int birCode, java.util.Date loanDate, java.util.Date expiryDate, int nameId, float loanAmount, float appraisedAmount, java.lang.String description, float serviceCharge, float advanceInterest, java.lang.String encoder, int extend, int branchId, int branchPid, int ptNumber, Date pulloutDate){
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
		this.pulloutDate = pulloutDate;
	}

	public Date getPulloutDate() {
		return pulloutDate;
	}

	public void setPulloutDate(java.util.Date pulloutDate) {
		this.pulloutDate = pulloutDate;
	}

}
