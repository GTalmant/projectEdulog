package com.edulog.simple.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.dao.queries.TaskRepository;
import com.edulog.simple.project.exceptions.TaskNotFoundException;

@Service
public class TaskService {
	
	
	@Autowired
	private TaskRepository repo;
	
	@Autowired
	private UserService userService;
	
	public List<Task> getAll() {
		return repo.findAll();
	}
	
	public Task getTask(String id) {
		return repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
	}
	
	public Task newTask(Task task) {
		return repo.save(task);
	}
	
	public Task delete(Task task) {
		repo.delete(task);
		userService.removeTaskForAllUsers(task);
		return task;
	}


}
