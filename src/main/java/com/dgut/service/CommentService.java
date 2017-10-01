package com.dgut.service;

import java.util.List;

import com.dgut.po.Comment;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.CommentVO;

/**
 * @ClassName: CommentService
 * @author 闲明苑
 * @date 2017年9月25日 上午9:36:23
 */
public interface CommentService {

	/**
	 * @Title: getComments
	 * @Description: 
	 * page为null则不分页查询（注意，跟获取全部是有区别的）
	 * likeComment 为null时，则不进行模糊查询
	 * 否则，若它属性不为null时，则对某个属性进行模糊查询
	 * publish_time 最新排前规则
	 * @return List<CommentVO>
	 * @throws
	 */
	List<CommentVO> getComments(Page page, Comment likeComment);

	/**
	 * @Title: addComment
	 * @Description: 
	 * 发表一条主评论，发表失败则抛出CommentException 及其子类异常
	 * @return void
	 * @throws
	 */
	void addComment(Comment comment) throws ServiceException;

	/**
	 * @Title: deleteComment
	 * @Description: 
	 * 删除对应commentId的记录，并返回删除的记录数(级联删除)
	 * @return int
	 * @throws
	 */
	int deleteComment(Integer commentId, Integer userId);
}
