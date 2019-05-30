package com.service;

import java.util.List;

import com.model.User;

public interface UserService {

	User authentication(String username,String password);
	
	List<User> findAllUsers();
}
