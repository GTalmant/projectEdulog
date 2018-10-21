package com.edulog.simple.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.dao.collections.User;
import com.edulog.simple.project.services.TaskService;
import com.edulog.simple.project.services.UserService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@Autowired
	private UserService userService;

	
	@GetMapping("/task")
	public List<Task> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/task/{id}")
	public Task getTask(@PathVariable(value="id") String id) {
		return service.getTask(id);
	}
	
	@PostMapping("/task")
	public Task newTask(@RequestBody Task task) {
		return service.newTask(task);
	}
	
	@PutMapping("/task/{id}")
	public Task updateTask(@RequestBody Task task, @PathVariable(value="id") String id) {
		task.setId(id);
		return service.newTask(task);
	}
	
	
	@DeleteMapping("/task")
	public Task delete(@RequestBody Task task) {
		return service.delete(task);
	}
	
	@DeleteMapping("/task/{id}")
	public Task deleteById(@PathVariable(value="id") String id) {
		Task task = service.getTask(id);
		List<User> users = userService.findByTasks(task);
		//here remove task from user
		return task;
	}

}
