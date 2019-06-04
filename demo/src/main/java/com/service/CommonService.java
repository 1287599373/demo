package com.service;

import com.utils.page.LayPage;
import com.utils.page.PageUtils;

public interface CommonService {

	LayPage getLgBidPage(String startTime, String endTime, String userName, PageUtils pageUtils);

	LayPage getLgBidPeoplePage(String startTime, String endTime, String userName, PageUtils pageUtils);
	
	LayPage getMallTradePage(String startTime, String endTime, String userName,String mallOrder,String mallName,PageUtils pageUtils);

}
