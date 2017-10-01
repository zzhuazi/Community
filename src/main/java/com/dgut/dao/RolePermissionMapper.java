package com.dgut.dao;

import com.dgut.po.RolePermissionExample;
import com.dgut.po.RolePermissionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapper {
    int countByExample(RolePermissionExample example);

    int deleteByExample(RolePermissionExample example);

    int deleteByPrimaryKey(RolePermissionKey key);

    int insert(RolePermissionKey record);

    int insertSelective(RolePermissionKey record);

    List<RolePermissionKey> selectByExample(RolePermissionExample example);

    int updateByExampleSelective(@Param("record") RolePermissionKey record, @Param("example") RolePermissionExample example);

    int updateByExample(@Param("record") RolePermissionKey record, @Param("example") RolePermissionExample example);
}