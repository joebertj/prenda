package com.prenda.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.prenda.helper.ErrorModelViewGithub;

@ControllerAdvice
class GlobalDefaultExceptionHandler { // handles JAVA errors
	
	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
		return ErrorModelViewGithub.logAsIssue(request, e);
	}

}