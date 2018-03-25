package com.prenda.servlet;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

@Controller
public class LanguageSelector {
	
	private static Logger log = Logger.getLogger(LanguageSelector.class);
	
	@Autowired
	LocaleResolver localeResolver; 
	
	@RequestMapping(value = "common/Language.htm", method = RequestMethod.GET)
	private String set(HttpServletRequest request, HttpServletResponse response, ModelMap map, Locale locale,
			@RequestParam("referer") String redirectUrl,
			@RequestParam("lang") String lang) {
		localeResolver.setLocale(request, response, locale);
		int idx = redirectUrl.indexOf("/prenda/");
		log.info("idx: " + idx);
		if(idx==0) { //prenda context
			redirectUrl = redirectUrl.substring(8);
		}else if(idx==-1) { //ROOT context
			;
		}
		log.info("locale: " + locale);
		map.addAttribute("lang", lang);
		return "redirect:/" + redirectUrl;
	}
}
