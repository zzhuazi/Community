package com.dgut.service;

import java.util.Set;

import com.dgut.vo.PermissionVO;

public interface PermissionService {

	/**
	 * @Title: getPermissionsBy
	 * @Description: 根据roleId获取该所有权限，该角色拥有的角色，则prosess为true
	 * @return List<PermissionVO>
	 * @throws
	 */
	Set<PermissionVO> getPermissionsBy(Integer roleId);

	/**
	 * @Title: update
	 * @Description: 更新角色的权限
	 * 先清除原有权限，再插入新的权限
	 * @return void
	 * @throws
	 */
	void update(Integer roleId, Integer[] permissionIds);

}
