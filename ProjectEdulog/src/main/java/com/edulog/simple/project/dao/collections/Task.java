package com.edulog.simple.project.dao.collections;

import org.springframework.data.annotation.Id;

public class Task {
	
	@Id
	private String id;
	private String description;

	public Task(String id, String description) {
		this.id = id;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return String.format("Task[id=%s, description=%s]",
				id, description);
	}
}
