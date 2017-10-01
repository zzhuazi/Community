package com.dgut.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgut.po.User;
import com.dgut.service.UserService;
import com.dgut.util.MD5Util;
import com.dgut.vo.UserVO;

/**
 * @ClassName: SettingController
 * @author 闲明苑
 * @date 2017年9月30日 下午1:11:47
 */
@Controller
public class SettingController {

	@Autowired
	private UserService userService;

	/**
	 * @Title: getResetPasswordPage
	 * @Description: 获取修改密码页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "password" }, method = { RequestMethod.GET })
	public String getResetPasswordPage() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return "redirect:/login";
		}
		return "reset_password";
	}

	/**
	 * @Title: updatePassword
	 * @Description: 提交修改密码表单更新密码
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "password" }, method = { RequestMethod.POST })
	public String updatePassword(String oldPassword, String newPassword) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			try {
				String email = ((UserVO) subject.getSession().getAttribute("user")).getEmail();
				User user = new User();
				user.setEmail(email);
				user.setPassword(MD5Util.encrypt(oldPassword));
				List<UserVO> users = userService.getUsers(null, user);
				if (users.size() > 0) {
					users.get(0).setPassword(MD5Util.encrypt(newPassword));
					userService.updateUser(users.get(0));
					subject.logout();
					return "redirect:/login";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/password";
		}
		return "redirect:/login";
	}

}
