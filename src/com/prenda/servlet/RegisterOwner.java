package com.prenda.servlet;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prenda.Level;
import com.prenda.model.obj.prenda.Users;
import com.prenda.service.RegisterService;
import com.prenda.service.UserService;
import com.prenda.services.data.DataLayerPrenda;
import com.prenda.services.data.DataLayerPrendaImpl;

@Controller
public class RegisterOwner {
	
	@RequestMapping(value = "register/RegisterOwner.htm", method = RequestMethod.POST)
	@Transactional
	private String register(HttpSession session, ModelMap map,
			@RequestParam("referer") String redirectUrl,
			@RequestParam("user") String targetUser,
			@RequestParam("email") String email,
			@RequestParam("pass1") String newPassword,
			@RequestParam("pass2") String verifyPassword,
			@RequestParam("lname") String lastName,
			@RequestParam("fname") String firstName,
			@RequestParam("mname") String middleName) {
		UserModify um = new UserModify();
		map.addAttribute("msg",um.createNewOwner(targetUser, newPassword, verifyPassword, Level.OWNER, email, lastName, firstName, middleName));
		return redirectUrl;
	}
	
	@RequestMapping(value = "register/ActivateOwner.htm", method = {RequestMethod.GET,RequestMethod.POST})
	@Transactional
	private String activatePost(HttpSession session, ModelMap map,
			@RequestParam("user") String targetUser,
			@RequestParam("key") String password) {
		String message = "Either key is invalid or user is not registered";
		UserService us = new UserService();
		Users user = us.getUser(targetUser);
		RegisterService rs = new RegisterService();
		String key = rs.getKey(user.getId());
		if(key.equals(password)) {
			if(user.isArchive()) {
				user.setArchive(false);
				DataLayerPrenda dataLayerPrenda = DataLayerPrendaImpl.getInstance();
				dataLayerPrenda.update(user);
				dataLayerPrenda.flushAndClearSession();
				message = "User activated";
			}else {
				message = "User already activated";
			}
		}
		map.addAttribute("msg",message);
		return "redirect:../common/login.jsp";
	}
}
