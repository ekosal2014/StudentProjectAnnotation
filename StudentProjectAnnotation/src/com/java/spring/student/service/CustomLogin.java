package com.java.spring.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.spring.student.mapper.StudentMapper;
import com.java.spring.student.model.Student;



@Service("customLogin")
public class CustomLogin implements UserDetailsService{

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Student student = new Student();
		
		student = studentMapper.login(username);
		if (student == null){
			throw new UsernameNotFoundException("UserName Or Password not found.");
		}
		try{
			User user = new User(student.getStudent_code(), student.getPassword(), true , true, true, true, getGrantedAuthorities(student));			
			return user;
		}catch(Exception e){
			e.printStackTrace();
		}
		/*User user = new User(student.getStudentCode(), student.getPassword(), true , true, true, true, getGrantedAuthorities(student));
		System.out.println("=========== PWD ============");*/
		return null;
	}

	private List<GrantedAuthority> getGrantedAuthorities(Student student){		
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();				
			String txt = student.getTxt().equals("0") ? "STUDENT" : "TEACHER";
			authorities.add(new SimpleGrantedAuthority("ROLE_"+txt));
			return authorities;
		
		
	}

}
