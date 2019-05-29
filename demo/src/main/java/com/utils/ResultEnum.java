package com.utils;

public enum ResultEnum {
	SUCCESS(0,"处理成功")
	,EXCEPTION(1,"系统繁忙")
	,PARAM_ERROR(2,"参数错误")
	,NOLOGIN(3,"未登录")
	,USERNAME_OR_PASSWORD_ERR(4,"用户名或密码错误")
	;
	public int code;
	public String msg;
	
	ResultEnum(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
}
