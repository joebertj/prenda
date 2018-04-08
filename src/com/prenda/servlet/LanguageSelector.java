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

	@RequestMapping(value = "public/Language.htm", method = RequestMethod.GET)
	private String set(HttpServletRequest request, HttpServletResponse response, ModelMap map, Locale locale,
			@RequestParam("referer") String redirectUrl, @RequestParam("lang") String lang) {
		localeResolver.setLocale(request, response, locale);
		int idx = redirectUrl.indexOf("/prenda/");
		int idxC = redirectUrl.indexOf("/prenda/public/");
		/*
		 * int idxA = redirectUrl.indexOf("/prenda/admin/"); int idxU =
		 * redirectUrl.indexOf("/prenda/customer/"); int idxO =
		 * redirectUrl.indexOf("/prenda/owner/"); int idxM =
		 * redirectUrl.indexOf("/prenda/manage/"); int idxR =
		 * redirectUrl.indexOf("/prenda/register/");
		 */
		log.info("idx: " + idx);
		if (idx == 0) { // prenda context
			if (idxC == 0) {
				redirectUrl = redirectUrl.substring(15); // relative to common
				/*
				 * }else if(idxA==0){ redirectUrl = redirectUrl.substring(14); }else
				 * if(idxU==0){ redirectUrl = redirectUrl.substring(17); }else if(idxO==0){
				 * redirectUrl = redirectUrl.substring(14); }else if(idxM==0){ redirectUrl =
				 * redirectUrl.substring(15); }else if(idxR==0){ redirectUrl =
				 * redirectUrl.substring(17);
				 */
			} else {
				redirectUrl = redirectUrl.substring(7);
			}
		} else if (idx == -1) { // ROOT context
			;
		}
		log.info("redirectUrl: " + redirectUrl);
		log.info("locale: " + locale);
		map.addAttribute("lang", lang);
		return "redirect:" + redirectUrl;
	}
}
