package com.zettamine.boot.service;



import com.zettamine.boot.entity.User;

public interface IUserService {
	
	Boolean validateUserCredintials(String userName,String password);
	
	User getUserDetailsByUserName(String userName);
	
	

}
