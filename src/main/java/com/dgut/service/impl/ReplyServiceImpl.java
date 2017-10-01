package com.dgut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.dao.CommentMapper;
import com.dgut.dao.ReplyMapper;
import com.dgut.po.Comment;
import com.dgut.po.Reply;
import com.dgut.po.ReplyExample;
import com.dgut.service.ReplyService;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyMapper replyMapper;
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<ReplyVO> getReplies(Page page, Reply likeReply) {
		// 构造模糊查询条件
		ReplyExample replyExample = new ReplyExample();
		if (null != likeReply) {
			com.dgut.po.ReplyExample.Criteria criteria = replyExample.createCriteria();
			if (null != likeReply.getContent()) {
				criteria.andContentEqualTo(likeReply.getContent());
			}
			if (null != likeReply.getId()) {
				criteria.andIdEqualTo(likeReply.getId());
			}
			if (null != likeReply.getRepliedId()) {
				criteria.andRepliedIdEqualTo(likeReply.getId());
			}
			if (null != likeReply.getUserId()) {
				criteria.andUserIdEqualTo(likeReply.getUserId());
			}
			if (null != likeReply.getCommentId()) {
				criteria.andCommentIdEqualTo(likeReply.getCommentId());
			}
			if (null != likeReply.getPublishTime()) {
				criteria.andPublishTimeEqualTo(likeReply.getPublishTime());
			}
		}
		return replyMapper.selectReplyVOs(page, replyExample);
	}

	@Transactional
	@Override
	public void addReply(Reply reply) throws ServiceException {
		replyMapper.insert(reply);
		Comment comment = commentMapper.selectByPrimaryKey(reply.getCommentId());
		comment.setReplies(comment.getReplies() + 1);
		commentMapper.updateByPrimaryKeySelective(comment);
	}

	@Override
	public int deleteReply(Integer replyId) {
		return replyMapper.deleteByPrimaryKey(replyId);
	}

	@Override
	public ReplyVO getReply(Integer replyId) {
		return replyMapper.selectReplyVO(replyId);
	}

}
