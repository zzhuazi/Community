package com.dgut.dao;

import com.dgut.po.Chat;
import com.dgut.po.ChatExample;
import com.dgut.util.Page;
import com.dgut.vo.ChatVO;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ChatMapper {
    int countByExample(ChatExample example);

    int deleteByExample(ChatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Chat record);

    int insertSelective(Chat record);

    List<Chat> selectByExample(ChatExample example);

    Chat selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Chat record, @Param("example") ChatExample example);

    int updateByExample(@Param("record") Chat record, @Param("example") ChatExample example);

    int updateByPrimaryKeySelective(Chat record);

    int updateByPrimaryKey(Chat record);

    /****************************************/
	List<ChatVO> selectChatVOs(@Param("page")Page page, @Param("userId")Integer userId);
}