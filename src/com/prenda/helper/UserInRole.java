package com.prenda.helper;

import javax.servlet.http.HttpServletRequest;

public class UserInRole {
	public static Integer getLevel(HttpServletRequest request) {
		Integer level = 0;
		if(request.isUserInRole("ROLE_ADMIN")) {
			level = 9;
		}else if(request.isUserInRole("ROLE_OWNER")) {
			level = 8;
		}else if(request.isUserInRole("ROLE_MANAGER")) {
			level = 7;
		}else if(request.isUserInRole("ROLE_LIAISON")) {
			level = 3;
		}else if(request.isUserInRole("ROLE_ENCODER")) {
			level = 1;
		}
		return level;
	}
}
