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
import com.edulog.simple.project.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/user")
	public List<User> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable(value="id") String id) {
		return service.getUser(id);
	}
	
	
	@PostMapping("/user")
	public User newUser(@RequestBody User user) {
		return service.newUser(user);
	}
	
	@PutMapping("/user/{id}")
	public User updateUser(@RequestBody User user, @PathVariable(value="id") String id) {
		user.setId(id);
		return service.newUser(user);
	}
	
	@DeleteMapping("/user")
	public User delete(@RequestBody User user) {
		return service.delete(user);
	}
	
	@DeleteMapping("/user/{id}")
	public User deleteById(@PathVariable(value="id") String id) {
		User user = service.getUser(id);
		return service.delete(user);
	}
	
	@PostMapping("/user/{id}/tasks")
	public User addTaskToUser(@PathVariable(value="id") String id, @RequestBody Task task) {
		
		return service.addTaskToUser(task, id);
	}
	
	@DeleteMapping("/user/{userId}/tasks/{taskId}")
	public User deleteTaskForUser(@PathVariable(value="userId") String userId,@PathVariable(value="taskId") String taskId) {
		return service.removeTaskForUserById(taskId, userId);
	}
	
	@PutMapping("/user/{userId}/tasks/{taskId}")
	public User updateTaskForUser(@PathVariable(value="userId") String userId,@PathVariable(value="taskId") String taskId, @RequestBody Task task) {
		task.setId(taskId);
		return service.addTaskToUser(task, userId);
	}
	
}
