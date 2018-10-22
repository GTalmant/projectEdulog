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
import com.edulog.simple.project.exceptions.BadParamException;
import com.edulog.simple.project.services.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@GetMapping("/task")
	public List<Task> getAll() {
		return service.getAll();
	}
	
	@GetMapping("/task/{id}")
	public Task getTask(@PathVariable(value="id") String id) {
		return service.getTask(id);
	}
	
	@PostMapping("/task")
	public Task newTask(@Valid @RequestBody Task task, BindingResult bindingResult) {
		handleBindingResult(bindingResult);
		return service.newTask(task);
	}
	
	@PutMapping("/task/{id}")
	public Task updateTask(@Valid @RequestBody Task task, @PathVariable(value="id") String id, BindingResult bindingResult) {
		handleBindingResult(bindingResult);
		task.setId(id);
		return service.newTask(task);
	}
	
	
	@DeleteMapping("/task")
	public Task delete(@Valid @RequestBody Task task, BindingResult bindingResult) {
		handleBindingResult(bindingResult);
		return service.delete(task);
	}
	
	@DeleteMapping("/task/{id}")
	public Task deleteById(@PathVariable(value="id") String id) {
		Task task = service.getTask(id);
		return service.delete(task);
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
