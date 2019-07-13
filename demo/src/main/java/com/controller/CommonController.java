package com.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping("/exportLgBidPeople")
	public void exportCart(HttpServletResponse response,String startTime, String endTime, String userName){
		commonService.exportCart(response,startTime, endTime, userName);
	}
	
	@RequestMapping("/exportLgBid")
	public void exportLgBid(HttpServletResponse response,String startTime, String endTime, String userName){
		commonService.exportLgBid(response,startTime, endTime, userName);
	}
	
	@RequestMapping("/hc/delete")
	public String deleteHC(@RequestParam String ids){
		return commonService.deleteLgBidPeople(ids);
	}
	
	@RequestMapping("/hz/delete")
	public String deleteHZ(@RequestParam String ids){
		return commonService.deleteLgBid(ids);
	}
}
