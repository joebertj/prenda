/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.service;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.prenda.Level;
import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.helper.PasswordEncoderGenerator;
import com.prenda.model.obj.prenda.Branch;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.data.DataLayerPrenda;
import com.prenda.service.data.DataLayerPrendaImpl;

public class UserService {
	
	private static Logger log = Logger.getLogger(UserService.class);
	
	private int userId;
	private String userName;
	private int level;
	private int branchId;
	private Date loanDate;
	private Date redeemDate;
	private Users user;
	private String lastName;
	private String firstName;
	private String middleName;
	private List<Users> users;
	private List<Users> allUsers;
	
	private int pageSize;
	private int pageNum;
	private long count; 
	
	public UserService(){
		user = null;
	}
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	@Transactional
	public long getCount() {
		DataLayerPrenda instance = DataLayerPrendaImpl.getInstance();
		Session session = instance.getCurrentSession();
		Criteria criteriaCount = session.createCriteria(Users.class);
		criteriaCount.setProjection(Projections.rowCount());
		count = (Long) criteriaCount.uniqueResult();
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public void setAllUsers(List<Users> allUsers) {
		this.allUsers = allUsers;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public String getFirstName() {
		firstName = user.getFirstname();
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
	
	public String getLastName() {
		lastName = user.getLastname();
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		middleName = user.getMiddlename();
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Transactional
	public Users saveUser(String targetUser, String newPassword, int targetUserLevel, int branchId, boolean archive) {
		Users user = new Users();
		String hashedNewPassword = PasswordEncoderGenerator.getHash(newPassword);
		user.setPassword(hashedNewPassword);
		user.setUsername(targetUser);
		user.setLevel((byte) (targetUserLevel & 0xff));
		user.setBranch(branchId);
		user.setArchive(archive);
		user = saveUser(user);
		BranchService bs = new BranchService();
		UserService us = new UserService();
		Branch branch;
		if(targetUserLevel == Level.OWNER) {
			branch = bs.saveBranch(targetUser); // Create new branch
			user.setBranch(branch.getId()); // Safely retrieve the generated branchId
			us.updateUser(user);
			branch.setOwner(user.getId()); // Update branch owner
			bs.updateBranch(branch);
		}else if(targetUserLevel < Level.OWNER) {
			user.setBranch(branchId); // Safely retrieve the generated branchId
			us.updateUser(user);
		}
		return user;
	}
	
	@Transactional
	public Users saveUser(Users user) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.save(user);
		dataLayerPrenda.flushAndClearSession();
		return user;
	}

	@Transactional
	public Users updateUser(Users user) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.update(user);
		dataLayerPrenda.flushAndClearSession();
		return user;
	}
	
	@Transactional
	public Users getUser(String username) {
		Users user = null;
		ListIterator <Users> li = HibernatePrendaDaoFactory.getUsersDao().findByCriteria(Restrictions.eq("username", username)).listIterator();
		if(li.hasNext()) {
			 user = (Users) li.next();
		}
		return user;
	}
	
	@Transactional
	public Users getUser(Integer userId) {
		Users user = null;
		ListIterator <Users> li = HibernatePrendaDaoFactory.getUsersDao().findByCriteria(Restrictions.eq("id", userId)).listIterator();
		if(li.hasNext()) {
			 user = (Users) li.next();
		}
		return user;
	}
	
	@Transactional
	public List<Users> getAllUsers() {
		DataLayerPrenda instance = DataLayerPrendaImpl.getInstance();
		Session session = instance.getCurrentSession();
		Criteria criteria = session.createCriteria(Users.class);
		criteria.setFirstResult((pageNum-1)*pageSize);
		criteria.setMaxResults(pageSize);
		allUsers = criteria.list();
		return allUsers;
	}
	
	@Transactional
	public List<Users> getUsers(int branchId) {
		List<Users> users = HibernatePrendaDaoFactory.getUsersDao()
				.findByCriteria(Restrictions.and(
						Restrictions.eq("branch", branchId),
						Restrictions.eq("archive", false)));
		return users;
	}
	
	@Transactional
	public List<Users> getUsers() {
		users = this.getUsers(branchId);
		return users;
	}
	
	public String getUserName(){
		userName = user.getUsername();
		return userName;
	}
	
	public int getUserId() {
		userId = user.getId();
		return userId;
	}

	@Transactional
	public void setUserId(int userId) {
		user = getUser(userId);
		this.userId = userId;
	}

	@Transactional
	public void setUserName(String userName) {
		user = getUser(userName);
		this.userName = userName;
	}

	public int getLevel() {
		level = user.getLevel();
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public int getBranchId() {
		branchId = user.getBranch();
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Date getLoanDate() {
		loanDate = user.getLoanDate();
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	
	public Date getRedeemDate() {
		redeemDate = user.getLoanDate(); // TODO add redeem date
		return redeemDate;
	}

	public void setRedeemDate(Date redeemDate) {
		this.redeemDate = redeemDate;
	}

}