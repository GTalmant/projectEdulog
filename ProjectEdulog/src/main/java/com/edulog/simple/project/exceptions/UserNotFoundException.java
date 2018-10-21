package com.edulog.simple.project.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String id) {
		super("could not found the user: " + id);
	}

}
