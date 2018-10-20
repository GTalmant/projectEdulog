package com.edulog.simple.project.dao.collections;

import java.util.Map;

import org.springframework.data.annotation.Id;

public class User {
	
	@Id
	private String id;

	private String firstName;
	private String lastName;
	
	private Map<String, Task> tasks;
	
	//this constructor was present in the mongoDB tutorial that I saw, may be not mandatory
	public User() {}
	
	public User(String firstName, String lastName, Map<String, Task> task) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.tasks = task;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public Map<String, Task> getTasks() {
		return tasks;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setTasks(Map<String, Task> tasks) {
		this.tasks = tasks;
	}
	
	@Override
	public String toString() {
		return String.format("User[id=%s, firstName=%s, lastName=%s, task=%s]",
				id, firstName, lastName, tasks.toString());
	}
}
