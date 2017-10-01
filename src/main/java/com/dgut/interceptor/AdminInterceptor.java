package com.dgut.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dgut.vo.UserVO;

/**
 * @ClassName: AdminInterceptor
 * @Description: 后台管理里拦截器
 * @author 闲明苑
 * @date 2017年9月29日 下午7:25:27
 */
public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		Object attribute = request.getSession().getAttribute("user");
		if (null != attribute && attribute instanceof UserVO) {
			return true;
		}
		String servletPath = request.getServletPath();
		System.out.println("servletPath : " + servletPath);

		StringBuilder url = new StringBuilder();
		url.append(servletPath);
		Enumeration<String> parameterNames = request.getParameterNames();
		// 如果有请求参数，则带上
		if (parameterNames.hasMoreElements()) {
			url.append("?");
		}
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			String parameter = request.getParameter(parameterName);
			System.out.println(parameterName + " : " + parameter);
			url.append(parameterName).append("=").append(parameter).append("&");
		}

		response.sendRedirect("/login?url=" + url.toString());
		return false;
	}

}
