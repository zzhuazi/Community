package com.dgut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgut.po.Article;
import com.dgut.po.ArticleExample;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;

public interface ArticleMapper {
	int countByExample(ArticleExample example);

	int deleteByExample(ArticleExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Article record);

	int insertSelective(Article record);

	List<Article> selectByExampleWithBLOBs(ArticleExample example);

	List<Article> selectByExample(ArticleExample example);

	Article selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Article record, @Param("example") ArticleExample example);

	int updateByExampleWithBLOBs(@Param("record") Article record, @Param("example") ArticleExample example);

	int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

	int updateByPrimaryKeySelective(Article record);

	int updateByPrimaryKeyWithBLOBs(Article record);

	int updateByPrimaryKey(Article record);

	/*******************************/
	List<ArticleVO> selectArticleVOs(@Param("page") Page page, @Param("example") ArticleExample example);

	ArticleVO selectArticleVO(Integer articleId);
}