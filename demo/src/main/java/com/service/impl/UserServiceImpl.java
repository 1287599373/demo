package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.mapper.UserMapper;
import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User authentication(String username, String password) {
		if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
			return null;
		}
		User user = userMapper.login(username,password);
		//判断用户为管理员
		if(user != null && null != user.getTerracetype() && 3 == user.getTerracetype()) {
			return user;
		}
		return null;
	}

}
