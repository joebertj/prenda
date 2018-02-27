/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Name {

	private int nid;
	private int aid;
	private String lname;
	private String fname;
	private String mname;
	private String address;
  
	public Name(String lname,String fname,String mname,int nid) {
		this.nid = nid;
		this.lname = lname;
		this.fname = fname;
		this.mname = mname;
	}
	
	public Name(String address,int aid) {
		this.aid = aid;
		this.address = address;
	}
  
	public int getNid() {
		return this.nid;
	}
	
	public int getAid() {
		return this.aid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}
	
	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getLname() {
		return this.lname;
	}

	public String getFname() {
		return this.fname;
	}	
	
	public String getMname() {
		return this.mname;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public void setMname(String mname) {
		this.mname = mname;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String toString() {
		return new ToStringBuilder(this).append("nid",nid).append("aid",aid).append("lname", lname).append("fname", fname).append("mname", mname).toString();
	}

}
