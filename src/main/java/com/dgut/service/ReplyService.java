package com.dgut.service;

import java.util.List;

import com.dgut.po.Reply;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.ReplyVO;

/**
 * @ClassName: ReplyService
 * @Description: 次评论业务
 * @author 闲明苑
 * @date 2017年9月29日 下午1:58:21
 */
public interface ReplyService {

	/**
	 * @Title: getReplies
	 * @Description:
	 * page为null则不分页查询（注意，跟获取全部是有区别的）
	 * likeReply 为null时，则不进行模糊查询，
	 * 否则，若它属性不为null时，则对某个属性进行模糊查询
	 * publish_time 最新排后规则
	 * @param page
	 * @param likeReply
	 * @return List<ReplyVO>
	 * @throws
	 */
	List<ReplyVO> getReplies(Page page, Reply likeReply);

	/**
	 * @Title: addReply
	 * @Description: 
	 * 发表一条次评论，发表失败则抛出ServiceException 及其子类异常
	 * 发表成功，所属的主评论的次评论数+1
	 * @param reply
	 * @throws ServiceException void
	 * @throws
	 */
	void addReply(Reply reply) throws ServiceException;

	/**
	 * @Title: deleteReply
	 * @Description: 删除对应replyId的记录，并返回删除的记录数(级联删除)
	 * @param replyId
	 * @return int
	 * @throws
	 */
	int deleteReply(Integer replyId);

	/**
	 * @Title: getReply
	 * @Description: 根据replyId获取对应实例，没有则返回null
	 * @return ReplyVO
	 * @throws
	 */
	ReplyVO getReply(Integer replyId);
}
