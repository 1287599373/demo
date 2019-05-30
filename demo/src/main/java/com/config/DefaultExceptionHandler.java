package com.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.utils.ResultEnum;
import com.utils.ResultUtils;

@ControllerAdvice
public class DefaultExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String catchException(Exception e) {
		e.printStackTrace();
		return ResultUtils.createResult(ResultEnum.EXCEPTION, null).toJSONString();
		
	}
}
