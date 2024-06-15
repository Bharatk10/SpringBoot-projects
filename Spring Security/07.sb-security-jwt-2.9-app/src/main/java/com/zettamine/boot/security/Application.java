package com.zettamine.boot.security;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zettamine.boot.security.entity.UserEntity;
import com.zettamine.boot.security.repository.UserRepository;

@SpringBootApplication
public class Application {
	
	@Autowired
    private UserRepository userRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@PostConstruct 
    public void initUsers() {
    	List<UserEntity> users = Stream.of(
		new UserEntity(1, "admin", "1234"),
		new UserEntity(2, "employee", "1234"),
		new UserEntity(3, "clerk", "1234"),
		new UserEntity(4, "sanjay", "1234")).collect(Collectors.toList());

        userRepo.saveAll(users);
    }

}
