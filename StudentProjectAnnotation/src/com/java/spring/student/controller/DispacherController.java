package com.java.spring.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.spring.student.service.StudentService;


@Controller
public class DispacherController {
	
	private static final Logger logger = Logger.getLogger(DispacherController.class);
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String getHome(){
	
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(){
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String getLogOut(HttpServletRequest request,HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:login?logout";
	}
	
	
}
