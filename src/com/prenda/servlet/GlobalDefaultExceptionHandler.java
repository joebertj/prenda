package com.prenda.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.prenda.Mode;
import com.prenda.helper.GithubIssue;

@ControllerAdvice
class GlobalDefaultExceptionHandler { // handles JAVA errors

	public static final String DEFAULT_ERROR_VIEW = "public/error";

	@ExceptionHandler(value = Exception.class)
	public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
		e.printStackTrace();
		String exception = e.getMessage();
		String url = request.getRequestURI();
		String stackTrace ="";
		for(StackTraceElement element: e.getStackTrace()) {
			stackTrace += element.getClassName()+" "+element.getMethodName()+" "+element.getLineNumber() +"<br/>";
		}
		ModelAndView mav = new ModelAndView(DEFAULT_ERROR_VIEW);
		mav.addObject("exception", exception);
		mav.addObject("url", url);
		String [] labels = {"bug"};
		String [] assignees = {"joebertj"};
		GithubIssue issue = new GithubIssue();
		issue.create(url + " " + exception, stackTrace, "joebertj", "prenda", labels, assignees, Mode.JWT);
		return mav;
	}

}