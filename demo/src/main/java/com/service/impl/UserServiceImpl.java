package com.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.joda.LocalDateTimeParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.UserMapper;
import com.model.User;
import com.service.UserService;
import com.utils.ResultEnum;
import com.utils.ResultUtils;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

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

	@Override
	public List<User> findAllUsers() {
		return userMapper.findAllUsers();
	}

	@Override
	public LayPage getUserList(User user, PageUtils page) {
		PageHelper.startPage(page.getCurrPage(), page.getPageSize());
		List<User> userList = userMapper.findUsers(user);
		PageInfo<User> pageInfo = new PageInfo<User>(userList);
		return LayPage.create(pageInfo);
	}

	@Override
	@Transactional
	public String saveUser(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setTime(sdf.format(new Date()));
		int i = userMapper.insertSelective(user);
		if(i>0)
			return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
		return ResultUtils.createResult(ResultEnum.FAIL,null).toJSONString();
	}

	@Override
	public String getUserInfo(Integer id) {
		if(null == id) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		User user = userMapper.selectByPrimaryKey(id);
		if(null == user) {
			return ResultUtils.createResult(ResultEnum.NO_DATA, null).toJSONString();
		}
		return ResultUtils.createResult(ResultEnum.SUCCESS, user).toJSONString();
	}

	@Override
	public String updateUser(User user) {
		if(null == user || null == user.getUserid()) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		int i = userMapper.updateByPrimaryKeySelective(user);
		if(i>0)
			return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
		return ResultUtils.createResult(ResultEnum.FAIL,null).toJSONString();
	}

	@Override
	public String delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		List<String> idList = Arrays.asList(ids.split(","));
		boolean validate = idList.stream().allMatch(id -> Pattern.matches("\\d+",id));
		if(!validate) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		idList.forEach(id->{
			userMapper.deleteByPrimaryKey(Integer.parseInt(id));
		});
		return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
	}	
}
