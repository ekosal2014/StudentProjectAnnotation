package com.java.spring.student.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {
	
	    @ExceptionHandler(NoHandlerFoundException.class)
	    public String handle(NoHandlerFoundException ex){
	        String message = "HTTP " + ex.getHttpMethod() + " for " + ex.getRequestURL() + " is not supported.";
	        System.out.println(" ============================== " + message);
	        return "error/404";
	    }
	
	 @ExceptionHandler(value = NullPointerException.class)
	    public String handleNullPointerException(Exception e) {

	        System.out.println("A null pointer exception ocurred " + e);

	        return "nullpointerExceptionPage";
	    }


	    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	    @ExceptionHandler(value = Exception.class)
	    public String handleAllException(Exception e) {

	        System.out.println("A unknow Exception Ocurred: " + e);

	        return "unknowExceptionPage";
	    }




}
