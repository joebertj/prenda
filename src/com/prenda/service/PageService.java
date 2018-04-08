/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.util.ListIterator;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.model.obj.prenda.Page;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

public class PageService {

	private byte user;  
	private byte customer;
	private byte pawn;
	private byte redeem;
	private byte foreclose;
	private byte pullout;
	private byte outstanding;
	private byte inventory;
	private byte auction;
	
	private int branch;
	private int chart;
	private int disburse;
	
	private int branchId;
	
	private Page page;
	
	public PageService() {
		user = (byte) (10 & 0xff);  
		customer = (byte) (10 & 0xff);
		pawn = (byte) (10 & 0xff);
		redeem = (byte) (10 & 0xff);
		foreclose = (byte) (10 & 0xff);
		pullout = (byte) (10 & 0xff);
		outstanding = (byte) (10 & 0xff);
		inventory = (byte) (10 & 0xff);
		auction = (byte) (10 & 0xff);
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public byte getUser() {
		return user;
	}

	public void setUser(byte user) {
		this.user = user;
	}

	public byte getCustomer() {
		return customer;
	}

	public void setCustomer(byte customer) {
		this.customer = customer;
	}

	public byte getPawn() {
		return pawn;
	}

	public void setPawn(byte pawn) {
		this.pawn = pawn;
	}

	public byte getRedeem() {
		return redeem;
	}

	public void setRedeem(byte redeem) {
		this.redeem = redeem;
	}

	public byte getForeclose() {
		return foreclose;
	}

	public void setForeclose(byte foreclose) {
		this.foreclose = foreclose;
	}

	public byte getPullout() {
		return pullout;
	}

	public void setPullout(byte pullout) {
		this.pullout = pullout;
	}

	public byte getOutstanding() {
		return outstanding;
	}

	public void setOutstanding(byte outstanding) {
		this.outstanding = outstanding;
	}

	public byte getInventory() {
		return inventory;
	}

	public void setInventory(byte inventory) {
		this.inventory = inventory;
	}

	public byte getAuction() {
		return auction;
	}

	public void setAuction(byte auction) {
		this.auction = auction;
	}

	public int getBranchId() {
		return branchId;
	}

	@Transactional
	public void setBranchId(int branchId) {
		ListIterator<Page> li = HibernatePrendaDaoFactory.getPageDao().findByCriteria(Restrictions.eq("id", branchId)).listIterator();
		if(li.hasNext()) {
			page = (Page) li.next();
			user = page.getUser(); 
			customer = page.getCustomer();
			pawn = page.getPawn();
			redeem = page.getRedeem();
			foreclose = page.getForeclose();
			pullout = page.getPullout();
			outstanding = page.getOutstanding();
			inventory = page.getInventory();
			auction = page.getAuction();
		}else { // Create page if not present
			Page page = new Page();
			page.setId(branchId);
			page.setAuction(auction);
			page.setCustomer(customer);
			page.setForeclose(foreclose);
			page.setInventory(inventory);
			page.setOutstanding(outstanding);
			page.setPawn(pawn);
			page.setPullout(pullout);
			page.setRedeem(redeem);
			page.setUser(user);
			DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
			dataLayerPrenda.save(page);
			dataLayerPrenda.flushAndClearSession();
		}
		this.branchId = branchId;
	}

	public int getBranch() {
		branch=10; // TODO add to table
		return branch;
	}

	public void setBranch(int branch) {
		this.branch = branch;
	}

	public int getChart() {
		chart=50; // TODO add to table
		return chart;
	}

	public void setChart(int chart) {
		this.chart = chart;
	}

	public int getDisburse() {
		disburse=20; // TODO add to table
		return disburse;
	}

	public void setDisburse(int disburse) {
		this.disburse = disburse;
	}
	
}
