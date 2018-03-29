package com.prenda.service;

import java.util.List;
import java.util.ListIterator;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.model.obj.prenda.Customer;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

public class CustomerService {
	
	@Transactional
	public Customer getCustomerbyNames(String lastName, String firstName, String middleName) {
		Customer customer = new Customer();
		ListIterator<Customer> li = HibernatePrendaDaoFactory.getCustomerDao()
				.findByCriteria(Restrictions.and(Restrictions.eq("lastName", lastName),
						Restrictions.and(Restrictions.eq("firstName", firstName), Restrictions.eq("middleName", middleName))))
				.listIterator();
		if (li.hasNext()) {
			customer = li.next();
		}
		return customer;
	}
	
	@Transactional
	public List<Customer> getCustomersbyNames(String lastName, String firstName, String middleName) {
		DataLayerPrenda instance = DataLayerPrendaImpl.getInstance();
		Session s = instance.getCurrentSession();
		Criteria c = s.createCriteria(Customer.class);
		if(!lastName.isEmpty()) {
			c.add(Restrictions.like("lastName", lastName+"%", MatchMode.END));
		}
		if(!lastName.isEmpty()) {
			c.add(Restrictions.like("firstName", firstName+"%", MatchMode.END));
		}
		if(!lastName.isEmpty()) {
			Restrictions.like("middleName", middleName+"%", MatchMode.END);
		}
		List<Customer> customers = c.list();
		return customers;
	}
}
