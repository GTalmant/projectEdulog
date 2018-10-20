package com.edulog.simple.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.dao.queries.TaskRepository;

@RestController
public class TaskController {
	
	@Autowired
	private TaskRepository repo;
	
	@GetMapping("/task")
	public List<Task> getAll() {
		System.out.printf("!!!GTA!!!: getAll asked\n");
		return repo.findAll();
	}
	
	@GetMapping("/task{id}")
	public Task getTask(@RequestParam(value="id") String id) {
		System.out.printf("!!!GTA!!!: received: %s\n", id);
		return repo.findById(id).orElse(null);
	}
	
	@PostMapping("/task")
	public Task newTask(@RequestBody Task task) {
		System.out.printf("!!!GTA!!!: %s\n", task.toString());
		return repo.insert(task);
	}
}
