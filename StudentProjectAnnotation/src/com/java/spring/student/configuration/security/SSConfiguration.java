package com.java.spring.student.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebMvcSecurity
public class SSConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("customLogin")
	UserDetailsService userDetailsService;
	
	@Autowired
	CustomUrlSuccess customUrlSuccess;
	
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService);
		//auth.authenticationProvider(authenticaticationProvider());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/student/**").access("hasRole('STUDENT')")
        .antMatchers("/teacher/**").access("hasRole('TEACHER')")
        .and().formLogin().loginPage("/login")
        .successHandler(customUrlSuccess)
        .usernameParameter("username").passwordParameter("password")
        .and().exceptionHandling().accessDeniedPage("/Access_Denied")
        .and().csrf().disable()
        ;
    }

	@Bean
	public PasswordEncoder passwordEncoder(){		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticaticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		//authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
