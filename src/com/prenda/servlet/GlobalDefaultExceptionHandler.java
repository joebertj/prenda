package com.prenda.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.prenda.helper.GithubIssue;

@ControllerAdvice
class GlobalDefaultExceptionHandler {

	public static final String DEFAULT_ERROR_VIEW = "common/error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
		String exception = e.getMessage();
		String url = request.getRequestURL().toString();
		String stackTrace ="";
		for(StackTraceElement element: e.getStackTrace()) {
			stackTrace += element.getClassName()+" "+element.getMethodName()+" "+element.getLineNumber() +"<br/>";
		}
		mav.addObject("exception", exception);
		mav.addObject("url", url);
		String [] labels = {"bug"};
		String [] assignees = {"joebertj"};
		GithubIssue issue = new GithubIssue();
		issue.create(url + " " + exception, stackTrace, "joebertj", "prenda", labels, assignees, "a3afb3526bbc4a2d5873024f056059d734f8005d");
		return mav;
	}

}