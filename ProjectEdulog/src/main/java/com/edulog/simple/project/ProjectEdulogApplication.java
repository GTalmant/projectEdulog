package com.edulog.simple.project;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edulog.simple.project.dao.collections.User;
import com.edulog.simple.project.dao.queries.UserRepository;

@SpringBootApplication
public class ProjectEdulogApplication {
	
	@Autowired
	private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProjectEdulogApplication.class, args);
	}
	
	@SuppressWarnings("unused")
	private void run(String[] args) {
		Optional<User> firstUser = userRepo.findByFirstNameAndLastName("super", "user");
		if(firstUser.isPresent()) {
			System.out.println(String.format("!!!GTA!!! %s: found", firstUser.get().toString()));
		} else {
			System.out.println("!!!GTA!!! nothing found");
		}
	}
}
