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
import com.prenda.model.obj.prenda.Branch;
import com.prenda.model.obj.prenda.Users;
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
	
	Users user;
	
	@RequestMapping(value = "UserModify.htm", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_OWNER", "ROLE_MANAGER", "ROLE_LIAISON", "ROLE_ENCODER" })
	@Transactional
	public String modifyUserActionSelecor(HttpSession session, ModelMap map, 
			@RequestParam("referer") String redirectUrl, 
			@RequestParam("modtype") int mode, 
			@RequestParam("user") String targetUser, 
			@RequestParam("pass") String oldPassword, 
			@RequestParam("pass1") String newPassword, 
			@RequestParam("pass2") String verifyPassword, 
			@RequestParam("level") int targetLevel, 
			@RequestParam("branch") int targetBranch) {
		String message ="Action is not valid";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String actionUser = auth.getName();
		if(mode==Mode.CREATENEW) {
			message=createNewUser(actionUser, targetUser, newPassword, verifyPassword, targetLevel, targetBranch);
		} else if(mode==Mode.DELETE) {
			message="Under construction";
		} else if(mode==Mode.UPDATE) {
			message=changePassword(actionUser, targetUser, oldPassword, newPassword, verifyPassword);
		}
		map.addAttribute("msg", message);
		log.info("message: " + message + " redirectUrl: " + redirectUrl);
		return redirectUrl;
	}
	
	@Transactional
	private String createNewUser(String actionUser, String targetUser, String newPassword, String verifyPassword,
			int targetLevel, int targetBranch) {
		String message="Your restriction level does not allow you to perform such action";
		byte actionUserLevel;
		byte targetUserLevel;
		Users user = new Users();
		user.setUsername(targetUser);
		UserService us = new UserService();
		actionUserLevel = (byte) (us.getLevelByUsername(actionUser) & 0xFF);
		targetUserLevel = (byte) (targetLevel & 0xFF);
		log.info("actionUser " + actionUser + " targetUser " + targetUser + " newPassword " + newPassword + " verifyPassword " + verifyPassword + " message " + message);
		if(actionUserLevel==Level.ADMIN && targetUserLevel<Level.ADMIN){
			user.setLevel(targetUserLevel);
			user.setBranch(targetBranch);
			user.setArchive(false);
			if(verifyPassword(targetUser,"",newPassword,verifyPassword,true)) {
				message=saveUser(user,newPassword);
			}else {
				message = "New password does not match";
			}
		}else if(actionUserLevel==Level.MANAGER && targetUserLevel<Level.MANAGER && us.getBranchIdByUsername(actionUser)==us.getBranchIdByUsername(targetUser)) {
			if(verifyPassword(targetUser,"",newPassword,verifyPassword,true)) {
				message=saveUser(user,newPassword);
			}else {
				message = "New password does not match";
			}
		}else if(actionUserLevel==Level.OWNER) {
			int actionUserId = us.getIdByUsername(actionUser);
			ListIterator <Branch> li = HibernatePrendaDaoFactory.getBranchDao().findByCriteria(Restrictions.eq("owner", actionUserId)).listIterator();
			Branch branch = new Branch();
			int targetUserBranchId = us.getBranchIdByUsername(targetUser);
			while(li.hasNext()){
				branch = li.next();
				if(branch.getId()==targetUserBranchId) {
					if(verifyPassword(targetUser,"",newPassword,verifyPassword,true)) {
						message=saveUser(user,newPassword);
					}else {
						message = "New password does not match";
					}
					break;
				}
			}
		}
		return message;
	}

	@Transactional
	private String saveUser(Users user, String newPassword) {
		String message = "Password changed successfully";
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		String hashedNewPassword = PasswordEncoderGenerator.getHash(newPassword);
		user.setPassword(hashedNewPassword);
		dataLayerPrenda.save(user);
		dataLayerPrenda.flushAndClearSession();
		return message;
	}

	@Transactional
	public String changePassword(String actionUser, String targetUser, String oldPassword, String newPassword, String verifyPassword){
		String message = "Your restriction level does not allow you to perform such action";
		int actionUserLevel;
		int targetUserLevel;
		if(actionUser.equals(targetUser)) {
			if(verifyPassword(targetUser,oldPassword,newPassword,verifyPassword,false)) {
				message=savePassword(user, newPassword);
			}else {
				message = "New password does not match";
			}
			log.info("actionUser " + actionUser + " targetUser " + targetUser + " oldPassword " + oldPassword + " newPassword " + newPassword + " verifyPassword " + verifyPassword + " message " + message);
		}else {
			UserService us = new UserService();
			actionUserLevel = us.getLevelByUsername(actionUser);
			targetUserLevel = us.getLevelByUsername(targetUser);
			if(actionUserLevel==Level.ADMIN && targetUserLevel<Level.ADMIN) {
				if(verifyPassword(targetUser,oldPassword,newPassword,verifyPassword,true)) {
					message=savePassword(user, newPassword);
				}else {
					message = "New password does not match";
				}
			}else if(actionUserLevel==Level.MANAGER && targetUserLevel<Level.MANAGER && us.getBranchIdByUsername(actionUser)==us.getBranchIdByUsername(targetUser)) {
				if(verifyPassword(targetUser,oldPassword,newPassword,verifyPassword,true)) {
					message=savePassword(user, newPassword);
				}else {
					message = "New password does not match";
				}
			}else if(actionUserLevel==Level.OWNER) {
				int actionUserId = us.getIdByUsername(actionUser);
				ListIterator <Branch> li = HibernatePrendaDaoFactory.getBranchDao().findByCriteria(Restrictions.eq("owner", actionUserId)).listIterator();
				Branch branch = new Branch();
				int targetUserBranchId = us.getBranchIdByUsername(targetUser);
				while(li.hasNext()){
					branch = li.next();
					if(branch.getId()==targetUserBranchId) {
						if(verifyPassword(targetUser,oldPassword,newPassword,verifyPassword,true)) {
							message=savePassword(user, newPassword);
						}else {
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
	private boolean verifyPassword(String targetUser, String oldPassword, String newPassword, String verifyPassword, boolean skipTestOldPassword) {
		boolean passwordTest = false;
		ListIterator <Users> li = HibernatePrendaDaoFactory.getUsersDao().findByCriteria(Restrictions.eq("username", targetUser)).listIterator();
		Users user;
		if(newPassword.equals(verifyPassword)){
			if(skipTestOldPassword) {
				passwordTest=true;
				if(li.hasNext()) {
					user = (Users) li.next();
					this.user = user;
				}
			}else {
				if(li.hasNext()) {
					user = (Users) li.next();
					if(user.getPassword().equals(oldPassword)) {
						passwordTest = true; 
						this.user = user;
					}
				}
			}
		}
		return passwordTest;
	}

	@Transactional
	private String savePassword(Users user, String newPassword) {
		String message = "Password changed successfully";
		DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
		String hashedNewPassword = PasswordEncoderGenerator.getHash(newPassword);
		user.setPassword(hashedNewPassword);
		dataLayerPrenda.update(user);
		dataLayerPrenda.flushAndClearSession();
		return message;
	}
}