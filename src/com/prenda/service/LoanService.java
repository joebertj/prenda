package com.prenda.service;

public class LoanService {
	double appraised, advanceInterest, serviceCharge, margin = 100;
	double loan, interestRate;
	double interest, netProceeds;
	
	public void setInterest(double interest) {
		this.interest = interest;
	}

	public void setNetProceeds(double netProceeds) {
		this.netProceeds = netProceeds;
	}

	public void setAppraised(double appraised) {
		this.appraised = appraised;
	}

	public void setAdvanceInterest(double advanceInterest) {
		this.advanceInterest = advanceInterest;
	}

	public void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public void setMargin(double margin) {
		this.margin = margin;
	}

	public void setLoan(double loan) {
		this.loan = loan;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public double getNetProceeds(){
		return this.getNetProceeds(appraised, advanceInterest, serviceCharge, margin);
	}
	
	public double getInterest(){
		return this.getInterest(appraised, advanceInterest, margin);
	}
	
	public double getRedeemAmount(){
		return this.getRedeemAmount(loan, interestRate);
	}
	
	public double getNetProceeds(double appraised, double advanceInterest, double serviceCharge, double margin){
		double net = 0.0d;
		net = (appraised - margin) * (1 - advanceInterest/100) - serviceCharge;
		return net;
	}
	
	public double getInterest(double appraised, double advanceInterest, double margin){
		double interest = 0.0d;
		interest = (appraised - margin) * advanceInterest/100;
		return interest;
	}
	
	public double getRedeemAmount(double loan, double interestRate){
		double net = 0.0d;
		net = loan * (1 + interestRate/100);
		return net;
	}
}
