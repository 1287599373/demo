package com.utils.page;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import com.github.pagehelper.PageInfo;

public class LayPage  extends HashMap<String, Object> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static LayPage data(Integer count,List<?> data){
        LayPage r = new LayPage();
        r.put("code", 0);
        r.put("msg", "");
        r.put("count", count);
        r.put("data", data);
        return r;
    }
	
	public static <T> LayPage create(PageInfo<T> pageInfo) {
		if(null == pageInfo) {
			return null;
		}
		long count = pageInfo.getTotal();
		List<T> data = pageInfo.getList();
		LayPage r = new LayPage();
        r.put("code", 0);
        r.put("msg", "");
        r.put("count", count);
        r.put("data", data);
        return r;
	}
}