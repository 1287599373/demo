package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.LgBidMapper;
import com.mapper.LgBidPeopleMapper;
import com.service.CommonService;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

@Service
public class CommonServiceImpl implements CommonService{

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
}
