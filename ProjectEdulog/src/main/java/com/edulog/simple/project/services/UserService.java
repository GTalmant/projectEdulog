package com.edulog.simple.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.edulog.simple.project.dao.collections.Task;
import com.edulog.simple.project.dao.collections.User;
import com.edulog.simple.project.dao.queries.UserRepository;
import com.edulog.simple.project.exceptions.UserNotFoundException;

@Service
public class UserService {
	
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> getAll() {
		return repo.findAll();
	}
	
	public User getUser(String id) {
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public User newUser(User user) {
		user.getTasks().forEach(t -> taskService.newTask(t));
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

	/**
	 * 
	 * remove the current task on the selected user</br>
	 * @param task
	 */
	public User removeTaskForUser(Task task, String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userId));
		Update update = new Update();
		update.pull("tasks", task);
		mongoTemplate.updateMulti(query, update, User.class);
		return getUser(userId);
	}
	
	 /** 
	 * remove the current task on the selected user</br>
	 * @param task
	 */
	public User removeTaskForUserById(String taskId, String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userId));
		Update update = new Update();
		update.pull("tasks", Query.query(Criteria.where("id").is(taskId)));
		mongoTemplate.updateMulti(query, update, User.class);
		return getUser(userId);
	}
	
	/**
	 * 
	 * add the current task on the selected user</br>
	 * @param task
	 */
	public User addTaskToUser(Task task, String userId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(userId));
		Update update = new Update();
		// I supose that we don't want two same task in the task list
		update.addToSet("tasks", task);
		mongoTemplate.updateMulti(query, update, User.class);
		taskService.newTask(task);
		return getUser(userId);
	}
}
