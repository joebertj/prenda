package com.prenda.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prenda.helper.ErrorModelViewGithub;

@Controller
public class ErrorHandler { // handles JSP errors
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "public/Error.htm", method = RequestMethod.GET)
	public ModelAndView error(ModelMap map, Exception e) throws Exception{
		return ErrorModelViewGithub.logAsIssue(request, e);
	}
	
}
