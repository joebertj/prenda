/**
 * @author Joebert S. Jacaba
 * Copyright (C) 2008
 */

package com.prenda.servlet;


import javax.servlet.http.HttpSession;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.tools.crypt.Md5;

import com.prenda.factories.HibernatePrendaDaoFactory;
import com.prenda.model.obj.Users;

/**
 * Servlet implementation class for Servlet: Login
 *
 */
@Controller
@RequestMapping("TestLogin.htm")
public class TestLogin {
   
	private static Logger log = Logger.getLogger(TestLogin.class);
	
	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	protected String doPost(HttpSession session, ModelMap map, @RequestParam("user") String username, @RequestParam("pass") String pass)  {
		String badLogin="No such user or bad password!";
		String redirectURL = "index";
		ListIterator <Users> li = HibernatePrendaDaoFactory.getUsersDao().findByCriteria(Restrictions.eq("username", username)).listIterator();
		int size = 0;
		Users user = new Users();
		while(li.hasNext() && size<=1){
			user = li.next();
			size++;
		}
		log.debug("size " + size);
		if (size==1){
			Md5 md5=new Md5(pass);
		    md5.processString();
		    String password = md5.getStringDigest();
		    String password2 = user.getPassword();
		    log.debug(password);
		    log.debug(password2);
		    if(password.equals(password2)){
		    	DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
		        if(defaultSavedRequest != null) {
		        	redirectURL = defaultSavedRequest.getRequestURL();
		        }
		    	redirectURL = "redirect:" + redirectURL + ".jsp";
		    }else{
				map.addAttribute("msg",badLogin);
				redirectURL = "common/login";        			
			}
		}else{
			map.addAttribute("msg",badLogin);
			redirectURL = "common/login";
		}
		log.debug(redirectURL);
		return redirectURL;
	}   	  	    
}