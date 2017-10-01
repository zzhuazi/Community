package com.dgut.service;

import java.util.List;

import com.dgut.po.Article;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;

/**
 * @ClassName: ArticleService
 * @author 闲明苑
 * @date 2017年9月25日 上午9:36:32
 */
public interface ArticleService {

	/**
	 * @Title: getArticles
	 * @Description: 
	 * page为null则不分页查询（注意，跟获取全部是有区别的）
	 * likearticle 为null时，则不进行模糊查询，否则，肯定有一个属性不为null
	 * 否则，若它属性不为null时，则对某个属性进行模糊查询
	 * last_active_time 最新排前规则
	 * @return List<ArticleVO>
	 * @throws
	 */
	List<ArticleVO> getArticles(Page page, Article likeArticle);

	/**
	 * @Title: getArticle
	 * @Description: 
	 * 根据articleId获取对应 articleVo实例
	 * 存在则返回对应实例，否则返回null
	 * @return ArticleVO
	 * @throws
	 */
	ArticleVO getArticle(Integer articleId);

	/**
	 * @Title: addArticle
	 * @Description: 
	 * 添加失败则抛出ServiceException 及其子类异常
	 * @return void
	 * @throws
	 */
	void addArticle(Article article) throws ServiceException;

	/**
	 * @Title: updateArticle
	 * @Description: 
	 * 更新不为null属性，更新失败则抛出ServiceException ,其id不能为null
	 * @return void
	 * @throws
	 */
	void updateArticle(Article article) throws ServiceException;

	/**
	 * @Title: deleteArticle
	 * @Description: 
	 * 删除对应sectionId的记录，并返回删除的记录数
	 * 删除成功后，所属该文章的主次评论连带删除，用户文章数-1、所属版块文章数-1
	 * @return int
	 * @throws
	 */
	int deleteArticle(Integer articleId);

}
