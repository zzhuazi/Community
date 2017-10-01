package com.dgut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.dao.ArticleMapper;
import com.dgut.dao.CommentMapper;
import com.dgut.dao.ReplyMapper;
import com.dgut.po.Article;
import com.dgut.po.Comment;
import com.dgut.po.CommentExample;
import com.dgut.po.CommentExample.Criteria;
import com.dgut.po.ReplyExample;
import com.dgut.service.CommentService;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.CommentVO;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ReplyMapper replyMapper;

	@Override
	public List<CommentVO> getComments(Page page, Comment likeComment) {
		CommentExample example = new CommentExample();
		if (null != likeComment) {
			Criteria criteria = example.createCriteria();
			if (null != likeComment.getArticleId()) {
				criteria.andArticleIdEqualTo(likeComment.getArticleId());
			}
			if (null != likeComment.getId()) {
				criteria.andIdEqualTo(likeComment.getId());
			}
			if (null != likeComment.getReplies()) {
				criteria.andRepliesEqualTo(likeComment.getReplies());
			}
			if (null != likeComment.getUserId()) {
				criteria.andUserIdEqualTo(likeComment.getUserId());
			}
			if (null != likeComment.getContent()) {
				criteria.andContentLike(likeComment.getContent());
			}
			if (null != likeComment.getPublishTime()) {
				criteria.andPublishTimeEqualTo(likeComment.getPublishTime());
			}
		}
		return commentMapper.selectCommentVOs(page, example);
	}

	@Transactional
	@Override
	public void addComment(Comment comment) throws ServiceException {
		// 插入主评论
		commentMapper.insert(comment);
		// article comments+1
		Article article = articleMapper.selectByPrimaryKey(comment.getArticleId());
		article.setComments(article.getComments() + 1);
		articleMapper.updateByPrimaryKeySelective(article);
	}

	@Override
	public int deleteComment(Integer commentId, Integer userId) {
		// 删除该主评论下的次评论
		ReplyExample example = new ReplyExample();
		com.dgut.po.ReplyExample.Criteria criteria = example.createCriteria();
		criteria.andCommentIdEqualTo(commentId);
		criteria.andUserIdEqualTo(userId);
		replyMapper.deleteByExample(example);
		// 删除该主评论
		return commentMapper.deleteByPrimaryKey(commentId);
	}

}
