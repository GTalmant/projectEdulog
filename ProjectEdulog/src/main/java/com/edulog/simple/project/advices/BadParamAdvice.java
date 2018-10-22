package com.edulog.simple.project.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.edulog.simple.project.exceptions.BadParamException;

@ControllerAdvice
public class BadParamAdvice {
	
	@ResponseBody
	@ExceptionHandler(BadParamException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String badParamAdvice(BadParamException ex) {
		return ex.getMessage();
	}

}
