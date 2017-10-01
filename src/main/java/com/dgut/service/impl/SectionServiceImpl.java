package com.dgut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgut.dao.SectionMapper;
import com.dgut.po.Section;
import com.dgut.po.SectionExample;
import com.dgut.po.SectionExample.Criteria;
import com.dgut.service.SectionService;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.SectionVO;

@Service
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionMapper sectionMapper;

	@Override
	public List<SectionVO> getSections(Page page, Section likeSection, boolean orderByLastPublishArticleTime) {
		SectionExample example = new SectionExample();
		if (null != likeSection) {
			Criteria criteria = example.createCriteria();
			if (null != likeSection.getArticles()) {
				criteria.andArticlesEqualTo(likeSection.getArticles());
			}
			if (null != likeSection.getId()) {
				criteria.andIdEqualTo(likeSection.getId());
			}
			if (null != likeSection.getAvatar()) {
				criteria.andAvatarLike(likeSection.getAvatar());
			}
			if (null != likeSection.getIntroduce()) {
				criteria.andIntroduceLike(likeSection.getIntroduce());
			}
			if (null != likeSection.getLastPublishArticleTime()) {
				criteria.andLastPublishArticleTimeEqualTo(likeSection.getLastPublishArticleTime());
			}
			if (null != likeSection.getName()) {
				criteria.andNameLike(likeSection.getName());
			}
		}
		return sectionMapper.selectSectionVOs(page, example, orderByLastPublishArticleTime);
	}

	@Override
	public SectionVO getSection(Integer sectionId) {
		Section section = sectionMapper.selectByPrimaryKey(sectionId);
		SectionVO sectionVO = new SectionVO();
		sectionVO.setArticles(section.getArticles());
		sectionVO.setAvatar(section.getAvatar());
		sectionVO.setId(section.getId());
		sectionVO.setIntroduce(section.getIntroduce());
		sectionVO.setLastPublishArticleTime(section.getLastPublishArticleTime());
		sectionVO.setName(section.getName());
		return sectionVO;
	}

	@Override
	public void updateSection(Section section) throws ServiceException {
		sectionMapper.updateByPrimaryKeySelective(section);
	}

	@Override
	public void addSection(Section section) throws ServiceException {
		sectionMapper.insert(section);
	}

	@Override
	public int delectSection(Integer sectionId) {
		return sectionMapper.deleteByPrimaryKey(sectionId);
	}

}
