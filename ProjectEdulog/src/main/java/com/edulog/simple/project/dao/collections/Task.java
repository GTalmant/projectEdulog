package com.edulog.simple.project.dao.collections;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class Task {
	
	@Id
	private String id;
	
	@NotNull
	@Size(min = 10, max= 100, message="description size must be between 10 and 150 characters")
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
	
	/**
	 * a litle help for JUnit test
	 * @return
	 */
	public String toJson() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"id\":\"").append(id).append("\", \"description\":\"").append(description).append("\"}");
		return builder.toString();
	}
}


