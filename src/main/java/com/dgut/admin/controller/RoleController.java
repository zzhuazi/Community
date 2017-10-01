package com.dgut.admin.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgut.service.PermissionService;
import com.dgut.vo.PermissionVO;

@Controller
public class RoleController {

	@Autowired
	private PermissionService permissionService;

	/**
	 * @Title: getRolePermissionPage
	 * @Description: 获取角色权限页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "admin/roles" }, method = { RequestMethod.GET })
	public String getRolePermissionPage(HttpServletRequest request) {
		try {
			// 获取普通管理员的权限
			Set<PermissionVO> admin = permissionService.getPermissionsBy(2);
			// 获取普通用户的权限
			Set<PermissionVO> user = permissionService.getPermissionsBy(3);
			request.setAttribute("admin", admin);
			request.setAttribute("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/roles";
	}

	/**
	 * @Title: updateRolePermissions
	 * @Description: 更新角色的权限
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "admin/permission" }, method = { RequestMethod.POST })
	public String updateRolePermissions(Integer roleId, Integer[] permissionIds) {
		try {
			permissionService.update(roleId, permissionIds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/roles";
	}

}
