package com.prenda.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.prenda.Mode;
import com.prenda.helper.GithubIssue;

@Controller
public class ErrorHandler { // handles JSP errors
	
	public static final String DEFAULT_ERROR_VIEW = "public/error";
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "public/Error.htm", method = RequestMethod.GET)
	public ModelAndView error(ModelMap map, Exception e) throws Exception{
		String url = request.getRequestURI().toString();
		e.printStackTrace();
		String exception = e.getMessage();
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
		issue.create(url + " " + exception, stackTrace, "joebertj", "prenda", labels, assignees, Mode.JWT, "");
		return mav;
	}
	
}
