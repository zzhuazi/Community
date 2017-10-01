package com.dgut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgut.dao.ChatMapper;
import com.dgut.dao.MessageMapper;
import com.dgut.po.Chat;
import com.dgut.po.ChatExample;
import com.dgut.po.ChatExample.Criteria;
import com.dgut.po.Message;
import com.dgut.service.MessageService;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.MessageVO;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private ChatMapper chatMapper;
	@Autowired
	private MessageMapper messageMapper;

	@Transactional
	@Override
	public List<MessageVO> getMessages(Page page, Integer userId, Integer receiverId) {
		// 更新chat.userId = userId的unread为0
		ChatExample example = new ChatExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andReceiverIdEqualTo(receiverId);
		List<Chat> chats = chatMapper.selectByExample(example);
		chats.get(0).setUnread(0);
		chatMapper.updateByPrimaryKeySelective(chats.get(0));

		return messageMapper.selectMessageVOs(page, userId, receiverId);
	}

	@Transactional
	@Override
	public void addMessage(Message message) throws ServiceException {
		Integer userId = message.getUserId();
		Integer receiverId = message.getReceiverId();
		ChatExample example = new ChatExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andReceiverIdEqualTo(receiverId);
		List<Chat> chats = chatMapper.selectByExample(example);
		// 更新我与对方会话内容
		if (chats.size() > 0) {
			// 我与对方会话存在
			Chat chat = chatMapper.selectByPrimaryKey(chats.get(0).getId());
			chat.setLastActiveContent(message.getContent());
			chat.setLastActiveTime(message.getSendTime());
			chat.setMessages(chat.getMessages() + 1);
			chat.setUnread(0);
			chatMapper.updateByPrimaryKeySelective(chat);
		} else {
			// 我与对方会话不存在
			Chat chat = new Chat();
			chat.setUserId(userId);
			chat.setReceiverId(receiverId);
			chat.setMessages(1);
			chat.setLastActiveContent(message.getContent());
			chat.setLastActiveTime(message.getSendTime());
			chat.setUnread(0);
			chatMapper.insert(chat);
		}

		example.clear();
		criteria = null;
		criteria = example.createCriteria();
		criteria.andUserIdEqualTo(receiverId);
		criteria.andReceiverIdEqualTo(userId);
		chats = null;
		chats = chatMapper.selectByExample(example);
		// 更新对方与我会话内容
		if (null != chats && chats.size() > 0) {
			// 对方与我的会话存在
			Chat chat = chats.get(0);
			chat.setLastActiveContent(message.getContent());
			chat.setLastActiveTime(message.getSendTime());
			chat.setMessages(chat.getMessages() + 1);
			chat.setUnread(chat.getUnread() + 1);
			chatMapper.updateByPrimaryKeySelective(chat);
		} else {
			// 对方与我的会话不存在
			Chat chat = new Chat();
			chat.setUserId(receiverId);
			chat.setReceiverId(userId);
			chat.setLastActiveContent(message.getContent());
			chat.setLastActiveTime(message.getSendTime());
			chat.setMessages(1);
			chat.setUnread(1);
			chatMapper.insert(chat);
		}
		// 为双方各自插入一条私信内容
		messageMapper.insert(message);
		message.setId(null);
		message.setUserId(receiverId);
		message.setReceiverId(userId);
		messageMapper.insertSelective(message);
	}

}
