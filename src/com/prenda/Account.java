/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda;

public class Account {
	private int accountId;
	private int accountCode;
	private String accountName;
	
	public Account() {
		
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	
	
}
