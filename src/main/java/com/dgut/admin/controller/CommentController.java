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

import com.dgut.po.Comment;
import com.dgut.service.CommentService;
import com.dgut.util.Page;
import com.dgut.vo.CommentVO;

/**
 * @ClassName: CommentController
 * @Description: 主评论管理控制器
 * @author 闲明苑
 * @date 2017年9月29日 下午3:54:51
 */
@Controller("adminCommentController")
public class CommentController {

	private int pageSize = 10;

	@Autowired
	private CommentService commentService;

	/**
	 * @Title: getCommentPage
	 * @Description: 获取主评论页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = "admin/comments", method = { RequestMethod.GET })
	public String getCommentPage(@RequestParam(defaultValue = "1") Integer currentpage, Comment comment,
			HttpServletRequest request) {
		try {
			Page page = new Page(pageSize, currentpage, 0);
			List<CommentVO> comments = commentService.getComments(page, comment);
			request.setAttribute("comments", comments);
			int size = commentService.getComments(null, comment).size();
			int total = (size / pageSize + (size % pageSize != 0 ? 1 : 0));
			request.setAttribute("page", currentpage);
			request.setAttribute("total", total);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "admin/comments";
	}

	/**
	 * @Title: deleteComments
	 * @Description: 批量删除评论
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "admin/comments" }, method = { RequestMethod.POST }, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody()
	public Map<String, Object> deleteComments(@RequestParam(value = "commentIds[]") List<Integer> articleIds) {
		Map<String, Object> map = new HashMap<>();
		try {
			for (int i = 0; i < articleIds.size(); i++) {
				commentService.deleteComment(articleIds.get(i), null);
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
