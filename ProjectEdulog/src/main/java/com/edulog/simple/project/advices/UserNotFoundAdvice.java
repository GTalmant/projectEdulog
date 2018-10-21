package com.edulog.simple.project.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.edulog.simple.project.exceptions.UserNotFoundException;

@ControllerAdvice
public class UserNotFoundAdvice {
	
	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String userNotFoundHandler(UserNotFoundException ex) {
		return ex.getMessage();
	}

}
