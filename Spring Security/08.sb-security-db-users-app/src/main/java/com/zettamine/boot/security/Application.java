package com.zettamine.boot.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zettamine.boot.security.entity.UserInfo;
import com.zettamine.boot.security.repository.UserInfoRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{
	
	@Autowired
	private UserInfoRepository userRepo;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		List<UserInfo> users = new ArrayList<>();
		
		UserInfo user1 = new UserInfo("bharat","bharat@gmail.com",passwordEncoder.encode("bha@123"),"ADMIN");
		UserInfo user2 = new UserInfo("kumar","kumar@gmail.com",passwordEncoder.encode("kum@123"),"USER"); 
		
		users.add(user1);
		users.add(user2);
		
		//userRepo.saveAll(users);
		
	}

}
