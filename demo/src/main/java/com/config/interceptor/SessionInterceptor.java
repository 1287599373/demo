package com.config.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.utils.Constants;
import com.utils.ResultEnum;
import com.utils.ResultUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

	// 不鉴权接口后缀
	private String[] includeUrls = new String[] { "/login", "/exit"};

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI(); 
		// 判断是否匹配不鉴权接口
		boolean result = Arrays.asList(includeUrls).stream().anyMatch((x) -> url.endsWith(x));
		if (!result) {// 鉴权
			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute(Constants.SessionAttr.USER_ID);
			if (StringUtils.isEmpty(userId)) {
				String msg = ResultUtils.createResult(ResultEnum.NOLOGIN, null).toJSONString();
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(msg);
				return false;
			}
		}
		log.info("result:{}",result);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
