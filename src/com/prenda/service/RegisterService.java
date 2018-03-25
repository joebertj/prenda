package com.prenda.service;

import java.util.ListIterator;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.model.obj.prenda.Register;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

public class RegisterService {
	
	@Transactional
	public Register saveRegister(Register register) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.save(register);
		dataLayerPrenda.flushAndClearSession();
		return register;
	}
	
	@Transactional
	public String getKey(int id) {
		Register register = new Register();
		ListIterator <Register> li = HibernatePrendaDaoFactory.getRegisterDao().findByCriteria(Restrictions.eq("id", id)).listIterator();
		if(li.hasNext()) {
			register = (Register) li.next();
		}
		return register.getPassword();
	}
}
