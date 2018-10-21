package com.edulog.simple.project.controller;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.dao.queries.TaskRepository;
import com.edulog.simple.project.exceptions.TaskNotFoundException;

@RestController
public class TaskController {
	
	@Autowired
	private TaskRepository repo;
	
	@GetMapping("/task")
	public List<Task> getAll() {
		return repo.findAll();
	}
	
	@GetMapping("/task/{id}")
	public Task getTask(@PathVariable(value="id") String id) {
		return repo.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
	}
	
	@PostMapping("/task")
	public Task newTask(@RequestBody Task task) {
		return repo.insert(task);
	}
	
	@PutMapping("/task/{id}")
	public Task updateTask(@RequestBody Task task, @PathVariable(value="id") String id) {
		task.setId(id);
		return repo.save(task);
	}
	
	@DeleteMapping("/task/{id}")
	public Task deleteById(@PathVariable(value="id") String id) {
		Optional<Task> task = repo.findById(id);
		if(task.isPresent()) {
			repo.delete(task.get());
		}
		return task.orElseThrow(() -> new TaskNotFoundException(id));
	}
	
	@DeleteMapping("/task")
	public Task delete(@RequestBody Task task) {
		repo.delete(task);
		return task;
	}
}
