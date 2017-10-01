package com.dgut.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.po.Reply;
import com.dgut.service.ReplyService;
import com.dgut.util.Page;
import com.dgut.vo.ReplyVO;

@Controller("adminReplyController")
public class ReplyController {

	private int pageSize = 10;
	@Autowired
	private ReplyService replyService;

	/**
	 * @Title: getRepliesPage
	 * @Description: 获取主评论页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = "admin/replies", method = { RequestMethod.GET })
	public String getRepliesPage(@RequestParam(defaultValue = "1") Integer currentpage, Reply reply,
			HttpServletRequest request) {
		try {
			Page page = new Page(pageSize, currentpage, 0);
			List<ReplyVO> replies = replyService.getReplies(page, reply);
			request.setAttribute("replies", replies);
			int size = replyService.getReplies(null, reply).size();
			int total = (size / pageSize + (size % pageSize != 0 ? 1 : 0));
			request.setAttribute("page", currentpage);
			request.setAttribute("total", total);
		} catch (Exception e) {
		}
		return "admin/replies";
	}

	/**
	 * @Title: deleteReplies
	 * @Description: 批量删除评论
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "admin/replies" }, method = { RequestMethod.POST }, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody()
	public Map<String, Object> deleteReplies(@RequestParam(value = "replyIds[]") List<Integer> replyIds) {
		Map<String, Object> map = new HashMap<>();
		try {
			for (int i = 0; i < replyIds.size(); i++) {
				replyService.deleteReply(replyIds.get(i));
			}
			map.put("status", "success");
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "fail");
		return map;
	}

}
