package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.CommonService;
import com.utils.BaseController;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController extends BaseController{

	@Autowired
	private CommonService commonService;
	
	@RequestMapping("/getLgBidPage")
	public LayPage getLgBidPage(String startTime,String endTime,String userName) {
		log.info("getLgBidPage-->params:{},{},{}",startTime,endTime,userName);
		PageUtils pageUtils = new PageUtils(request);
		LayPage page = commonService.getLgBidPage(startTime,endTime,userName,pageUtils);
		return page;
	}
	
	@RequestMapping("/getLgBidPeoplePage")
	public LayPage getLgBidPeoplePage(String startTime,String endTime,String userName) {
		log.info("getLgBidPeoplePage-->params:{},{},{}",startTime,endTime,userName);
		PageUtils pageUtils = new PageUtils(request);
		LayPage page = commonService.getLgBidPeoplePage(startTime,endTime,userName,pageUtils);
		return page;
	}
	
	@RequestMapping("/getMallTradePage")
	public LayPage getMallTradePage(String startTime,String endTime,String userName,String mallOrder,String mallName) {
		log.info("getLgBidPeoplePage-->params:{},{},{},{},{}",startTime,endTime,userName,mallOrder,mallName);
		PageUtils pageUtils = new PageUtils(request);
		LayPage page = commonService.getMallTradePage(startTime,endTime,userName,mallOrder,mallName,pageUtils);
		return page;
	}
	
}
