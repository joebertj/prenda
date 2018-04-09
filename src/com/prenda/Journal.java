/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda;

public class Journal {
	private int journalId;
	private String journalGroup;
	private int accountCode;
	private String description;
	private String accountName;
	private double amount;
	private boolean drcr;
	
	public Journal() {
		
	}

	public Journal(String journalGroup, int accountCode, String accountName, String description, double amount, boolean drcr) {
		this.journalGroup = journalGroup;
		this.accountCode = accountCode;
		this.accountName = accountName;
		this.description = description;
		this.amount = amount;
		this.drcr = drcr;
	}

	public boolean isDrcr() {
		return drcr;
	}

	public void setDrcr(boolean drcr) {
		this.drcr = drcr;
	}

	public int getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(int accountCode) {
		this.accountCode = accountCode;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJournalGroup() {
		return journalGroup;
	}

	public void setJournalGroup(String journalGroup) {
		this.journalGroup = journalGroup;
	}

	public int getJournalId() {
		return journalId;
	}

	public void setJournalId(int journalId) {
		this.journalId = journalId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
}