package com.prenda.servlet;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prenda.Level;

@Controller
public class RegisterOwner {
	
	UserModify um = new UserModify();
	
	@RequestMapping(value = "register/RegisterOwner.htm", method = RequestMethod.POST)
	@Transactional
	private String register(HttpSession session, ModelMap map,
			@RequestParam("referer") String redirectUrl,
			@RequestParam("user") String targetUser,
			@RequestParam("pass1") String newPassword,
			@RequestParam("pass2") String verifyPassword) {
		map.addAttribute("msg",um.createNewOwner(targetUser, newPassword, verifyPassword, Level.OWNER));
		return redirectUrl;
	}
	
	//@RequestMapping(value = "register/ActivateOwner.htm", method = RequestMethod.POST)
	@Transactional
	private String activate(HttpSession session, ModelMap map,
			@RequestParam("referer") String redirectUrl,
			@RequestParam("user") String targetUser,
			@RequestParam("pass") String newPassword) {
		String message = "Either key is invalid or user is not registered";
		
		map.addAttribute("msg",message);
		return redirectUrl;
	}
}
