package com.service;

import javax.servlet.http.HttpServletResponse;

import com.utils.page.LayPage;
import com.utils.page.PageUtils;

public interface CommonService {

	LayPage getLgBidPage(String startTime, String endTime, String userName, PageUtils pageUtils);

	LayPage getLgBidPeoplePage(String startTime, String endTime, String userName, PageUtils pageUtils);

	void exportCart(HttpServletResponse response,String startTime, String endTime, String userName);

	void exportLgBid(HttpServletResponse response, String startTime, String endTime, String userName);

	String deleteLgBidPeople(String ids);

	String deleteLgBid(String ids);

}
