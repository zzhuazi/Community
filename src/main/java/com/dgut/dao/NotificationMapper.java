package com.dgut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgut.po.Notification;
import com.dgut.po.NotificationExample;
import com.dgut.util.Page;
import com.dgut.vo.NotificationVO;

public interface NotificationMapper {
	int countByExample(NotificationExample example);

	int deleteByExample(NotificationExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Notification record);

	int insertSelective(Notification record);

	List<Notification> selectByExample(NotificationExample example);

	Notification selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Notification record, @Param("example") NotificationExample example);

	int updateByExample(@Param("record") Notification record, @Param("example") NotificationExample example);

	int updateByPrimaryKeySelective(Notification record);

	int updateByPrimaryKey(Notification record);

	/*****************************************************************/
	List<NotificationVO> selectNotificationVOs(@Param("page") Page page, @Param("userId") Integer userId,
			@Param("isReaded") Boolean isReaded);
}