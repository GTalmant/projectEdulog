package com.edulog.simple.project.exceptions;

public class TaskNotFoundException extends RuntimeException {
	
	public TaskNotFoundException(String id) {
		super("could not found the task: " + id);
	}

}
