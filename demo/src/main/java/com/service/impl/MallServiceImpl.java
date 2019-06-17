package com.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mapper.LgMallMapper;
import com.model.LgMall;
import com.service.MallService;
import com.utils.ResultEnum;
import com.utils.ResultUtils;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

@Service
public class MallServiceImpl implements MallService{
	
	@Autowired
	private LgMallMapper mallMapper;

	@Override
	public LayPage getMallList(LgMall mall, PageUtils page) {
		PageHelper.startPage(page.getCurrPage(), page.getPageSize());
		List<LgMall> mallList = mallMapper.findMalls(mall);
		PageInfo<LgMall> pageInfo = new PageInfo<LgMall>(mallList);
		return LayPage.create(pageInfo);
	}

	@Override
	public String saveMall(LgMall mall) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		mall.setTime(sdf.format(new Date()));
		int i = mallMapper.insertSelective(mall);
		if(i>0)
			return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
		return ResultUtils.createResult(ResultEnum.FAIL,null).toJSONString();
	}

	@Override
	public String getMallInfo(Integer id) {
		if(null == id) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		LgMall mall = mallMapper.selectByPrimaryKey(id);
		if(null == mall) {
			return ResultUtils.createResult(ResultEnum.NO_DATA, null).toJSONString();
		}
		return ResultUtils.createResult(ResultEnum.SUCCESS, mall).toJSONString();
	}

	@Override
	public String updateMall(LgMall mall) {
		if(null == mall || null == mall.getMallid()) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		int i = mallMapper.updateByPrimaryKeySelective(mall);
		if(i>0)
			return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
		return ResultUtils.createResult(ResultEnum.FAIL,null).toJSONString();
	}

	@Override
	public String delete(String ids) {
		if(StringUtils.isEmpty(ids)) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		List<String> idList = Arrays.asList(ids.split(","));
		boolean validate = idList.stream().allMatch(id -> Pattern.matches("\\d+",id));
		if(!validate) {
			return ResultUtils.createResult(ResultEnum.PARAM_ERROR, null).toJSONString();
		}
		idList.forEach(id->{
			mallMapper.deleteByPrimaryKey(Integer.parseInt(id));
		});
		return ResultUtils.createResult(ResultEnum.SUCCESS, null).toJSONString();
	}
	
	@Override
	public  String uploadFile(MultipartFile file,String path) throws IllegalStateException, IOException {
        // String url =  request.getSession().getServletContext().getRealPath("/"); //服务器地址
        if (!file.isEmpty()) {
            // 文件保存路径
            String filePath = path + file.getOriginalFilename();
            // 转存文件
            file.transferTo(new File(filePath));
            return "yes";
        }else{
        	return "no";
        }
    }
}
