package com.prenda.service;

import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.model.obj.prenda.Customer;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

public class CustomerService {
	
	private static Logger log = Logger.getLogger(CustomerService.class);
	
	private String lastName;
	private String firstName;
	private String middleName;
	
	private List<Customer> customers;
	private List<Customer> allCustomers;
	
	private int pageSize;
	private int pageNum;
	private long count; 
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		customers = getCustomersbyName(lastName, firstName, middleName);
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		customers = getCustomersbyName(lastName, firstName, middleName);
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		customers = getCustomersbyName(lastName, firstName, middleName);
		this.middleName = middleName;
	}

	public List<Customer> getCustomers() {
		customers = getCustomersbyName(lastName, firstName, middleName);
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Customer> getAllCustomers() {
		DataLayerPrenda instance = DataLayerPrendaImpl.getInstance();
		Session s = instance.getCurrentSession();
		Criteria c = s.createCriteria(Customer.class);
		c.setFirstResult((pageNum-1)*pageSize);
		c.setMaxResults(pageSize);
		allCustomers = c.list();
		count = new Long(allCustomers.size());
		log.info("customers " + count);
		return allCustomers;
	}

	public void setAllCustomers(List<Customer> allCustomers) {
		this.allCustomers = allCustomers;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public long getCount() {
		if(lastName==null && firstName==null && middleName==null) { //lastName.isEmpty() && firstName.isEmpty() && middleName.isEmpty()
			DataLayerPrenda instance = DataLayerPrendaImpl.getInstance();
			Session session = instance.getCurrentSession();
			Criteria criteriaCount = session.createCriteria(Customer.class);
			criteriaCount.setProjection(Projections.rowCount());
			count = (Long) criteriaCount.uniqueResult();
		}
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	@Transactional
	public Customer getCustomerbyName(String lastName, String firstName, String middleName) {
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
	public List<Customer> getCustomersbyName(String lastName, String firstName, String middleName) {
		DataLayerPrenda instance = DataLayerPrendaImpl.getInstance();
		Session s = instance.getCurrentSession();
		Criteria c = s.createCriteria(Customer.class);
		if(lastName!=null && !lastName.isEmpty()) {
			c.add(Restrictions.ilike("lastName", lastName+"%", MatchMode.END));
		}
		if(firstName!=null && !firstName.isEmpty()) {
			c.add(Restrictions.ilike("firstName", firstName+"%", MatchMode.END));
		}
		if(middleName!=null && !middleName.isEmpty()) {
			c.add(Restrictions.ilike("middleName", middleName+"%", MatchMode.END));
		}
		c.setFirstResult((pageNum-1)*pageSize);
		c.setMaxResults(pageSize);
		List<Customer> customers = c.list();
		count = new Long(customers.size());
		log.info("customers " + count);
		return customers;
	}
	
}
