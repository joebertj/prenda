package com.prenda.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorHandler {

	@RequestMapping(value = "common/Error.htm", method = RequestMethod.GET)
	public void error(Exception e) throws Exception{
		throw new Exception(e);
	}
	
}
