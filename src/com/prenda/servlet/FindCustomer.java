package com.prenda.servlet;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prenda.service.CustomerService;

@Controller
public class FindCustomer {
	
	@RequestMapping(value = "FindCustomer.htm", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_OWNER", "ROLE_MANAGER", "ROLE_LIAISON", "ROLE_ENCODER" })
	@Transactional
	private String options(HttpSession session, ModelMap map,
			@RequestParam("referer") String redirectUrl,
			@RequestParam("lastName") String lastName,
			@RequestParam("firstName") String firstName,
			@RequestParam("middleName") String middleName) {
		CustomerService cs = new CustomerService();
		map.addAttribute("customers",cs.getCustomersbyNames(lastName, firstName, middleName));
		return redirectUrl;
	}
}
