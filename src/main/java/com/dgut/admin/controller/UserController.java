package com.dgut.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.po.User;
import com.dgut.service.UserService;
import com.dgut.util.Page;
import com.dgut.vo.UserVO;

/**
 * @ClassName: UserController
 * @Description: 用户管理控制器
 * @author 闲明苑
 * @date 2017年9月29日 下午1:54:28
 */
@Controller("adminUserController")
public class UserController {

	private int pageSize = 10;

	@Autowired
	private UserService userService;

	/**
	 * @Title: getUsersPage
	 * @Description: 获取用户管理页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "admin/users" }, method = { RequestMethod.GET })
	public String getUsersPage(@RequestParam(defaultValue = "1") Integer currentpage, User user,
			HttpServletRequest request) {
		Page page = new Page(pageSize, currentpage, 0);
		try {
			if (null != user.getRoleId() && user.getRoleId() == 4) {
				user.setRoleId(null);
			}
			List<UserVO> users = userService.getUsers(page, user);
			request.setAttribute("users", users);
			request.setAttribute("page", currentpage);
			int size = userService.getUsers(null, user).size();
			int total = (size / pageSize + (size % pageSize != 0 ? 1 : 0));
			request.setAttribute("total", total);
			request.setAttribute("user", user);
		} catch (Exception e) {
		}
		return "admin/users";
	}

	/**
	 * @Title: updateUsers
	 * @Description: 用户管理操作
	 * type 1 未分配角色用户全部设为普通用户; 2 userIds对应的用户全部设为 普通用户; 3 userIds对应的用户全部设为普通管理员
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "admin/users" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> updateUsers(Integer type,
			@RequestParam(name = "userIds[]", required = false) List<Integer> userIds) {
		Map<String, Object> map = new HashMap<>();
		try {
			switch (type) {
			case 2:
				for (int i = 0; i < userIds.size(); i++) {
					UserVO user = userService.getUser(userIds.get(i));
					user.setRoleId(3);
					userService.updateUser(user);
				}
				break;
			case 3:
				for (int i = 0; i < userIds.size(); i++) {
					UserVO user = userService.getUser(userIds.get(i));
					user.setRoleId(2);
					userService.updateUser(user);
				}
				break;
			default:
				List<UserVO> users = userService.getUsers(null, null);
				for (int i = 0; i < users.size(); i++) {
					User user = users.get(i);
					if (null == user.getRoleId()) {
						user.setRoleId(3);
						userService.updateUser(user);
					}
				}
				break;
			}
			map.put("status", "success");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "fail");
		return map;
	}
}
