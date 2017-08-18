package com.java.spring.student.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.student.mapper.StudentMapper;
import com.java.spring.student.model.Student;

@Service
public class StudentService {
	
	@Autowired
	private StudentMapper studentMapper;
	
	public Student login(String username){
		Student student = new Student();
		try{
			student = studentMapper.login(username);
		}catch(Exception e){
			e.printStackTrace();
		}
		return student;
	}
}
