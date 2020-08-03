package com.jiwoong.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class MyLoginSuccessHandler implements AuthenticationSuccessHandler{
	
	private String defaultUrl;
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("userID", authentication.getName());
		
		resultRedirectStrategy(request, response, authentication);
		
	}
	
	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException{
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if(savedRequest != null) {
			String targetURL = savedRequest.getRedirectUrl();
			redirectStrategy.sendRedirect(request, response, targetURL);
		} else {
			redirectStrategy.sendRedirect(request, response, defaultUrl);
		}
		
	}

}
