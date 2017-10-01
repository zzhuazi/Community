package com.dgut.service;

import java.util.List;

import com.dgut.po.Message;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.MessageVO;

/**
 * 
 * @ClassName: MessageService
 * @Description: Message业务接口
 * @author LJH
 * @date: 2017年9月25日上午9:40:24
 */
public interface MessageService {

	/**
	 * @Title: getMessages
	 * @Description: 
	 * page为null则不分页查询（注意，跟获取全部是有区别的）
	 * send_time 最新排前规则
	 * 更新userid的chat信息，即置chat.unread 为 0
	 * @param page
	 * @return List<MessageVO>
	 * @throws
	 */
	List<MessageVO> getMessages(Page page, Integer userId, Integer receiverId);

	/**
	 * @Title: addMessage
	 * @Description: 
	 * 添加一条私信，添加失败则抛出ServiceException 及其子类异常
	 * 1.1如果我跟对方的会话不存在，则新建我跟对话的会话，并且unread 为 0
	 * 1.2如果对方跟我的会话不存在，则新建对话跟我的会话，并且unread 为 1
	 * 1.2.1 如果对方与我的会话已存在，则unread + 1
	 * 2.插入我跟对方私信内容，并插入对方与我私信内容
	 * 3.更新我跟对方、对方跟我会话最新内容和时间
	 * @param message void
	 * @throws
	 */
	void addMessage(Message message) throws ServiceException;
}
