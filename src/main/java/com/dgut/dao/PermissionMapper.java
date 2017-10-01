package com.dgut.dao;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.dgut.po.Permission;
import com.dgut.po.PermissionExample;
import com.dgut.vo.PermissionVO;

public interface PermissionMapper {
	int countByExample(PermissionExample example);

	int deleteByExample(PermissionExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Permission record);

	int insertSelective(Permission record);

	List<Permission> selectByExample(PermissionExample example);

	Permission selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

	int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

	int updateByPrimaryKeySelective(Permission record);

	int updateByPrimaryKey(Permission record);

	/*******************************/
	Set<PermissionVO> selectByRoleId(Integer roleId);

	Set<PermissionVO> selectAll();
}