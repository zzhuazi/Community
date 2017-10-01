package com.dgut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgut.po.Reply;
import com.dgut.po.ReplyExample;
import com.dgut.util.Page;
import com.dgut.vo.ReplyVO;

public interface ReplyMapper {
	int countByExample(ReplyExample example);

	int deleteByExample(ReplyExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Reply record);

	int insertSelective(Reply record);

	List<Reply> selectByExample(ReplyExample example);

	Reply selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Reply record, @Param("example") ReplyExample example);

	int updateByExample(@Param("record") Reply record, @Param("example") ReplyExample example);

	int updateByPrimaryKeySelective(Reply record);

	int updateByPrimaryKey(Reply record);

	/***************************************************/
	List<ReplyVO> selectReplyVOs(@Param("page") Page page, @Param("example") ReplyExample example);

	ReplyVO selectReplyVO(Integer replyId);
}