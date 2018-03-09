package com.prenda.servlet;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prenda.service.JewelryService;

@Controller
public class BranchSettings {
	
	@RequestMapping(value = "BranchSettings.htm", method = RequestMethod.POST)
	@Secured({ "ROLE_ADMIN", "ROLE_OWNER" })
	@Transactional
	private String jewelryService(HttpSession session, ModelMap map,
			@RequestParam("branchid") int branchId) {
		map = add(map,branchId);
		return "owner/settings";
	}
	
	@RequestMapping(value = "BranchSettings.htm", method = RequestMethod.GET)
	@Secured({ "ROLE_MANAGER" })
	@Transactional
	private String jewelryServiceManager(HttpSession session, ModelMap map,
			@RequestParam("branchid") int branchId) {
		map = add(map,branchId);
		return "manage/settings";
	}
	
	private ModelMap add(ModelMap map, int branchId) {
		JewelryService js = new JewelryService();
		map.addAttribute("jewelry",js.getJewelryById(branchId));
		return map;
	}
}
