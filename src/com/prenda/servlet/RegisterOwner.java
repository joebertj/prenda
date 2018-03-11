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
public class RegisterOwner extends UserModify{
	
	@RequestMapping(value = "RegisterOwner.htm", method = RequestMethod.POST)
	@Transactional
	private String register(HttpSession session, ModelMap map,
			@RequestParam("referer") String redirectUrl,
			@RequestParam("user") String targetUser,
			@RequestParam("pass1") String newPassword,
			@RequestParam("pass2") String verifyPassword) {
		String message = "Please check your email and follow instructions to complete registration";
		message=createNewOwner(targetUser, newPassword, verifyPassword, Level.OWNER);
		map.addAttribute("msg",message);
		return redirectUrl;
	}
}
