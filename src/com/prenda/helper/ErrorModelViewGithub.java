package com.prenda.helper;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.prenda.Mode;

public class ErrorModelViewGithub {
	private static Logger log = Logger.getLogger(ErrorModelViewGithub.class);
	
	private static final String DEFAULT_ERROR_VIEW = "public/error";
	
	public static ModelAndView logAsIssue(HttpServletRequest request, Exception e) {
		String url = request.getAttribute("javax.servlet.error.request_uri").toString(); //javax.servlet.forward.request_uri
		String errorCode = request.getAttribute("javax.servlet.error.status_code").toString();
		e.printStackTrace();
		String exception = errorCode + " " + e.getMessage();
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
		String title = url + " " + exception;
		log.info("title: " + title);
		issue.create(title, stackTrace, "joebertj", "prenda", labels, assignees, Mode.JWT, "");
		return mav;
	}
}
