package com.prenda.servlet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

	private static Logger log = Logger.getLogger(GlobalDefaultExceptionHandler.class);

	public static final String DEFAULT_ERROR_VIEW = "common/error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
		mav.addObject("exception", e.getMessage());
	    mav.addObject("url", request.getRequestURL().toString());
		return mav;
	}
}