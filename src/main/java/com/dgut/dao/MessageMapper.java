package com.dgut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgut.po.Message;
import com.dgut.po.MessageExample;
import com.dgut.util.Page;
import com.dgut.vo.MessageVO;

public interface MessageMapper {
	int countByExample(MessageExample example);

	int deleteByExample(MessageExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Message record);

	int insertSelective(Message record);

	List<Message> selectByExample(MessageExample example);

	Message selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

	int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKey(Message record);

	/*************************************/
	List<MessageVO> selectMessageVOs(@Param("page") Page page, @Param("userId") Integer userId,
			@Param("receiverId") Integer receiverId);
}