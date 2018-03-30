package com.prenda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.model.obj.prenda.Jewelry;
import com.prenda.model.obj.prenda.JewelryPK;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

public class JewelryService {
	private int branchId;
	private int caratId;
	private double minimum;
	private double maximum;
	private Jewelry jewelry;
	private List<Jewelry> jewelries;
	
	public JewelryService(){
		
	}
	
	public Jewelry getJewelry() {
		return jewelry;
	}

	public void setJewelries(List<Jewelry> jewelries) {
		this.jewelries = jewelries;
	}

	public void setJewelry(Jewelry jewelry) {
		this.jewelry = jewelry;
	}

	@Transactional
	public Jewelry getJewelry(int branchId, int caratId) {
		Jewelry jewelry = new Jewelry();
		JewelryPK jpk = new JewelryPK();
		jpk.setBranchid(branchId);
		jpk.setCaratid((byte) (caratId & 0xff));
		ListIterator<Jewelry> list = HibernatePrendaDaoFactory.getJewelryDao().findByCriteria(Restrictions.eq("id", jpk)).listIterator();
		if(list.hasNext()) {
			jewelry = (Jewelry) list.next();
		}
		return jewelry;
	}
	
	@Transactional
	public List<Jewelry> getJewelry(int branchId) {
		List<Jewelry> list = new ArrayList<Jewelry>();
		int[] cid = {10, 14, 18, 22, 24};
		for(int c: cid) {
			JewelryPK jpk = new JewelryPK();
			jpk.setBranchid(branchId);
			jpk.setCaratid((byte) (c & 0xff));
			ListIterator<Jewelry> li = HibernatePrendaDaoFactory.getJewelryDao().findByCriteria(Restrictions.eq("id", jpk)).listIterator();
			if(li.hasNext()) {
				list.add((Jewelry)li.next());
			}else { // TODO add on BranchService Mode.CREATENEW
				Jewelry jewelry = new Jewelry();
				Log.info("carat: " + c);
				jewelry.setId(jpk);
				jewelry.setMinimum(0d);
				jewelry.setMaximum(0d);
				list.add(jewelry);
				// TODO persist
			}
		}
		return list;
	}
	
	@Transactional
	public Jewelry save(Jewelry jewelry) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.save(jewelry);
		dataLayerPrenda.flushAndClearSession();
		return jewelry;
	}
	
	@Transactional
	public List<Jewelry> getJewelries(){
		jewelries = getJewelry(branchId);
		return jewelries;
	}	

	public int getBranchId() {
		return branchId;
	}

	@Transactional
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getCaratId() {
		return caratId;
	}

	public void setCaratId(int caratId) {
		this.caratId = caratId;
	}

	@Transactional
	public double getMinimum() {
		jewelry = getJewelry(branchId, caratId);
		minimum = jewelry.getMinimum();
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	@Transactional
	public double getMaximum() {
		jewelry = getJewelry(branchId, caratId);
		maximum = jewelry.getMaximum();
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}
	
}
