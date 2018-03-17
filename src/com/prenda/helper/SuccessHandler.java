package com.prenda.helper;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

public class SuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	private static Logger log = Logger.getLogger(SuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		// To make it easy to retrieve the authenticated username
		session.setAttribute("authenticated", username);
		// This just redirects the page to the original request
		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
        if( defaultSavedRequest != null) {
            String requestUrl = defaultSavedRequest.getRequestURL();
            log.info("requestUrl " + requestUrl);
            getRedirectStrategy().sendRedirect(request, response, requestUrl);
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
	}

}
