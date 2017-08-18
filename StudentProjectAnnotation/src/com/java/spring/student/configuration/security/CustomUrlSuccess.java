package com.java.spring.student.configuration.security;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomUrlSuccess extends SimpleUrlAuthenticationSuccessHandler{

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String targetUrl = deterMineTarGetUrl(authentication);
		 if (response.isCommitted()) {
	            System.out.println("Can't redirect");
	            return;
	        }
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	private String deterMineTarGetUrl(Authentication authentication){
		String targetUrl = "";
		Collection<? extends GrantedAuthority> authority = authentication.getAuthorities();
		List<String> roles = new ArrayList<>();
		for(GrantedAuthority a : authority ){
			roles.add(a.getAuthority());
		}
		
		if (isStudent(roles))
			targetUrl = "/student";
		
		if (isTeacher(roles))
			targetUrl = "/teacher";
		
		return targetUrl;
	}
	private boolean isStudent(List<String> roles){
		
		if (roles.contains("ROLE_STUDENT")){
			return true;
		}
		return false
				;
	}
	private boolean isTeacher(List<String> roles){
		
		if (roles.contains("ROLE_TEACHER")){
			return true;
		}
		return false;
	}
}
