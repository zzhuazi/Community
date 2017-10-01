package com.dgut.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dgut.service.ChatService;
import com.dgut.service.NotificationService;
import com.dgut.vo.UserVO;

/**
 * @ClassName: UserInterceptor
 * @Description: 登录用户拦截器,用户通知、私信检查
 * @author 闲明苑
 * @date 2017年9月27日 下午2:14:26
 */
public class UserInterceptor implements HandlerInterceptor {

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private ChatService chatService;

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
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
		// 当前用户已登录
		if (null != user) {
			try {
				// 获取未读通知数量
				int notifications = notificationService.countUnreadNotification(user.getId());
				session.setAttribute("notifications", notifications);
				// 获取未读私信数量
				int messages = chatService.countUnreadMessage(user.getId());
				session.setAttribute("messages", messages);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}

}
