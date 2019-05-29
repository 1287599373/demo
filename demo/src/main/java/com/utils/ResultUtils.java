package com.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultUtils {

	public static ObjectMapper mapper = new ObjectMapper();

	static {
	    // 转换为格式化的json
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);

	    // 如果json中有新增的字段并且是实体类类中不存在的，不报错
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static JSONObject createResult(ResultEnum resultEnum,Object data){
		JSONObject json = new JSONObject();
		json.put(Constants.Inter.CODE, resultEnum.code);
		json.put(Constants.Inter.MSG, resultEnum.msg);
		json.put(Constants.Inter.DATA,ObjToJsonStr(data));
		return json;
	}
	
	static String ObjToJsonStr(Object obj) {
		String resultStr = null;
		try {
			resultStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage());
		}
		return resultStr;
	}
}
