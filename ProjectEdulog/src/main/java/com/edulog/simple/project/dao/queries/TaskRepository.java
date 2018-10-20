package com.edulog.simple.project.dao.queries;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edulog.simple.project.dao.collections.Task;

public interface TaskRepository extends MongoRepository<Task, String> {

	public Optional<Task> findById(String id);
}
