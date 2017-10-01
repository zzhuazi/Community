package com.dgut.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.service.NotificationService;
import com.dgut.util.Page;
import com.dgut.vo.NotificationVO;
import com.dgut.vo.UserVO;

/**
 * @ClassName: NotificationController
 * @Description: 通知相关控制器
 * @author 闲明苑
 * @date 2017年9月29日 下午1:55:50
 */
@Controller
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	/**
	 * @Title: getNotificationsPage
	 * @Description: 获取通知列表页面，分页实现，非更多实现
	 * type 通知类型 0 全部, 1 已读, 2 未读
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "notifications" }, method = { RequestMethod.GET })
	public String getNotificationsPage(@RequestParam(defaultValue = "1") Integer currentpage, Integer type,
			HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			// 未登录
			return "redirect:/login";
		}
		try {
			Page page = new Page(5, currentpage, 0);
			Integer userId = ((UserVO) subject.getSession().getAttribute("user")).getId();
			Boolean isReaded = null;
			if (null != type) {
				switch (type) {
				case 1:
					isReaded = true;
					break;
				case 2:
					isReaded = false;
					break;
				default:
					break;
				}
			} else {
				type = 2;
				isReaded = false;
			}
			List<NotificationVO> notifications = notificationService.getNotifications(page, userId, isReaded);
			request.setAttribute("notifications", notifications);
			// 获取该类型通知总数页数
			int total = notificationService.getNotifications(null, userId, isReaded).size();
			total = (total / 5 + (total % 5 != 0 ? 1 : 0));
			currentpage = (currentpage >= total ? total : currentpage);
			request.setAttribute("page", currentpage);
			request.setAttribute("total", total);
			request.setAttribute("type", type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 分页实现，非更多实现
		return "notification";
	}

	/**
	 * @Title: updateNotification
	 * @Description: 更改通知已读
	 * type = 1 更改所有，否则，只更改notificationId这一条
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "notification" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateNotification(Integer type, Integer notificationId) {
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			try {
				UserVO user = (UserVO) subject.getSession().getAttribute("user");
				if (null != type && type == 1) {
					notificationService.updateNotification(user.getId(), true, null);
				} else if (null != notificationId) {
					notificationService.updateNotification(user.getId(), true, notificationId);
				}
				map.put("status", "success");
				return map;
			} catch (Exception e) {
			}
		}
		map.put("status", "false");
		return map;
	}

	/**
	 * @Title: deleteNotifications
	 * @Description: 批量删除通知
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "notification" }, method = { RequestMethod.DELETE })
	@ResponseBody
	public Map<String, Object> deleteNotifications(
			@RequestParam(name = "notificationIds[]", required = false) List<Integer> notificationIds) {
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			try {
				// 验证参数notificationIds内的所有值是否都属于当前用户的
				Integer userId = ((UserVO) subject.getSession().getAttribute("user")).getId();
				List<NotificationVO> notifications = notificationService.getNotifications(null, userId, null);
				// 存放合法的notificationIds
				List<Integer> deleteId = new ArrayList<>();
				for (int i = 0; i < notifications.size(); i++) {
					for (int j = 0; j < notificationIds.size(); j++) {
						if (notificationIds.get(j).equals(notifications.get(i).getId())) {
							deleteId.add(notificationIds.get(j));
							continue;
						}
					}
				}
				for (int i = 0; i < deleteId.size(); i++) {
					notificationService.deleteNotification(deleteId.get(i));
				}
				map.put("status", "success");
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("status", "fail");
		return map;
	}

}
