package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;
import com.utils.BaseController;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController extends BaseController{

	@Autowired
	private UserService userService;
	
	@RequestMapping("/page")
	public LayPage getUserList(User user) {
		log.info("UserController.getUserList");
		PageUtils page = new PageUtils(request);
		LayPage layPage = userService.getUserList(user,page);
		return layPage;
	}
	
	@RequestMapping("/save")
	public String saveUser(User user) {
		return userService.saveUser(user);
	}
	
	@RequestMapping("/info")
	public String getUserInfo(@RequestParam Integer id) {
		return userService.getUserInfo(id);
	}
}
