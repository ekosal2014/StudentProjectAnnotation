package com.java.spring.student.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {
	
	@RequestMapping(value = {"/student/home","/student"}, method = RequestMethod.GET)
	public String studentHomePage(){
		System.out.println(" =================================== ");
		return "student/index";
	}
	

	@RequestMapping(value = {"/student/listsubject"}, method = RequestMethod.GET)
	public String studentListSubject(){
		System.out.println(" =================================== ");
		return "student/listSubject";
	}
	
	@RequestMapping(value = "/teacher/home", method = RequestMethod.GET)
	public String teacherHomePage(){
		System.out.println(" =================================== ");
		return "student/index";
	}
}
