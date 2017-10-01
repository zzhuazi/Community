package com.dgut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgut.po.Section;
import com.dgut.po.SectionExample;
import com.dgut.util.Page;
import com.dgut.vo.SectionVO;

public interface SectionMapper {
	int countByExample(SectionExample example);

	int deleteByExample(SectionExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Section record);

	int insertSelective(Section record);

	List<Section> selectByExample(SectionExample example);

	Section selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Section record, @Param("example") SectionExample example);

	int updateByExample(@Param("record") Section record, @Param("example") SectionExample example);

	int updateByPrimaryKeySelective(Section record);

	int updateByPrimaryKey(Section record);

	/***************************************/
	List<SectionVO> selectSectionVOs(@Param("page") Page page, @Param("example") SectionExample example,
			@Param("time") Boolean orderByLastPublishArticleTime);
}