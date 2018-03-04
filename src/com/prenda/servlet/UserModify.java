/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.tools.crypt.Md5;

import com.prenda.Level;
import com.prenda.factories.HibernatePrendaDaoFactory;
import com.prenda.helper.DatabaseConnection;
import com.prenda.model.obj.Users;
import com.prenda.service.LevelService;
import com.prenda.services.data.DataLayerPrenda;
import com.prenda.services.data.DataLayerPrendaImpl;

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
	public String changePassword(HttpSession session, ModelMap map, @RequestParam("referer") String redirectUrl, @RequestParam("pass") String oldPassword, 
			@RequestParam("pass1") String newPassword, @RequestParam("pass2") String verifyPassword){
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		ListIterator <Users> li = HibernatePrendaDaoFactory.getUsersDao().findByCriteria(Restrictions.eq("username", username)).listIterator();
		Users user = new Users();
		int size = 0;
		while(li.hasNext()){
			user = li.next();
			size++;
		}
		String message = null;
		if(size == 0){
			message = "No username " + username + " found";
		}else if(size==1){
			if(newPassword.equals(verifyPassword)){
				String ep = user.getPassword();
				Md5 md5=new Md5(oldPassword);
				md5.processString();
				String eo = md5.getStringDigest();
				log.info("ep: " + ep + " eo: " + eo);
				if(eo.equals(ep)){
					DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
					md5 = new Md5(newPassword);
					md5.processString();
					String en = md5.getStringDigest();
					user.setPassword(en);
					dataLayerPrenda.update(user);
					dataLayerPrenda.flushAndClearSession();
					message = "Password for user " + username + " successfully changed";
				}else{
					message = "Wrong password";
				}
			}else{
				message = "New password does not match";
			}
		}else {
			message = "Duplicate username " + username;
		}
		if (message != null) {
			map.addAttribute("msg", message);
		}
		log.info("message: " + message + " redirectUrl: " + redirectUrl);
		return redirectUrl;
	}
	
	protected String doPost(HttpSession session, ModelMap map,
			@RequestParam("user") String username,
			@RequestParam("pass") String pass,
			@RequestParam("modtype") int modtype,
			@RequestParam("pass2") String pass2,
			@RequestParam("level") int lvl, @RequestParam("branch") int branch,
			@RequestParam("pass3") String pass3, @RequestParam("uid") int uid,
			@RequestParam("delresp") String delresp) {
		String redirectURL = "index";
		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) session
				.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
		if (defaultSavedRequest != null) {
			redirectURL = defaultSavedRequest.getRequestURL();
		}

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String authenticated = auth.getName();
		Iterator<? extends GrantedAuthority> li = auth.getAuthorities()
				.iterator();
		String role = "ROLE_";
		if (li.hasNext()) {
			GrantedAuthority ga = li.next();
			role = ga.getAuthority();
		}
		LevelService ls = new LevelService();
		int level = ls.getId(role.replace("ROLE_", ""));
		log.info("role: " + role + " level: " + level);
		String message = null;
		if (level == Level.ADMIN) {
			continuePost(map, redirectURL, username, pass, pass2, pass3, modtype, lvl,
					branch, uid, delresp);
		} else if (level == Level.OWNER) {
			if (modtype != 1) {
				try {
					if (lvl > Level.MANAGER) {
						message = "You can not assign access level rights greater than you own";
						redirectURL = "common/login";
					} else {
						Connection conn = DatabaseConnection.getConnection();
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						pstmt = conn
								.prepareStatement("SELECT branchid FROM branch LEFT JOIN users ON branch.owner=users.uid WHERE users.username=? AND branchid=?");
						pstmt.setString(1, authenticated);
						pstmt.setInt(2, branch);
						rs = pstmt.executeQuery();
						if (rs.first()) {
							continuePost(map, redirectURL, username, pass, pass2, pass3,
									modtype, lvl, branch, uid, delresp);
						} else {
							message = "You do not own the selected branch";
							redirectURL = "common/login";
						}
					}
				} catch (SQLException ex) {
					log.info("SQLException: " + ex.getMessage());
					log.info("SQLState: " + ex.getSQLState());
					log.info("VendorError: " + ex.getErrorCode());
				}
			} else {
				continuePost(map, redirectURL, username, pass, pass2, pass3, modtype,
						lvl, branch, uid, delresp);
			}
		} else if (authenticated.equals(username)) {
			continuePost(map, redirectURL, username, pass, pass2, pass3, modtype, lvl,
					branch, uid, delresp);
		} else if (level == Level.MANAGER) {
			if (modtype > 0) {
				try {
					Connection conn = DatabaseConnection.getConnection();
					PreparedStatement pstmt = null;
					pstmt = conn
							.prepareStatement("SELECT branch FROM users WHERE username=?");
					pstmt.setString(1, username);
					ResultSet rs = pstmt.executeQuery();
					if (rs.first()) {
						int branch2 = rs.getInt(1);
						if (branch == branch2) {
							continuePost(map, redirectURL, username, pass, pass2, pass3,
									modtype, lvl, branch, uid, delresp);
						} else {
							message = "You are not the manager of branch where user "
									+ username + " belongs";
							redirectURL = "manager/changepass";
						}
					} else {
						message = "You are not the manager of branch where user "
								+ username + " belongs";
						redirectURL = "manager/changepass";
					}
				} catch (SQLException ex) {
					log.info("SQLException: " + ex.getMessage());
					log.info("SQLState: " + ex.getSQLState());
					log.info("VendorError: " + ex.getErrorCode());
				}
			} else {
				continuePost(map, redirectURL, username, pass, pass2, pass3, modtype,
						lvl, branch, uid, delresp);
			}
		} else if (level < Level.MANAGER) {
			message = "You are not a manager, owner or an administrator";
			redirectURL = "common/login";
		} else {
			message = "You don't have access rights";
			redirectURL = "common/login=";
		}
		if (message != null) {
			map.addAttribute("msg", message);
		}
		return redirectURL;
	}

	protected String continuePost(ModelMap map, String redirectURL, String username, String pass,
			String pass2, String pass3, int modtype, int lvl, int branch,
			int uid, String delresp) {
		try {
			log.info("redirectURL: " + redirectURL);
			Connection conn = DatabaseConnection.getConnection();
			PreparedStatement pstmt = null;
			String message = null;
			if (modtype == 0) {
				if (pass.equals(pass2)) {
					pstmt = conn
							.prepareStatement("SELECT username FROM users WHERE username=?");
					pstmt.setString(1, username);
					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {
						if (redirectURL.contains("manager")) {
							message = "User " + username + " already exists";
							redirectURL = "manager/newuser";
						} else if (redirectURL.contains("owner")) {
							message = "User " + username + " already exists";
							redirectURL = "owner/newuser";
						} else {
							message = "User " + username + " already exists";
							redirectURL = "admin/newuser";
						}
					} else {
						pstmt = conn
								.prepareStatement("INSERT INTO users VALUES (0,?,?,'','','',?,?,false,CURDATE())");
						pstmt.setString(1, username);
						Md5 md5 = new Md5(pass);
						md5.processString();
						String password = md5.getStringDigest();
						pstmt.setString(2, password);
						pstmt.setInt(3, lvl);
						pstmt.setInt(4, branch);
						pstmt.executeUpdate();
						if (lvl == Level.OWNER) {
							pstmt = conn
									.prepareStatement("SELECT uid FROM users WHERE username=?");
							pstmt.setString(1, username);
							rs = pstmt.executeQuery();
							if (rs.first()) {
								int id = rs.getInt(1);
								pstmt = conn
										.prepareStatement("UPDATE branch SET owner=? WHERE branchid=?");
								pstmt.setInt(1, id);
								pstmt.setInt(2, branch);
								pstmt.executeUpdate();
							}
						}
						message = "User " + username + " successfully added";
						if (redirectURL.contains("manager")) {
							redirectURL = "manager/newuser";
						} else if (redirectURL.contains("owner")) {
							redirectURL = "owner/newuser";
						} else {
							redirectURL = "admin/newuser";
						}
					}
				} else {
					message = "Password does not match";
					if (redirectURL.contains("manager")) {
						redirectURL = "manager/newuser";
					} else if (redirectURL.contains("owner")) {
						redirectURL = "owner/newuser";
					} else {
						redirectURL = "admin/newuser";
					}
				}
			} else if (modtype == 1) {
				if (delresp.equals("Confirm")) {
					pstmt = conn
							.prepareStatement("UPDATE users SET archive=true WHERE uid = ?");
					pstmt.setInt(1, uid);
					pstmt.executeUpdate();
					message = "User " + username + " archived";
					if (redirectURL.contains("manager")) {
						redirectURL = "manager/userlist";
					} else if (redirectURL.contains("owner")) {
						redirectURL = "owner/userlist";
					} else {
						redirectURL = "admin/userlist";
					}
				} else {
					message = "Delete of user " + username + " cancelled";
					if (redirectURL.contains("manager")) {
						redirectURL = "manager/userlist";
					} else if (redirectURL.contains("owner")) {
						redirectURL = "owner/userlist";
					} else {
						redirectURL = "admin/userlist";
					}
				}
			} else if (modtype == 2) {
				if (redirectURL.contains("admin") || redirectURL.contains("owner")) {
					if (pass.equals(pass2)) {
						if (pass2.equals(pass3)) {
							pstmt = conn
									.prepareStatement("UPDATE users SET username = ?, level = ?, branch = ? WHERE uid = ?");
							pstmt.setInt(4, uid);
							pstmt.setString(1, username);
							pstmt.setInt(2, lvl);
							pstmt.setInt(3, branch);
						} else {
							pstmt = conn
									.prepareStatement("UPDATE users SET username = ?, password = ?, level = ?, branch = ? WHERE uid = ?");
							pstmt.setInt(5, uid);
							pstmt.setString(1, username);
							Md5 md5 = new Md5(pass);
							md5.processString();
							String password = md5.getStringDigest();
							pstmt.setString(2, password);
							pstmt.setInt(3, lvl);
							pstmt.setInt(4, branch);
						}
						pstmt.executeUpdate();
						if (lvl == 8) {
							pstmt = conn
									.prepareStatement("UPDATE branch SET owner=? WHERE branchid=?");
							pstmt.setInt(1, uid);
							pstmt.setInt(2, branch);
							pstmt.executeUpdate();
						}
						message = "Details for user " + username
								+ " successfully changed";
						if (redirectURL.contains("owner")) {
							redirectURL = "owner/userlist";
						} else {
							redirectURL = "admin/userlist";
						}
					} else {
						message = "Password does not match";
						if (redirectURL.contains("owner")) {
							redirectURL = "owner/userlist";
						} else {
							redirectURL = "admin/userlist";
						}
					}
				} else if (redirectURL.contains("manager")) {
					if (pass.equals(pass2)) {
						pstmt = conn
								.prepareStatement("UPDATE users SET password = ? WHERE uid = ?");
						pstmt.setInt(2, uid);
						Md5 md5 = new Md5(pass);
						md5.processString();
						String password = md5.getStringDigest();
						pstmt.setString(1, password);
						pstmt.executeUpdate();
						message = "Password for user " + username
								+ " successfully changed";
						redirectURL = "manager/changepass";
					} else {
						message = "Password does not match";
						if (redirectURL.contains("manager")) {
							redirectURL = "manager/changepass";
						} else {
							redirectURL = "changepass";
						}
					}
				} else {
					if (pass.equals(pass2)) {
						pstmt = conn
								.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
						pstmt.setString(2, username);
						Md5 md5 = new Md5(pass);
						md5.processString();
						String password = md5.getStringDigest();
						pstmt.setString(1, password);
						pstmt.executeUpdate();
						message = "Password for user " + username
								+ " successfully changed";
						redirectURL = "changepass";
					} else {
						message = "Password does not match";
						redirectURL = "changepass";
					}
				}
			}
			if (message != null) {
				map.addAttribute("msg", message);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			log.debug("SQLException: " + ex.getMessage());
			log.debug("SQLState: " + ex.getSQLState());
			log.debug("VendorError: " + ex.getErrorCode());
		}
		
		return redirectURL;
	}
}