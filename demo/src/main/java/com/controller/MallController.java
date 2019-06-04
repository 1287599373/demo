package com.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.model.LgMall;
import com.service.MallService;
import com.utils.BaseController;
import com.utils.page.LayPage;
import com.utils.page.PageUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/mall")
public class MallController extends BaseController{

	@Autowired
	private MallService mallService;
	
	@Value("${attach.dir}")
	private String attachDir;
	
	@RequestMapping("/list")
	public LayPage getMallList(LgMall mall) {
		PageUtils page = new PageUtils(request);
		LayPage layPage = mallService.getMallList(mall,page);
		return layPage;
	}
	
	@RequestMapping("/save")
	public String saveMall(LgMall mall) {
		return mallService.saveMall(mall);
	}
	
	@RequestMapping("/info")
	public String getMallInfo(@RequestParam Integer id) {
		return mallService.getMallInfo(id);
	}
	
	@RequestMapping("/update")
	public String updateMall(LgMall mall) {
		return mallService.updateMall(mall);
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam String ids) {
		return mallService.delete(ids);
	}
	
	@RequestMapping("/addAttach")
	public JSONObject uploadPicture(@RequestParam("file")MultipartFile file){ 
	    //如果文件内容不为空，则写入上传路径 
	    JSONObject res =null;
		JSONObject resUrl = null;
		//将上传文件保存到一个目标文档中 
		File tempFile;
		try {
			res = new JSONObject();
			resUrl = new JSONObject();
			//上传文件路径 
			String path = attachDir;
   
			//上传文件名    
			String fileName = file.getOriginalFilename();        
			File filepath = new File(path, fileName); 
			//判断路径是否存在，没有就创建一个 
			if (!filepath.getParentFile().exists()) { 
			    filepath.getParentFile().mkdirs(); 
			    } 
			//tempFile = new File(path + File.separator + fileName);
			tempFile = new File(fileName);
			file.transferTo(tempFile);
			
			resUrl.put("src", tempFile.getPath());
		    res.put("code", 0);
		    res.put("msg", "上传成功！");
		    res.put("data", resUrl);
		    
		    /**str = "{\"code\": 0,\"msg\": \"上传成功\",\"data\": {\"src\":\""+path+fileName + "\"}}";**/
		    log.info(path + File.separator + fileName);
		    log.info(res.toString());
		    return res;
		} catch (Exception e) {
			res.put("code", 1);
		    res.put("msg", "上传失败！");
		    res.put("data", "");
			e.printStackTrace();
			return res;
		}
	} 
}
