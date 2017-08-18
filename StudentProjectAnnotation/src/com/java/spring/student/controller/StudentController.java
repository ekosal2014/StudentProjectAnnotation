package com.java.spring.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.java.spring.student.service.StudentService;

@Controller
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(){
	
		return "index";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin(){
		//System.out.println(" =============== "+studentService.login("000000002").getFullName()+" ==============");
		return "login";
	}
}
