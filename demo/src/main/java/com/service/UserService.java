package com.service;

import com.model.User;

public interface UserService {

	User authentication(String username,String password);
}
