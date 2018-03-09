package com.prenda.service;

import java.util.ListIterator;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.model.obj.prenda.Customer;

public class CustomerService {
	
	@Transactional
	public Customer getCustomerbyNames(String lastName, String firstName, String middleName) {
		Customer customer = new Customer();
		ListIterator<Customer> li = HibernatePrendaDaoFactory.getCustomerDao()
				.findByCriteria(Restrictions.and(Restrictions.eq("lastName", lastName),
						Restrictions.and(Restrictions.eq("firstName", firstName), Restrictions.eq("middleName", middleName))))
				.listIterator();
		if (li.hasNext()) {
			customer = (Customer) li.next();
		}
		return customer;
	}
}
