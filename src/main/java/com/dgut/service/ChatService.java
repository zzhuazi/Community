package com.dgut.service;

import java.util.List;

import com.dgut.util.Page;
import com.dgut.vo.ChatVO;

/**
 * @ClassName: ChatService
 * @Description: 会话业务
 * @author 闲明苑
 * @date 2017年9月29日 下午1:57:50
 */
public interface ChatService {

	/**
	 * @Title: getChats
	 * @Description: 
	 * page为null则不分页查询（注意，跟获取全部是有区别的）
	 * last_active_time 最新排前规则
	 * @param page
	 * @param userId
	 * @return List<ChatVO>
	 * @throws
	 */
	List<ChatVO> getChats(Page page, Integer userId);

	/**
	 * @Title: deleteChat
	 * @Description: 
	 * 删除对应chatId和userid相符的记录，同时级联删除对应的Messages，并返回删除的记录数
	 * @param chatId
	 * @return int
	 * @throws
	 */
	int deleteChat(Integer chatId);

	/**
	 * @Title: countUnreadMessage
	 * @Description: 
	 * 获取userId对应的用户未读私信数量
	 * @param userId
	 * @return int
	 * @throws
	 */
	int countUnreadMessage(Integer userId);

	/**
	 * @Title: updateChat
	 * @Description: 根据userId,和receiverId得到chat，再将chat中的unread置为0
	 * @param uesrId
	 * @param receiverId
	 * @return
	 * @throws
	 */
	void updateChat(Integer userId, Integer receiverId);
}
