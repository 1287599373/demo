package com.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.LgMallMapper;
import com.mapper.LgTransactionDetailsMapper;
import com.mapper.UserMapper;
import com.model.LgMall;
import com.model.LgTransactionDetails;
import com.model.User;
import com.service.MallTradeService;
import com.utils.ExcelUtils;
import com.utils.ResultEnum;
import com.utils.ResultUtils;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

@Service
public class MallTradeServiceImpl implements MallTradeService {
	private final static String[] mall_excel_title = {"货车用户名","商品名称","商品规格","商品价格/元","商品库存量","商品积分","消费数量","消费积分","日期"};
	private final static String[] mall_excel_key = {"UserName","name","specifications","price","stockbalance","Requiredintegral","mallNumber","Consumptionintegral","time"};
	@Autowired
	private LgTransactionDetailsMapper lgTransactionDetailsMapper;
	
	@Autowired
	private LgMallMapper lgMallMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public LayPage getMallTradePage(String startTime, String endTime, String userName, String mallOrder,String mallName, PageUtils page) {
		PageHelper.startPage(page.getCurrPage(), page.getPageSize());
		List<Map<String,Object>> list = lgTransactionDetailsMapper.getMallTradePage(startTime,endTime,userName,mallOrder,mallName);
		PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(list);
		return LayPage.create(pageInfo);
	}
	
	@Override
	public List<LgMall> getAllLgMall() {
		 return lgMallMapper.findMalls(null);
	}
	

	@Override
	public List<User> getAllTruckUser() {
		//获取所有货车用户
		User user = new User();
		user.setTerracetype(2);
		List<User> users = new ArrayList<>();
		List<User> truckUsers = userMapper.findUsers(user);
		if(truckUsers!=null && truckUsers.size()>0){
			for (int i = 0; i < truckUsers.size(); i++) {
				User usertmp = new User();
				usertmp.setUserid(truckUsers.get(i).getUserid());
				usertmp.setUsername(truckUsers.get(i).getUsername());
				users.add(usertmp);
			}
		}
		return users;
	}

	@Override
	public LgMall finalMallByMallId(String id) {
		if(StringUtils.isNotBlank(id)){
			return lgMallMapper.selectByPrimaryKey(new Integer(id));
		}else{
			return null;
		}
	}

	@Override
	public String checkBugNumAndCosintegral(String mallid, String lguserid, String cosNum) {
		Integer mallId = null;
		Integer lguserId = null;
		Integer cosNo = null;
		if(StringUtils.isNotBlank(mallid)){
			mallId = new Integer(mallid);
		}
		if(StringUtils.isNotBlank(lguserid)){
			lguserId = new Integer(lguserid);
		}
		if(StringUtils.isNotBlank(cosNum)){
			cosNo = new Integer(cosNum);
		}
		if(mallId !=null && lguserId!= null && cosNo!= null){
			//商品兑换所需积分校验
			LgMall lgmall = lgMallMapper.selectByPrimaryKey(mallId);
			if(cosNo>lgmall.getStockbalance()){
				return ResultUtils.createResult(ResultEnum.INSUFFICIENT_STOCK, null).toJSONString();
			}else{
				Integer cosintegral = lgmall.getRequiredintegral() * cosNo;
				User user = userMapper.selectByPrimaryKey(lguserId);
				if(user != null && user.getAccumulatepoints() < cosintegral){
					return ResultUtils.createResult(ResultEnum.INSUFFICIENT_INTEGRAL, null).toJSONString();
				}else if(user == null ){
					return ResultUtils.createResult(ResultEnum.USER_INFO_FAIL, null).toJSONString();
				}else{
					return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
				}
			}
		}else{
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
	}

	@Override
	@Transactional
	public  synchronized String add(String mallid, String lguserid, String cosNum,String time) {
		//校验
		String result = checkBugNumAndCosintegral(mallid, lguserid, cosNum);
		JSONObject jsonObject = JSON.parseObject(result);
		int successValue = ResultEnum.SUCCESS.code; 
		if(jsonObject.get("code") != null){
			if(successValue != (int)jsonObject.get("code")){
				return result;
			}
		};
		
		//保存
		Integer mallId = null;
		Integer lguserId = null;
		Integer cosNo = null;
		if(StringUtils.isNotBlank(mallid)){
			mallId = new Integer(mallid);
		}
		if(StringUtils.isNotBlank(lguserid)){
			lguserId = new Integer(lguserid);
		}
		if(StringUtils.isNotBlank(cosNum)){
			cosNo = new Integer(cosNum);
		}
		User user = userMapper.selectByPrimaryKey(lguserId);
		LgMall lgmall = lgMallMapper.selectByPrimaryKey(mallId);
		
		//更新用户积分和商品库存
		Integer cosintegral = lgmall.getRequiredintegral() * cosNo;
		lgmall.setStockbalance(lgmall.getStockbalance() - cosNo);
		user.setAccumulatepoints(user.getAccumulatepoints() - cosintegral);
		lgMallMapper.updateByPrimaryKeySelective(lgmall);
		userMapper.updateByPrimaryKey(user);
		//添加交易记录
		LgTransactionDetails record = new LgTransactionDetails();
		record.setConsumptionintegral(cosintegral);//消费积分
		record.setMallid(mallId);//消费商品id
		record.setMallnumber(cosNo);//消费数量
		record.setTime(time);//时间是传过来的
		record.setUserid(lguserId);//消费的货车用户
		//tdid为主键
		
		lgTransactionDetailsMapper.insert(record);
		return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
	}

	@Override
	public void export(HttpServletResponse response, String startTime, String endTime, String userName,
			String mallOrder, String mallName) {
		List<Map<String,String>> dataList = lgTransactionDetailsMapper.getAllMallTrade(startTime,endTime,userName,mallOrder,mallName);
		try {
			ExcelUtils.createExcel(response, dataList, mall_excel_key, mall_excel_title, "spjy.xls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String updateTime(String time, Integer id) {
		LgTransactionDetails record = new LgTransactionDetails();
		if(StringUtils.isEmpty(time) || null == id){
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		record.setTdid(id);
		record.setTime(time);
		lgTransactionDetailsMapper.updateByPrimaryKeySelective(record);
		return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
	}


	
}
