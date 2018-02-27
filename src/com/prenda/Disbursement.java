/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda;

public class Disbursement {
	private int journalId;
	private String journalGroup;
	private int accountId;
	private int accountCode;
	private String description;
	private String accountName;
	private float amount;
	
	public Disbursement() {
		
	}

	public Disbursement(String journalGroup, int accountCode, String accountName, String description, float amount) {
		this.journalGroup = journalGroup;
		this.accountCode = accountCode;
		this.accountName = accountName;
		this.description = description;
		this.amount = amount;
	}

	public int getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(int accountCode) {
		this.accountCode = accountCode;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
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