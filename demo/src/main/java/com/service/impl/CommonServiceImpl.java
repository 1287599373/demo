package com.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.LgBidMapper;
import com.mapper.LgBidPeopleMapper;
import com.service.CommonService;
import com.utils.ExcelUtils;
import com.utils.ResultEnum;
import com.utils.ResultUtils;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

@Service
public class CommonServiceImpl implements CommonService{
	private final static String[] cart_excel_title = {"货车用户名","姓名","手机号","价格","标选状态","日期"};
	private final static String[] cart_excel_key = {"UserName","name","phoneNumber","price","status","time"};
	private final static String[] bid_excel_title = {"用户名","商品名","数量","发出地点","接受地点","订单时间","标选状态","完成状态","日期"};
	private final static String[] bid_excel_key = {"username","Goodsname","Quantity","Consignment","ReceivingPlace","bidTime","bidStart","finishStart","Time"};
 	@Autowired
	private LgBidMapper bidMapper;
	
	@Autowired
	private LgBidPeopleMapper bidPeopleMapper;

	@Override
	public LayPage getLgBidPage(String startTime, String endTime, String userName, PageUtils page) {
		PageHelper.startPage(page.getCurrPage(), page.getPageSize());
		List<Map<String,Object>> list = bidMapper.getLgBidPage(startTime,endTime,userName);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
		return LayPage.create(pageInfo);
	}

	@Override
	public LayPage getLgBidPeoplePage(String startTime, String endTime, String userName, PageUtils page) {
		PageHelper.startPage(page.getCurrPage(), page.getPageSize());
		List<Map<String,Object>> list = bidPeopleMapper.getLgBidPeoplePage(startTime,endTime,userName);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
		return LayPage.create(pageInfo);
	}

	@Override
	public void exportCart(HttpServletResponse response,String startTime, String endTime, String userName) {
		List<Map<String,String>> dataList = bidPeopleMapper.getAllLgBidPeople(startTime, endTime, userName);
		try {
			ExcelUtils.createExcel(response, dataList,cart_excel_key, cart_excel_title,"hc.xls");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void exportLgBid(HttpServletResponse response, String startTime, String endTime, String userName) {
		List<Map<String,String>> dataList = bidMapper.getAllLgBid(startTime, endTime, userName);
		try {
			ExcelUtils.createExcel(response, dataList,bid_excel_key, bid_excel_title,"hz.xls");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public String deleteLgBidPeople(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		List<String> idList = Arrays.asList(ids.split(","));
		boolean validate = idList.stream().allMatch(id -> Pattern.matches("\\d+",id));
		if(!validate) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		idList.forEach(id->{
			bidPeopleMapper.deleteByPrimaryKey(Integer.parseInt(id));
		});
		return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
	}

	@Override
	@Transactional
	public String deleteLgBid(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		List<String> idList = Arrays.asList(ids.split(","));
		boolean validate = idList.stream().allMatch(id -> Pattern.matches("\\d+",id));
		if(!validate) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		idList.forEach(id->{
			bidMapper.deleteByPrimaryKey(Integer.parseInt(id));
		});
		return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
	}

}
