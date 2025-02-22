package com.zettamine.docker.compose.servive;

import java.util.List;

import com.zettamine.docker.compose.entity.User;

public interface IUserService {
	
	List<User> getAllUsers();
	
	User saveUser(User user);
	
	User getUserById(Integer id);

}
