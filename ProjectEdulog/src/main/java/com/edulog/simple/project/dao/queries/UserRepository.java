package com.edulog.simple.project.dao.queries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.dao.collections.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findById(String id);
	
	public Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
	
	public List<User> findAll();
	
	public List<User> findByTasks(Task tasks);

}
