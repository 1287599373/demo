package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.LgMall;
import com.model.User;
import com.service.MallTradeService;
import com.utils.BaseController;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/malltrade")
public class MallTradeController extends BaseController{
	
	@Autowired
	private MallTradeService mallTradeService;

	@RequestMapping("/getMallTradePage")
	public LayPage getMallTradePage(String startTime,String endTime,String userName,String mallOrder,String mallName) {
		log.info("getLgBidPeoplePage-->params:{},{},{},{},{}",startTime,endTime,userName,mallOrder,mallName);
		PageUtils pageUtils = new PageUtils(request);
		LayPage page = mallTradeService.getMallTradePage(startTime,endTime,userName,mallOrder,mallName,pageUtils);
		return page;
	}
	
	@RequestMapping("/finAllTruckUser")
	public List<User> finAllTruckUser(){
		return mallTradeService.getAllTruckUser();
	}

	@RequestMapping("/finalALLBidmall")
	public List<LgMall> finalALLBidmall(){
		return mallTradeService.getAllLgMall();
	}
	
	@RequestMapping("/finalMallByMallId")
	public LgMall finalMallByMallId(String id){
		log.info("finalMallByMallId-->params:{}",id);
		return mallTradeService.finalMallByMallId(id);
	}
	
	@RequestMapping("/checkBugNumAndCosintegral")
	public String checkBugNumAndCosintegral(String mallid,String lguserid,String cosNum){
		log.info("checkBugNumAndCosintegral-->params:{},{},{}",mallid,lguserid,cosNum);
		return mallTradeService.checkBugNumAndCosintegral(mallid,lguserid,cosNum);
	}
	
	@PostMapping("/add")
	public String add(String MallName,String UserName,String cosNum,String time){
		//MallName 传递的mallid，UserName 传递的是userid，cosNum传的cosNum
		log.info("add-->params:{},{},{}",MallName,UserName,cosNum);
		String mallid = MallName;
		String lguserid = UserName;
		String cosnum = cosNum;
		return mallTradeService.add(mallid,lguserid,cosnum,time);
	}
	
	@RequestMapping("/exportMall")
	public void export(HttpServletResponse response,String startTime,String endTime,String userName
			,String mallOrder,String mallName){
		mallTradeService.export(response,startTime,endTime,userName,mallOrder,mallName);
	}
	
}
