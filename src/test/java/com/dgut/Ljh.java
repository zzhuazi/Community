package com.dgut;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dgut.controller.ChatController;
import com.dgut.controller.CommentController;
import com.dgut.dao.ChatMapper;
import com.dgut.dao.MessageMapper;
import com.dgut.dao.NotificationMapper;
import com.dgut.dao.ReplyMapper;
import com.dgut.dao.RoleMapper;
import com.dgut.dao.UserMapper;
import com.dgut.po.Reply;
import com.dgut.service.ChatService;
import com.dgut.service.CommentService;
import com.dgut.service.MessageService;
import com.dgut.service.NotificationService;
import com.dgut.service.ReplyService;
import com.dgut.util.Page;
import com.dgut.vo.ChatVO;
import com.dgut.vo.MessageVO;
import com.dgut.vo.NotificationVO;
import com.dgut.vo.ReplyVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Ljh {

	private static final Logger log = LoggerFactory.getLogger(Ljh.class);

	@Autowired
	UserMapper userMapper;
	@Autowired
	MessageMapper messageMapper;
	@Autowired
	MessageService messageService;
	@Autowired
	ChatMapper chatMapper;
	@Autowired
	ChatService chatService;
	@Autowired
	NotificationMapper notificationMapper;
	@Autowired
	NotificationService notificationService;
	@Autowired
	ReplyMapper replyMapper;
	@Autowired
	ReplyService replyService;
	@Autowired
	RoleMapper roleMapper;

	@Test
	public void Message() {
		// Message record = new Message(null, "haha", new Date(), 1, 1);
		// messageMapper.insert(record );
		Page page = new Page(10, 1, 0);
		List<MessageVO> messages = messageService.getMessages(null, 4, 1);
		for (int i = 0; i < messages.size(); i++) {
			MessageVO message = messages.get(i);
			System.out.println(message.getContent());
			System.out.println(message.getReceiver().getName());
		}
		// messageService.addMessage(record);
	}

	@Test
	public void Chat() {
		// com.dgut.po.Chat chat = new com.dgut.po.Chat(null, 2, "haha", new
		// Date(), 1, 1, 1);
		// chatMapper.insert(chat);
		// Page page = new Page(10, 1, 0);
		ChatController chatController = new ChatController();
		List<ChatVO> chats = chatService.getChats(null, 4);
		for (int i = 0; i < chats.size(); i++) {
			ChatVO chat = chats.get(i);
			System.out.println("userId:::::" + chat.getUserId());
			System.out.println("receiverId" + chat.getReceiverId());
			System.out.println("receiver.Id:::::::" + chat.getReceiver().getId());
		}
		// System.out.println(chatService.countUnreadMessage(1));//计算用户Id=1的未读数量
		// com.dgut.po.Chat chat = new com.dgut.po.Chat(null, 2, "aaa", new
		// Date(), 1, 1, 1);
		// chatService.updateChat(chat);
		// chatService.addChat(chat);
		// chatService.deleteChat(2);
	}

	@Test
	public void Notification() {
		// Notification notification = new com.dgut.po.Notification(null, "新通知",
		// "www.baidu.com", new Date(), false, 1, 1);
		// notificationMapper.insert(notification);

		Page page = new Page(10, 1, 0);
		List<NotificationVO> notifications = notificationService.getNotifications(page, 1, null);
		for (int i = 0; i < notifications.size(); i++) {
			NotificationVO notification1 = notifications.get(i);
			System.out.println(notification1.getUrl());
			System.out.println(notification1.getSender().getName());
		}

		// Notification notification = new com.dgut.po.Notification(null, "新通知",
		// "www.baidu.com", new Date(), true, 1, 1);
		// notificationMapper.insert(notification);
		// notificationService.addNotification(notification);
		// System.out.println(notificationService.countUnreadNotification(1));
	}

	@Test
	public void Reply() {
		Reply reply = new com.dgut.po.Reply(null, "新回复", new Date(), null, null, null);
		Page page = new Page(10, 1, 0);
		List<ReplyVO> replies = replyService.getReplies(page, null);
		for (int i = 0; i < replies.size(); i++) {
			ReplyVO replyVO = replies.get(i);
			System.out.println(replyVO.getContent());
			System.out.println(replyVO.getAuthor().getEmail());
		}
	}
	
	@Autowired
	private CommentService commentService;
	@Test
	public void Comment(){
		CommentController commentController = new CommentController();
		Map<String, Object> commentsPage = commentController.getCommentsPage(1, 3);
		System.out.println(commentsPage);
	}

}
