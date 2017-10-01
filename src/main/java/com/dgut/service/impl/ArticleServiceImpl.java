package com.dgut.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.dao.ArticleMapper;
import com.dgut.dao.CommentMapper;
import com.dgut.dao.ReplyMapper;
import com.dgut.dao.SectionMapper;
import com.dgut.dao.UserMapper;
import com.dgut.po.Article;
import com.dgut.po.ArticleExample;
import com.dgut.po.ArticleExample.Criteria;
import com.dgut.po.Comment;
import com.dgut.po.CommentExample;
import com.dgut.po.ReplyExample;
import com.dgut.po.Section;
import com.dgut.po.User;
import com.dgut.service.ArticleService;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private SectionMapper sectionMapper;

	@Override
	public List<ArticleVO> getArticles(Page page, Article likeArticle) {
		// 构造模糊查询条件
		ArticleExample example = new ArticleExample();
		if (null != likeArticle) {
			Criteria criteria = example.createCriteria();
			if (null != likeArticle.getComments()) {
				criteria.andCommentsEqualTo(likeArticle.getComments());
			}
			if (null != likeArticle.getId()) {
				criteria.andIdEqualTo(likeArticle.getId());
			}
			if (null != likeArticle.getSectionId()) {
				criteria.andSectionIdEqualTo(likeArticle.getSectionId());
			}
			if (null != likeArticle.getStatus()) {
				criteria.andStatusEqualTo(likeArticle.getStatus());
			}
			if (null != likeArticle.getUserId()) {
				criteria.andUserIdEqualTo(likeArticle.getUserId());
			}
			if (null != likeArticle.getContent()) {
			}
			if (null != likeArticle.getLastActiveTime()) {
				criteria.andLastActiveTimeEqualTo(likeArticle.getLastActiveTime());
			}
			if (null != likeArticle.getPublishTime()) {
				criteria.andPublishTimeEqualTo(likeArticle.getPublishTime());
			}
			if (null != likeArticle.getTitle()) {
				criteria.andTitleLike(likeArticle.getTitle());
			}
		}
		return articleMapper.selectArticleVOs(page, example);
	}

	@Override
	public ArticleVO getArticle(Integer articleId) {
		return articleMapper.selectArticleVO(articleId);
	}

	@Transactional
	@Override
	public void addArticle(Article article) throws ServiceException {
		// 插入文章记录
		articleMapper.insert(article);
	}

	@Override
	public void updateArticle(Article article) throws ServiceException {
		articleMapper.updateByPrimaryKeySelective(article);
	}

	@Transactional
	@Override
	public int deleteArticle(Integer articleId) {
		Article article = articleMapper.selectByPrimaryKey(articleId);
		// 获取该文章的所有主评论内容
		CommentExample example = new CommentExample();
		com.dgut.po.CommentExample.Criteria criteria = example.createCriteria();
		criteria.andArticleIdEqualTo(articleId);
		List<Comment> comments = commentMapper.selectByExample(example);
		List<Integer> values = new ArrayList<>(comments.size());

		for (int i = 0; i < comments.size(); i++) {
			values.set(i, comments.get(i).getId());
		}

		ReplyExample rExample = new ReplyExample();
		com.dgut.po.ReplyExample.Criteria rCriteria = rExample.createCriteria();
		// 先删除所有次评论
		rCriteria.andCommentIdIn(values);
		replyMapper.deleteByExample(rExample);
		// 再删除所有主评论
		commentMapper.deleteByExample(example);
		// 再更新用户文章数
		User record = userMapper.selectByPrimaryKey(article.getUserId());
		record.setArticles(record.getArticles() - 1);
		userMapper.updateByPrimaryKeySelective(record);
		// 更新版块文章数
		Section section = sectionMapper.selectByPrimaryKey(article.getSectionId());
		section.setArticles(section.getArticles() - 1);
		sectionMapper.updateByPrimaryKeySelective(section);
		// 然后删除文章
		return articleMapper.deleteByPrimaryKey(articleId);
	}

}
