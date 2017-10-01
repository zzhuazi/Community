package com.dgut.service;

import java.util.List;

import com.dgut.po.Section;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.SectionVO;

/**
 * @ClassName: SectionService
 * @Description: 版块业务
 * @author 闲明苑
 * @date 2017年9月29日 下午1:58:33
 */
public interface SectionService {

	/**
	 * @Title: getSections
	 * @Description: 
	 * page为null则不分页查询（注意，跟获取全部是有区别的）
	 * likeSection 为null时，则不进行模糊查询，
	 * 否则，若它属性不为null时，则对某个属性进行模糊查询
	 * orderByLastPublishArticleTime 为true则按最新排前，否则默认数据库排序
	 * @return List<SectionVO>
	 * @throws
	 */
	List<SectionVO> getSections(Page page, Section likeSection, boolean orderByLastPublishArticleTime);

	/**
	 * @Title: getSection
	 * @Description: 
	 * 根据sectionId 获取对应 sectionVO实例
	 * 存在则返回对应实例，否则返回null
	 * @return SectionVO
	 * @throws
	 */
	SectionVO getSection(Integer sectionId);

	/**
	 * @Title: updateSection
	 * @Description: 
	 * 更新不为null属性，更新失败则抛出ServiceException ,其id不能为null
	 * @return void
	 * @throws
	 */
	void updateSection(Section section) throws ServiceException;

	/**
	 * @Title: addSection
	 * @Description: 
	 * 添加失败则返回ServiceException 及它子类异常，否则什么都不发生
	 * @return void
	 * @throws
	 */
	void addSection(Section section) throws ServiceException;

	/**
	 * @Title: delectSection
	 * @Description: 
	 * 删除对应sectionId的记录，并返回删除的记录数
	 * 这是危险操作，系统不实现级联删除该版块下的文章及评论内容
	 * 执行该操作前，需求该版块下的文章及评论内容已清空
	 * @return int
	 * @throws
	 */
	int delectSection(Integer sectionId);
}
