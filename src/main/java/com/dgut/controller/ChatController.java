package com.dgut.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.service.ChatService;
import com.dgut.util.Page;
import com.dgut.vo.ChatVO;
import com.dgut.vo.UserVO;

@Controller
public class ChatController {

	@Autowired
	private ChatService chatService;

	/**
	 * @Title: getChatsPage
	 * @Description: 获取一页私信会话列表页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "chats" }, method = { RequestMethod.GET })
	public String getChatsPage(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();// 获得session
		try {
			UserVO user = (UserVO) session.getAttribute("user");
			int userId = user.getId();
			Page page = new Page(15, 1, 0);
			List<ChatVO> chatVOs = chatService.getChats(page, userId);
			request.setAttribute("chatVOs", chatVOs);
			return "chat";
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 没有登录时重定向到Login页面
		return "redirect:login?url=/chats";
	}

	/**
	 * @Title: getChats
	 * @Description: 获得一页私信列表数据，以json格式返回
	 * @param currentPage
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "chats" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getChats(Integer currentPage) {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Map<String, Object> map = new HashMap<>();
		try {
			UserVO user = (UserVO) session.getAttribute("user");
			int userId = user.getId();
			Page page = new Page(15, currentPage, 0);
			List<ChatVO> chatVOs = chatService.getChats(page, userId);
			System.out.println("chatVOs::::::::::::" + chatVOs.size());
			map.put("stauts", "success");
			map.put("chatVOs", chatVOs);
			map.put("page", chatVOs.size() >= 5 ? currentPage : 0);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("stauts", "fail");
		return map;
	}

	@RequestMapping(path = { "deleteChat" }, method = { RequestMethod.GET })
	public String deleteChat(Integer chatId) {
		chatService.deleteChat(chatId);
		return "redirect:chats";
	}
}
