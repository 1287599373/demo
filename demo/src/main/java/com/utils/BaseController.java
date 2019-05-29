package com.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
	
	@Autowired
	public HttpServletRequest request;
	@Autowired
	public HttpServletResponse respone;

	public static String response(ResultEnum resultEnum,Object data) {
		return ResultUtils.createResult(resultEnum, data).toJSONString();
	}
}
