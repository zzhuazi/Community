package com.dgut.controller;

import static org.assertj.core.api.Assertions.linesOf;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.po.Message;
import com.dgut.po.User;
import com.dgut.service.ChatService;
import com.dgut.service.MessageService;
import com.dgut.util.Page;
import com.dgut.vo.MessageVO;
import com.dgut.vo.UserVO;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private ChatService chatService;
	/**
	 * @Title: message
	 * @Description:  提交私信表单信息，发送私信
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "message" }, method = { RequestMethod.POST })
	public String message(String message, Integer receiverId) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try{
			UserVO user = (UserVO) session.getAttribute("user");
			int userId = user.getId();
			Message message2 = new Message(null, message, new Date(), userId, receiverId, userId);
			messageService.addMessage(message2);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/messages?receiverId="+ receiverId;
	}

	/**
	 * @Title: getMessagesPage
	 * @Description: 获取一页私信数据页面(更多),未实现分页
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "messages" }, method = { RequestMethod.GET })
	public String getMessagesPage(Integer receiverId, HttpServletRequest request) {
		// 获取一页私信数据
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		try {
			UserVO user =  (UserVO) session.getAttribute("user");
			int userId = user.getId();
			chatService.updateChat(userId, receiverId);
			Page page = new Page(15, 1, 0);
			List<MessageVO> messages = messageService.getMessages(page, userId, receiverId);
			request.setAttribute("userVO", user);
			request.setAttribute("messageVOs", messages);
			return "message";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "message";
	}

	/**
	 * @Title: getMessages
	 * @Description: 获取一页私信数据，以json格式返回
	 * @param currentPage
	 * @param receiverId
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = {"messages"}, method = {RequestMethod.POST})
	@ResponseBody
	public Map<String, Object> getMessages(Integer currentPage, Integer receiverId){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> map = new HashMap<>();
		try {
			UserVO user = (UserVO) session.getAttribute("user");
			int userId = user.getId();
			Page page = new Page(15, currentPage, 0);
			List<MessageVO> messages = messageService.getMessages(page, userId, receiverId);
			map.put("stauts", "success");
			map.put("messageVOs", messages);
			map.put("page", messages.size() >= 15 ? currentPage:0);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("stauts", "fail");
		return map;
	}
}
