package com.edulog.simple.project.dao.collections;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.lang.Nullable;

public class User {
	
	@Id
	@NotNull
	private String id;

	@NotNull
	@Size(min = 2, max= 20, message="firstName size must be between 2 and 20 characters")
	private String firstName;
	
	@NotNull
	@Size(min = 2, max= 20, message="lastName size must be between 2 and 20 characters")
	private String lastName;
	
	@Nullable
	private List<Task> tasks; //haven't find a way to check the data inside this List
	
	public User() {
	}
	
	public User(String id, String firstName, String lastName) {
		this(id, firstName, lastName, new ArrayList<>());
	}
	
	public User(String id, String firstName, String lastName, List<Task> tasks) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.tasks = tasks;
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

	public List<Task> getTasks() {
		return tasks;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	@Override
	public String toString() {
		return String.format("User[id=%s, firstName=%s, lastName=%s, task=%s]",
				id, firstName, lastName, tasks.toString());
	}
}
