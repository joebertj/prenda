package com.prenda.servlet;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prenda.service.StatisticsService;

@Controller
public class RedeemStatistics {
	@RequestMapping(value = "RedeemStatistics.htm", method = RequestMethod.GET)
	@Secured({ "ROLE_ADMIN", "ROLE_OWNER", "ROLE_MANAGER" })
	@Transactional
	private String jewelryService(HttpSession session, ModelMap map,
			@RequestParam("branchid") int branchId,
			@RequestParam("userid") int userId) {
		StatisticsService ss = new StatisticsService();
		int [][] redeemstat = new int [20] [4];
		for(int i=0;i<20;i++) {
			redeemstat[i][0] = ss.getRedeemCountByInterestRate(i+1, branchId, userId, 99);
			redeemstat[i][1] = ss.getRedeemCountByInterestRate(i+1, branchId, userId, 3);
			redeemstat[i][2] = ss.getRedeemCountByInterestRate(i+1, branchId, userId, 2);
			redeemstat[i][3] = ss.getRedeemCountByInterestRate(i+1, branchId, userId, 1);
		}
		map.addAttribute("redeemstat",redeemstat);
		return "manage/redeemsummarystatrate";
	}
}
