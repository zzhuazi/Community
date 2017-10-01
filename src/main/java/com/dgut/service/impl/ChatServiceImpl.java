package com.dgut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgut.dao.ChatMapper;
import com.dgut.dao.MessageMapper;
import com.dgut.po.Chat;
import com.dgut.po.ChatExample;
import com.dgut.po.MessageExample;
import com.dgut.po.ChatExample.Criteria;
import com.dgut.service.ChatService;
import com.dgut.util.Page;
import com.dgut.vo.ChatVO;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatMapper chatMapper;
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public List<ChatVO> getChats(Page page, Integer userId) {
		return chatMapper.selectChatVOs(page, userId);
	}

	@Override
	public int deleteChat(Integer chatId) {
		ChatExample chatExample = new ChatExample();
		Criteria chatCriteria = chatExample.createCriteria();
		chatCriteria.andIdEqualTo(chatId);
		List<Chat> chats = chatMapper.selectByExample(chatExample);
		Chat chat = chats.get(0);
		MessageExample messageExample = new MessageExample();
		com.dgut.po.MessageExample.Criteria messageCriteria = messageExample.createCriteria();
		messageCriteria.andUserIdEqualTo(chat.getUserId());
		messageCriteria.andReceiverIdEqualTo(chat.getReceiverId());
		messageMapper.deleteByExample(messageExample );
		return chatMapper.deleteByPrimaryKey(chatId);
	}

	@Override
	public int countUnreadMessage(Integer userId) {
		ChatExample example = new ChatExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<Chat> chats = chatMapper.selectByExample(example);
		int number = 0;
		for (int i = 0; i < chats.size(); i++) {
			number += chats.get(i).getUnread();
		}
		return number;
	}
	
	@Override
	public void updateChat(Integer userId, Integer receiverId) {
		ChatExample example = new ChatExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		criteria.andReceiverIdEqualTo(receiverId);
		List<Chat> chats = chatMapper.selectByExample(example);
		if (chats.size() > 0) {
			chats.get(0).setUnread(0);;
		}
	}
}
