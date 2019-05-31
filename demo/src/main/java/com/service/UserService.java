package com.service;

import java.util.List;

import com.model.User;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

public interface UserService {

	User authentication(String username,String password);
	
	List<User> findAllUsers();

	LayPage getUserList(User user, PageUtils page);

	String saveUser(User user);
}
