/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;

import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.prenda.Level;
import com.prenda.Mode;
import com.prenda.factories.prenda.HibernatePrendaDaoFactory;
import com.prenda.helper.PasswordEncoderGenerator;
import com.prenda.helper.PasswordGenerator;
import com.prenda.model.obj.prenda.Branch;
import com.prenda.model.obj.prenda.Register;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.BranchService;
import com.prenda.service.UserService;
import com.prenda.services.data.DataLayerPrenda;
import com.prenda.services.data.DataLayerPrendaImpl;

/**
 * Servlet implementation class for Servlet: UserModify
 * 
 */
@Controller
public class UserModify {

	private static Logger log = Logger.getLogger(UserModify.class);

	private Users user;

	@RequestMapping(value = "UserModify.htm", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_OWNER", "ROLE_MANAGER", "ROLE_LIAISON", "ROLE_ENCODER" })
	@Transactional
	private String modifyUserActionSelecor(HttpSession session, ModelMap map,
			@RequestParam("referer") String redirectUrl, @RequestParam("modtype") int mode,
			@RequestParam("user") String targetUser, @RequestParam("pass") String oldPassword,
			@RequestParam("pass1") String newPassword, @RequestParam("pass2") String verifyPassword,
			@RequestParam("level") int targetLevel, @RequestParam("branch") int targetBranch) {
		String message = "Action is not valid";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String actionUser = auth.getName();
		if (mode == Mode.CREATENEW) {
			message = createNewUser(actionUser, targetUser, newPassword, verifyPassword, targetLevel, targetBranch, false);
		} else if (mode == Mode.DELETE) {
			message = "Under construction";
		} else if (mode == Mode.UPDATE) {
			message = changePassword(actionUser, targetUser, oldPassword, newPassword, verifyPassword);
		}
		map.addAttribute("msg", message);
		log.info("message: " + message + " redirectUrl: " + redirectUrl);
		return redirectUrl;
	}
	
	@Transactional
	protected String createNewOwner(String targetUser, String newPassword, String verifyPassword,
			int targetLevel) {
		int targetBranch = 0;
		String message;
		message = createNewUser("admin", targetUser, newPassword, verifyPassword, targetLevel, targetBranch, true);
		log.info("createNewOwner message " + message);
		if(message.equals("User added successfully")) {
			UserService us = new UserService();
			int id = us.getIdByUsername(targetUser);
			String key = PasswordGenerator.getPassword(128);
			Register register = new Register();
			register.setId(id);
			register.setPassword(key);
			saveRegister(register);
			// TODO send email
						// POST register/ActivateOwner.htm
						// ?referer=../common/login.jsp
						// &user=targetUser
						// &key=key
			message = "Please check your email and follow instructions to complete registration";
		}
		return message;
	}

	@Transactional
	protected String createNewUser(String actionUser, String targetUser, String newPassword, String verifyPassword,
			int targetLevel, int targetBranch, boolean archive) {
		String message = "Your restriction level does not allow you to perform such action";
		byte actionUserLevel;
		byte targetUserLevel;
		Users user = new Users();
		user.setUsername(targetUser);
		UserService us = new UserService();
		actionUserLevel = (byte) (us.getLevelByUsername(actionUser) & 0xFF);
		targetUserLevel = (byte) (targetLevel & 0xFF);
		log.info("actionUser " + actionUser + " targetUser " + targetUser + " newPassword " + newPassword
				+ " verifyPassword " + verifyPassword + " message " + message);
		user.setLevel(targetUserLevel);
		user.setBranch(targetBranch);
		user.setArchive(false);
		if (actionUserLevel > targetUserLevel) {
			if (actionUserLevel == Level.ADMIN) {
				if (verifyPassword(targetUser, "", newPassword, verifyPassword, true)) {
					if (targetBranch==0) {
						BranchService bs = new BranchService();
						int branchId = bs.getNextBranchId();
						user.setBranch(branchId+1);
						user.setArchive(true);
						log.info("branchId " + branchId);
						message = saveUser(user, newPassword);
						Branch branch = new Branch();
						branch.setId(branchId);
						branch.setOwner(user.getId());
						branch.setArchive(true);
						branch.setAddress("Default Address of " + targetUser + "'s Default Pawnshop");
						branch.setAdvanceInterest(0.0d);
						branch.setBalance(0.0d);
						branch.setCounter(0L);
						branch.setExtend((byte) (15 & 0xff));
						branch.setName("Default Pawnshop of " + targetUser);
						branch.setPtNumber(0L);
						branch.setReserve((byte) (15 & 0xff));
						branch.setServiceCharge(0.0d);
						saveBranch(branch);
					}else {
						message = saveUser(user, newPassword);
					}
				} else {
					message = "New password does not match";
				}
			} else if (actionUserLevel == Level.MANAGER) {
				if (us.getBranchIdByUsername(actionUser) == targetBranch) {
					if (verifyPassword(targetUser, "", newPassword, verifyPassword, true)) {
						message = saveUser(user, newPassword);
					} else {
						message = "New password does not match";
					}
				}
			} else if (actionUserLevel == Level.OWNER) {
				int actionUserId = us.getIdByUsername(actionUser);
				ListIterator<Branch> li = HibernatePrendaDaoFactory.getBranchDao()
						.findByCriteria(Restrictions.eq("owner", actionUserId)).listIterator();
				Branch branch = new Branch();
				while (li.hasNext()) {
					branch = (Branch) li.next();
					if (branch.getId() == targetBranch) {
						if (verifyPassword(targetUser, "", newPassword, verifyPassword, true)) {
							message = saveUser(user, newPassword);
						} else {
							message = "New password does not match";
						}
						break;
					}
				}
			}
		}
		return message;
	}
	
	@Transactional
	protected Register saveRegister(Register register) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.save(register);
		dataLayerPrenda.flushAndClearSession();
		return register;
	}
	
	@Transactional
	protected Branch saveBranch(Branch branch) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.save(branch);
		dataLayerPrenda.flushAndClearSession();
		return branch;
	}
	
	@Transactional
	protected Users saveUser(Users user) {
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		dataLayerPrenda.save(user);
		dataLayerPrenda.flushAndClearSession();
		return user;
	}

	@Transactional
	protected String saveUser(Users user, String newPassword) {
		String message = "User added successfully";
		String hashedNewPassword = PasswordEncoderGenerator.getHash(newPassword);
		user.setPassword(hashedNewPassword);
		saveUser(user);
		return message;
	}

	@Transactional
	protected String changePassword(String actionUser, String targetUser, String oldPassword, String newPassword,
			String verifyPassword) {
		String message = "Your restriction level does not allow you to perform such action";
		int actionUserLevel;
		int targetUserLevel;
		UserService us = new UserService();
		int targetUserBranchId = us.getBranchIdByUsername(targetUser);
		if (targetUserBranchId < 1) {
			return "Invalid user";
		}
		if (actionUser.equals(targetUser)) {
			if (verifyPassword(targetUser, oldPassword, newPassword, verifyPassword, false)) {
				message = savePassword(user, newPassword);
			} else {
				message = "Either the old password is not correct or the new password does not match";
			}
			log.info("actionUser " + actionUser + " targetUser " + targetUser + " oldPassword " + oldPassword
					+ " newPassword " + newPassword + " verifyPassword " + verifyPassword + " message " + message);
		} else {
			actionUserLevel = us.getLevelByUsername(actionUser);
			targetUserLevel = us.getLevelByUsername(targetUser);
			if (actionUserLevel > targetUserLevel) {
				if (actionUserLevel == Level.ADMIN) {
					if (verifyPassword(targetUser, oldPassword, newPassword, verifyPassword, true)) {
						message = savePassword(user, newPassword);
					} else {
						message = "New password does not match";
					}
				} else if (actionUserLevel == Level.MANAGER) {
					if (us.getBranchIdByUsername(actionUser) == targetUserBranchId) {
						if (verifyPassword(targetUser, oldPassword, newPassword, verifyPassword, true)) {
							message = savePassword(user, newPassword);
						} else {
							message = "New password does not match";
						}
					}
				} else if (actionUserLevel == Level.OWNER) {
					int actionUserId = us.getIdByUsername(actionUser);
					ListIterator<Branch> li = HibernatePrendaDaoFactory.getBranchDao()
							.findByCriteria(Restrictions.eq("owner", actionUserId)).listIterator();
					Branch branch = new Branch();
					
					while (li.hasNext()) {
						branch = (Branch) li.next();
						if (branch.getId() == targetUserBranchId) {
							if (verifyPassword(targetUser, oldPassword, newPassword, verifyPassword, true)) {
								message = savePassword(user, newPassword);
							} else {
								message = "New password does not match";
							}
							break;
						}
					}
					
				}
			}
		}
		return message;
	}

	@Transactional
	protected boolean verifyPassword(String targetUser, String oldPassword, String newPassword, String verifyPassword,
			boolean skipTestOldPassword) {
		boolean passwordTest = false;
		ListIterator<Users> li = HibernatePrendaDaoFactory.getUsersDao()
				.findByCriteria(Restrictions.eq("username", targetUser)).listIterator();
		Users user;
		if (newPassword.equals(verifyPassword)) {
			if (skipTestOldPassword) {
				passwordTest = true;
				if (li.hasNext()) {
					user = (Users) li.next();
					this.user = user;
				}
			} else {
				if (li.hasNext()) {
					user = (Users) li.next();
					String passwordHash = user.getPassword();
					if (PasswordEncoderGenerator.matches(oldPassword, passwordHash)) {
						passwordTest = true;
						this.user = user;
					}
				}
			}
		}
		return passwordTest;
	}

	@Transactional
	protected String savePassword(Users user, String newPassword) {
		String message = "Password changed successfully";
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		String hashedNewPassword = PasswordEncoderGenerator.getHash(newPassword);
		user.setPassword(hashedNewPassword);
		dataLayerPrenda.update(user);
		dataLayerPrenda.flushAndClearSession();
		return message;
	}
}