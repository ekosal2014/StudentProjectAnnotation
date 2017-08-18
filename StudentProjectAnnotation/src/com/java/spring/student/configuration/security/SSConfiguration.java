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
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
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
		auth.userDetailsService(userDetailsService);	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers("/", "/home").permitAll()
        .antMatchers("/student/**").access("hasRole('ROLE_STUDENT')")
        .antMatchers("/teacher/**").access("hasRole('ROLE_TEACHER')")
        .and().formLogin().loginPage("/login")
        .successHandler(customUrlSuccess)
        .usernameParameter("username").passwordParameter("password")        
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied")
        .and().exceptionHandling().accessDeniedPage("/403")
        .and()
		  	  .sessionManagement()
		  	  .sessionAuthenticationErrorUrl("/login")
		  	  .sessionFixation()
		  	  .changeSessionId()
		  	  .maximumSessions(10)
		  	  .maxSessionsPreventsLogin(true)
		  	  .expiredUrl("/login/maxSessions")
		  	  .sessionRegistry(sessionRegistryImpl()); // when login it create one session already;
    }

	@Bean(name="sessionRegistry")
    protected SessionRegistry sessionRegistryImpl()
    {
        return new SessionRegistryImpl();
    }
}
