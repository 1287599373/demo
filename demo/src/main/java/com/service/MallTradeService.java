package com.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.model.LgMall;
import com.model.User;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

public interface MallTradeService {

	LayPage getMallTradePage(String startTime, String endTime, String userName,String mallOrder,String mallName,PageUtils pageUtils);
	
	/**获取所有货车*/
	List<User> getAllTruckUser();
	
	/**获取所有商品*/
	public List<LgMall> getAllLgMall();
	
	public LgMall finalMallByMallId(String id);
	
	/**校验函数**/
	public String checkBugNumAndCosintegral(String mallid,String lguserid,String cosNum);
	
	/**添加交易记录*/
	public String add(String mallid,String lguserid,String cosNum,String time);

	void export(HttpServletResponse response, String startTime, String endTime, String userName, String mallOrder,
			String mallName);

	String updateTime(String time, Integer id);
	
}
