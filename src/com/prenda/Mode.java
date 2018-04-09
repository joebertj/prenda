/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda;

public class Mode {
	
	// Dates
	
	public static final int DAILY = 1;
	
	public static final int MONTHLY = 2;
	
	public static final int YEARLY = 3;
	
	public static final int ALL = 99;
	
	// CRUD
	
	public static final int CREATENEW = 0;
	
	public static final int DELETE = 1;
	
	public static final int UPDATE = 2;
	
	// Names
	
    public static final int LASTNAME = 0;
	
	public static final int FIRSTNAME = 1;
	
	public static final int MIDDLENAME = 2;
	
	// SORT
	
	public static final int DESC = 1;
	
	public static final int ASC = 2;
	
	// JWT
	
	public static final int PAT = 1;
	
	public static final int JWT = 2;
	
	public static final int OAUTH = 2;
	
	public static final int PKCS1 = 1;
	
	public static final int PKCS8 = 2;	
	
	// Accounting 
	
	public static final boolean DEBIT = false;
	
	public static final boolean CREDIT = true;	
	
	public static final int ASSET = 1;

	public static final int LIABILITY = 2;
	
	public static final int CAPITAL = 3;
	
	public static final int INCOME = 4;
	
	public static final int EXPENSE = 5;
}
