package com.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.utils.ResultEnum;
import com.utils.ResultUtils;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String catchException(Exception e) {
		log.error(e.getMessage());
		return ResultUtils.createResult(ResultEnum.EXCEPTION, null).toJSONString();
		
	}
}
