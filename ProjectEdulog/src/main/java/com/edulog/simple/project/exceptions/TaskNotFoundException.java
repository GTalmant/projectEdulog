package com.edulog.simple.project.exceptions;

public class TaskNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(String id) {
		super("could not found the task: " + id);
	}

}
