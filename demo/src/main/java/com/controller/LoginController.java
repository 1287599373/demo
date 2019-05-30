package com.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.model.User;
import com.service.UserService;
import com.utils.BaseController;
import com.utils.Constants;
import com.utils.ResultEnum;
import com.utils.page.Layui;
import com.utils.page.PageUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class LoginController extends BaseController{

	@Autowired
	private UserService userService;
	
	//@PostMapping("/login")
	@RequestMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password,HttpSession session) {
		User user = userService.authentication(username, password);
		if(null == user) {
			return response(ResultEnum.USERNAME_OR_PASSWORD_ERR, null);
		}
		session.setAttribute(Constants.SessionAttr.USER_NAME, user.getUsername());
		session.setAttribute(Constants.SessionAttr.USER_ID, user.getUserid().toString());
		log.info("用户：{}登录成功！时间：{}",user.getUsername(),LocalDateTime.now());
		System.out.println(request);
		return response(ResultEnum.SUCCESS, user);
	}
	
	@PostMapping("/exit")
	public String exit(@RequestParam String userId,HttpSession session) {
		String session_userId = (String) session.getAttribute(Constants.SessionAttr.USER_ID);
		if(StringUtils.isEmpty(userId) || !session_userId.equals(userId)) {
			return response(ResultEnum.PARAM_ERROR, null);
		}
		String userName = (String) session.getAttribute(Constants.SessionAttr.USER_NAME);
		session.removeAttribute(Constants.SessionAttr.USER_NAME);
		session.removeAttribute(Constants.SessionAttr.USER_ID);
		log.info("用户:{}已退出",userName);
		return response(ResultEnum.SUCCESS, null);
	}
	
	@RequestMapping("/test")
	public String test() {
		return "hello";
	}
	
	/**
	 * 测试分页用
	 * @param params
	 * @return
	 */
	@RequestMapping("/user/list")
    @ResponseBody
    public Layui list(@RequestParam Map<String, Object> params){
     
		PageUtils pageUtil = new PageUtils(request);
		PageHelper.startPage(pageUtil.getCurrPage(), pageUtil.getPageSize());
        List<User> findAllUsers = userService.findAllUsers();
        PageInfo<User> tmp = new PageInfo<>(findAllUsers);
        return Layui.data((int)tmp.getTotal(), tmp.getList());
    }
}
