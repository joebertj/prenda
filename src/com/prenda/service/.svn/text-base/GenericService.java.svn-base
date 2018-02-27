/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.prenda.helper.DatabaseConnection;

public abstract class GenericService {
	protected Connection conn;
	protected PreparedStatement pstmt;
	
	protected int level;
	protected int branchId;
	protected int userId;
	protected int mode;
	
	protected String sort;
	protected int order;
	protected int page;
	protected int pageSize;
	protected String filter;
	protected String filterFormat;
	protected Date filterDate;
	
	public GenericService(){
		filterFormat = "MMM dd, yyyy";
		conn = DatabaseConnection.getConnection();
	}
	
	public int getBranchId() {
		return branchId;
	}


	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getMode() {
		return mode;
	}


	public void setMode(int mode) {
		this.mode = mode;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getPageSize() {
		return pageSize;
	}


	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}


	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFilterFormat() {
		return filterFormat;
	}

	public void setFilterFormat(String filterFormat) {
		this.filterFormat = filterFormat;
	}

	public Date getFilterDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(filterFormat);
		try {
			filterDate = sdf.parse(filter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return filterDate;
	}

	public void setFilterDate(Date filterDate) {
		this.filterDate = filterDate;
	}
}
