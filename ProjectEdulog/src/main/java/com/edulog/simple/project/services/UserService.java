package com.edulog.simple.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.dao.collections.User;
import com.edulog.simple.project.dao.queries.TaskRepository;
import com.edulog.simple.project.dao.queries.UserRepository;
import com.edulog.simple.project.exceptions.UserNotFoundException;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> getAll() {
		return repo.findAll();
	}
	
	public User getUser(String id) {
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public User newUser(User user) {
		user.getTasks().forEach(t -> taskRepo.save(t));
		return repo.save(user);
	}
	
	public User delete(User user) {
		repo.delete(user);
		return user;
	}

	public List<User> findByTasks(Task task) {
		return repo.findByTasks(task);
	}
	
	/**
	 * 
	 * remove the current task on every user</br>
	 * equivalent to : db.user.update({},{$pull: {tasks: _id: $1}}}, { multi: true}) </br>
	 * but I have not found the way to use directly this request
	 * @param task
	 */
	public void removeTaskForAllUsers(Task task) {
		Query query = new Query();
		Update update = new Update();
		update.pull("tasks", task);
		mongoTemplate.updateMulti(query, update, User.class);
	}

}
