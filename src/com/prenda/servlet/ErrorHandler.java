package com.prenda.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorHandler {

	@RequestMapping(value = "public/Error.htm", method = RequestMethod.GET)
	public void error(Exception e) throws Exception{
		throw new Exception(e);
	}
	
	@RequestMapping(value = "public/Error.htm", method = RequestMethod.POST)
	public void errorPost(Exception e) throws Exception{
		error(e);
	}
	
}
