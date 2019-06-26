package com.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.model.LgMall;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

public interface MallService {

	LayPage getMallList(LgMall mall, PageUtils page);

	String saveMall(LgMall mall);

	String getMallInfo(Integer id);

	String updateMall(LgMall mall);

	String delete(String ids);
	
	String uploadFile(MultipartFile file,String path)throws IllegalStateException,IOException;
}
