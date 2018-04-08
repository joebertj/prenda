/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;

import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.passay.RuleResult;
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
import com.prenda.helper.CustomPasswordGenerator;
import com.prenda.helper.SimpleJavaMailUtil;
import com.prenda.model.obj.prenda.Register;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.BranchService;
import com.prenda.service.RegisterService;
import com.prenda.service.UserService;
import com.prenda.validation.CustomPasswordValidator;

/**
 * Servlet implementation class for Servlet: UserModify
 * 
 */
@Controller
public class UserModify {

	private static Logger log = Logger.getLogger(UserModify.class);

	@RequestMapping(value = "UserModify.htm", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_OWNER", "ROLE_MANAGER", "ROLE_LIAISON", "ROLE_ENCODER" })
	@Transactional
	private String modifyUserActionSelector(HttpSession session, ModelMap map,
			@RequestParam("referer") String redirectUrl, @RequestParam("modtype") int mode,
			@RequestParam("user") String targetUser, @RequestParam(value = "pass", required = false) String oldPassword,
			@RequestParam(value = "pass1", required = false) String newPassword,
			@RequestParam(value = "pass2", required = false) String verifyPassword,
			@RequestParam(value = "level", required = false) Integer targetLevel,
			@RequestParam(value = "branch", required = false) Integer targetBranch,
			@RequestParam(value = "uid", required = false) Integer userId,
			@RequestParam(value = "lname", required = false) String lastName,
			@RequestParam(value = "fname", required = false) String firstName,
			@RequestParam(value = "mname", required = false) String middleName,
			@RequestParam(value = "delresp", required = false) String response) {

		String message = "Your restriction level does not allow you to perform such action";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String actionUser = auth.getName();
		log.info("actionUser " + actionUser + " targetUser " + targetUser + " targetLevel " + targetLevel
				+ " targetBranch " + targetBranch);
		if (mode == Mode.CREATENEW) {
			message = createNewUser(actionUser, targetUser, newPassword, verifyPassword, targetLevel, targetBranch,
					false);
		} else if (mode == Mode.DELETE) {
			if (response.equals("Confirm")) {
				message = deleteUser(actionUser, targetUser);
			} else if (response.equals("Cancel")) {
				message = "Delete cancelled";
			}
		} else if (mode == Mode.UPDATE) {
			if(actionUser.equals(targetUser)) { //For self password change remove parameters that indicates changing password in behalf of subordinates
				redirectUrl ="redirect:"+redirectUrl+".jsp";
			}
			message = updateUser(userId, actionUser, targetUser, lastName, firstName, middleName, oldPassword,
					newPassword, verifyPassword, targetLevel, targetBranch);
		}
		map.addAttribute("msg", message);
		log.info("message: " + message + " redirectUrl: " + redirectUrl);
		return redirectUrl;
	}

	@Transactional
	protected String deleteUser(String actionUser, String targetUser) {
		String message = "Your restriction level does not allow you to perform such action";
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		Users actUser = us.getUser(actionUser);
		int targetBranch = user.getBranch();
		int targetLevel = user.getLevel();
		byte actionUserLevel = (byte) (actUser.getLevel() & 0xFF);
		byte targetUserLevel = (byte) (targetLevel & 0xFF);
		if (actionUserLevel > targetUserLevel) {
			if (actionUserLevel == Level.ADMIN) {
				message = deleteUser(user);
			} else if (actionUserLevel == Level.MANAGER) {
				if (actUser.getBranch() == targetBranch) {
					message = deleteUser(user);
				}
			} else if (actionUserLevel == Level.OWNER) {
				log.info("actionUserBranch " + actUser.getBranch() + " targetBranch " + targetBranch);
				int actionUserId = actUser.getId();
				BranchService bs = new BranchService();
				log.info("branchOwnerId " + bs.getOwnerId(targetBranch) + " actionUserId " + actionUserId);
				if (bs.getOwnerId(targetBranch) == actionUserId) {
					message = deleteUser(user);
				}
			}
		}
		return message;
	}

	@Transactional
	private String deleteUser(Users user) {
		user.setArchive(true);
		UserService us = new UserService();
		us.updateUser(user);
		String message = "User " + user.getUsername() + " deleted";
		return message;
	}

	@Transactional
	private String updateUser(Integer userId, String actionUser, String targetUser, String lastName, String firstName,
			String middleName, String oldPassword, String newPassword, String verifyPassword, Integer targetLevel,
			Integer targetBranch) {
		String message = "Your restriction level does not allow you to perform such action";
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		// if just changing password or updating name
		if (targetLevel == null) {
			targetLevel = user.getLevel().intValue();
		}
		if (targetBranch == null) {
			targetBranch = user.getBranch();
		}
		Users actUser = us.getUser(actionUser);
		byte actionUserLevel = (byte) (actUser.getLevel() & 0xFF);
		byte targetUserLevel = (byte) (targetLevel & 0xFF);
		if (actionUserLevel > targetUserLevel) {
			if (actionUserLevel == Level.ADMIN) {
				message = updateName(targetUser, lastName, firstName, middleName, userId, targetLevel, targetBranch,
						true);
			} else if (actionUserLevel == Level.MANAGER) {
				if (actUser.getBranch() == targetBranch) {
					message = updateName(targetUser, lastName, firstName, middleName);
				}
			} else if (actionUserLevel == Level.OWNER) {
				log.info("actionUserBranch " + actUser.getBranch() + " targetBranch " + targetBranch);
				int actionUserId = actUser.getId();
				BranchService bs = new BranchService();
				log.info("branchOwnerId " + bs.getOwnerId(targetBranch) + " actionUserId " + actionUserId);
				if (bs.getOwnerId(targetBranch) == actionUserId) {
					message = updateName(targetUser, lastName, firstName, middleName, userId, targetLevel, targetBranch,
							false);
				}
			} else {
				message = updateName(targetUser, lastName, firstName, middleName);
			}
		}
		// if password field was filled also attempt to change password
		if (newPassword != null && !newPassword.trim().isEmpty()) {
			message = changePassword(actionUser, targetUser, oldPassword, newPassword, verifyPassword);
		}
		return message;
	}

	@Transactional
	private String updateName(String targetUser, String lastName, String firstName, String middleName) {
		return updateName(targetUser, lastName, firstName, middleName, null, null, null, false);
	}

	@Transactional
	private String updateName(String targetUser, String lastName, String firstName, String middleName, Integer userId,
			Integer targetLevel, Integer targetBranch, boolean changeUsernameAlso) {
		String message = "Details for user " + targetUser + " updated";
		UserService us = new UserService();
		Users userCheck = us.getUser(targetUser);
		Users user = us.getUser(userId);
		if (changeUsernameAlso) { // used by Admin to change username
			// If no same name on existing users
			if (userCheck == null) {
				user.setUsername(targetUser); // update username
			} else {
				if (!targetUser.equals(user.getUsername())) { // the username was changed but there was an existing user
					return "Duplicate username";
				}
			}
		}
		user.setLastname(lastName);
		user.setFirstname(firstName);
		user.setMiddlename(middleName);
		us.updateUser(user);
		return message;
	}

	@Transactional
	protected String createNewOwner(String targetUser, String newPassword, String verifyPassword, int targetLevel,
			String email, String lastName, String firstName, String middleName) {
		int targetBranch = 0;
		String message;
		message = createNewUser("admin", targetUser, newPassword, verifyPassword, targetLevel, targetBranch, true);
		log.info("createNewOwner message " + message);
		if (message.equals("User added successfully")) {
			UserService us = new UserService();
			Users user = us.getUser(targetUser);
			int id = user.getId();
			user.setLastname(lastName);
			user.setFirstname(firstName);
			user.setMiddlename(middleName);
			us.updateUser(user);
			String key = CustomPasswordGenerator.getPassword(128);
			Register register = new Register();
			register.setId(id);
			register.setPassword(key);
			RegisterService rs = new RegisterService();
			rs.saveRegister(register);
			SimpleJavaMailUtil.send(targetUser, email, key);
			message = "Please check your email and follow instructions to complete registration";
		}
		return message;
	}

	@Transactional
	protected String createNewUser(String actionUser, String targetUser, String newPassword, String verifyPassword,
			Integer targetLevel, Integer targetBranch, Boolean archive) {
		String message = "Your restriction level does not allow you to perform such action";
		byte actionUserLevel;
		byte targetUserLevel;
		UserService us = new UserService();
		Users user = us.getUser(actionUser);
		// if just changing password or updating name
		if (targetLevel == null) {
			targetLevel = user.getLevel().intValue();
		}
		if (targetBranch == null) {
			targetBranch = user.getBranch();
		}
		actionUserLevel = (byte) (user.getLevel() & 0xFF);
		targetUserLevel = (byte) (targetLevel & 0xFF);
		log.info("actionUser " + actionUser + " targetUser " + targetUser + " newPassword " + newPassword
				+ " verifyPassword " + verifyPassword + " message " + message);
		if (actionUserLevel > targetUserLevel) {
			if (actionUserLevel == Level.ADMIN || actionUserLevel == Level.MANAGER || actionUserLevel == Level.OWNER) {
				log.info("actionUserBranch " + user.getBranch() + " targetBranch " + targetBranch);
				if (verifyPassword(targetUser, "", newPassword, verifyPassword, true)) {
					CustomPasswordValidator cpv = new CustomPasswordValidator();
					RuleResult result = cpv.validate(newPassword);
					if (result.isValid()) {
						us.saveUser(targetUser, newPassword, targetUserLevel, targetBranch, false);
						message = "User added successfully";
					} else {
						message = "Invalid password:";
						for (String msg : cpv.getMessages(result)) {
							message += " " + msg;
						}
						message = message.replaceAll(", matching.*}", "}").replaceAll("validCharacters=.* ", "");
					}

				} else {
					message = "New password does not match";
				}
			}
		}
		return message;
	}

	@Transactional
	protected String changePassword(String actionUser, String targetUser, String oldPassword, String newPassword,
			String verifyPassword) {
		String message = "Your restriction level does not allow you to perform such action";
		int actionUserLevel;
		int targetUserLevel;
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		if (user == null) {
			return "Invalid user";
		}
		Users actUser = us.getUser(actionUser);
		int targetUserBranchId = user.getBranch();
		if (targetUserBranchId < 1) {
			return "Invalid user";
		}
		if (actionUser.equals(targetUser)) {
			if (verifyPassword(targetUser, oldPassword, newPassword, verifyPassword, false)) {
				CustomPasswordValidator cpv = new CustomPasswordValidator();
				RuleResult result = cpv.validate(newPassword);
				if (result.isValid()) {
					message = savePassword(user, newPassword);
				} else {
					message = "Invalid password:";
					for (String msg : cpv.getMessages(result)) {
						message += " " + msg;
					}
					message = message.replaceAll(", matching.*}", "}").replaceAll("validCharacters=.*? ", "");
				}
			} else {
				message = "Either the old password is not correct or the new password does not match";
			}
			log.info("actionUser " + actionUser + " targetUser " + targetUser + " oldPassword " + oldPassword
					+ " newPassword " + newPassword + " verifyPassword " + verifyPassword + " message " + message);
		} else {
			actionUserLevel = actUser.getLevel();
			targetUserLevel = user.getLevel();
			int targetBranch = user.getBranch();
			if (actionUserLevel > targetUserLevel) {
				if (actionUserLevel == Level.ADMIN) {
					if (verifyPassword(targetUser, oldPassword, newPassword, verifyPassword, true)) {
						CustomPasswordValidator cpv = new CustomPasswordValidator();
						RuleResult result = cpv.validate(newPassword);
						if (result.isValid()) {
							message = savePassword(user, newPassword);
						} else {
							message = "Invalid password:";
							for (String msg : cpv.getMessages(result)) {
								message += " " + msg;
							}
							message = message.replaceAll(", matching.*}", "}").replaceAll("validCharacters=.*? ", "");
						}
					} else {
						message = "New password does not match";
					}
				} else if (actionUserLevel == Level.MANAGER) {
					if (actUser.getBranch() == targetUserBranchId) {
						if (verifyPassword(targetUser, oldPassword, newPassword, verifyPassword, true)) {
							CustomPasswordValidator cpv = new CustomPasswordValidator();
							RuleResult result = cpv.validate(newPassword);
							if (result.isValid()) {
								message = savePassword(user, newPassword);
							} else {
								message = "Invalid password:";
								for (String msg : cpv.getMessages(result)) {
									message += " " + msg;
								}
								message = message.replaceAll(", matching.*}", "}").replaceAll("validCharacters=.*? ",
										"");
							}
							message = "New password does not match";
						}
					}
				} else if (actionUserLevel == Level.OWNER) {
					log.info("actionUserBranch " + actUser.getBranch() + " targetBranch " + targetBranch);
					int actionUserId = actUser.getId();
					BranchService bs = new BranchService();
					log.info("branchOwnerId " + bs.getOwnerId(targetBranch) + " actionUserId " + actionUserId);
					if (bs.getOwnerId(targetBranch) == actionUserId) {
						if (verifyPassword(targetUser, oldPassword, newPassword, verifyPassword, true)) {
							CustomPasswordValidator cpv = new CustomPasswordValidator();
							RuleResult result = cpv.validate(newPassword);
							if (result.isValid()) {
								message = savePassword(user, newPassword);
							} else {
								message = "Invalid password:";
								for (String msg : cpv.getMessages(result)) {
									message += " " + msg;
								}
								message = message.replaceAll(", matching.*}", "}").replaceAll("validCharacters=.*? ",
										"");
							}
						} else {
							message = "New password does not match";
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
			} else {
				if (li.hasNext()) {
					user = (Users) li.next();
					String passwordHash = user.getPassword();
					if (PasswordEncoderGenerator.matches(oldPassword, passwordHash)) {
						passwordTest = true;
					}
				}
			}
		}
		return passwordTest;
	}

	@Transactional
	protected String savePassword(Users user, String newPassword) {
		String message = "Password changed successfully";
		String hashedNewPassword = PasswordEncoderGenerator.getHash(newPassword);
		user.setPassword(hashedNewPassword);
		UserService us = new UserService();
		us.updateUser(user);
		return message;
	}
}