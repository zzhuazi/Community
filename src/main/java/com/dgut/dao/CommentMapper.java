package com.dgut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgut.po.Comment;
import com.dgut.po.CommentExample;
import com.dgut.util.Page;
import com.dgut.vo.CommentVO;

public interface CommentMapper {
	int countByExample(CommentExample example);

	int deleteByExample(CommentExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Comment record);

	int insertSelective(Comment record);

	List<Comment> selectByExample(CommentExample example);

	Comment selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

	int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

	int updateByPrimaryKeySelective(Comment record);

	int updateByPrimaryKey(Comment record);

	/**************************************/
	List<CommentVO> selectCommentVOs(@Param("page") Page page, @Param("example") CommentExample example);
}