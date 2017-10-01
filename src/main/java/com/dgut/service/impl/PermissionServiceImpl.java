package com.dgut.service.impl;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgut.dao.PermissionMapper;
import com.dgut.dao.RolePermissionMapper;
import com.dgut.po.RolePermissionExample;
import com.dgut.po.RolePermissionExample.Criteria;
import com.dgut.po.RolePermissionKey;
import com.dgut.service.PermissionService;
import com.dgut.vo.PermissionVO;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;
	@Autowired
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public Set<PermissionVO> getPermissionsBy(Integer roleId) {
		// 获取所有权限
		Set<PermissionVO> all = permissionMapper.selectAll();
		// 获取当前角色已拥有的权限
		Set<PermissionVO> hasPermissions = permissionMapper.selectByRoleId(roleId);
		// 并集
		all.addAll(hasPermissions);
		Iterator<PermissionVO> iterator = all.iterator();
		while (iterator.hasNext()) {
			PermissionVO permission = iterator.next();
			if (hasPermissions.contains(permission)) {
				// 如果该权限该角色拥有，则置possess为true
				permission.setPossess(true);
			}
		}
		return all;
	}

	@Override
	public void update(Integer roleId, Integer[] permissionIds) {
		RolePermissionExample example = new RolePermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andRoleIdEqualTo(roleId);
		rolePermissionMapper.deleteByExample(example);
		for (int i = 0; i < permissionIds.length; i++) {
			RolePermissionKey key = new RolePermissionKey();
			key.setRoleId(roleId);
			key.setPermissionId(permissionIds[i]);
			rolePermissionMapper.insert(key);
		}
	}

}
