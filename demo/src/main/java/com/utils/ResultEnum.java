package com.utils;

public enum ResultEnum {
	SUCCESS(0,"处理成功")
	,EXCEPTION(1,"系统繁忙")
	,PARAM_ERROR(2,"参数错误")
	,NOLOGIN(3,"未登录")
	,USERNAME_OR_PASSWORD_ERR(4,"用户名或密码错误")
	,FAIL(5,"处理失败"), NO_DATA(6,"无数据")
	
	,INSUFFICIENT_STOCK(7,"库存不足")
	,INSUFFICIENT_INTEGRAL(8,"用户积分不足")
	,USER_INFO_FAIL(9,"用户信息获取失败")
	;
	public int code;
	public String msg;
	
	ResultEnum(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
}
