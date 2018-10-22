package com.edulog.simple.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.dao.collections.User;
import com.edulog.simple.project.exceptions.BadParamException;
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
	public User newUser(@Valid @RequestBody User user, BindingResult bindingResult) {
		handleBindingResult(bindingResult);
		return service.newUser(user);
	}
	
	@PutMapping("/user/{id}")
	public User updateUser(@Valid @RequestBody User user, @PathVariable(value="id") String id, BindingResult bindingResult) {
		handleBindingResult(bindingResult);
		user.setId(id);
		return service.newUser(user);
	}
	
	@DeleteMapping("/user")
	public User delete(@Valid @RequestBody User user, BindingResult bindingResult) {
		handleBindingResult(bindingResult);
		return service.delete(user);
	}
	
	@DeleteMapping("/user/{id}")
	public User deleteById(@PathVariable(value="id") String id) {
		User user = service.getUser(id);
		return service.delete(user);
	}
	
	@GetMapping("/user/{id}/tasks")
	public List<Task> getAllTaskForUser(@PathVariable(value="id") String id) {
		return service.getUser(id).getTasks();
	}
	
	@PostMapping("/user/{id}/tasks")
	public User addTaskToUser(@PathVariable(value="id") String id, @Valid @RequestBody Task task, BindingResult bindingResult) {
		handleBindingResult(bindingResult);
		return service.addTaskToUser(task, id);
	}
	
	@DeleteMapping("/user/{userId}/tasks/{taskId}")
	public User deleteTaskForUser(@PathVariable(value="userId") String userId,@PathVariable(value="taskId") String taskId) {
		return service.removeTaskForUserById(taskId, userId);
	}
	
	@PutMapping("/user/{userId}/tasks/{taskId}")
	public User updateTaskForUser(@PathVariable(value="userId") String userId,@PathVariable(value="taskId") String taskId, @Valid @RequestBody Task task, BindingResult bindingResult) {
		handleBindingResult(bindingResult);
		task.setId(taskId);
		return service.addTaskToUser(task, userId);
	}
	

	/**
	 * handle the result of the binding and throw a BadParamException if there are field errors
	 * @param bindingResult
	 */
	private void handleBindingResult(BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors()) {
			StringBuilder stringBuilder = new StringBuilder();
			bindingResult.getFieldErrors().forEach(field -> stringBuilder.append(field.getDefaultMessage()).append("\n"));
			throw new BadParamException(stringBuilder.toString());
		}
	}
	
}
