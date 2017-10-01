package com.dgut.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.po.Comment;
import com.dgut.po.Notification;
import com.dgut.po.User;
import com.dgut.service.ArticleService;
import com.dgut.service.CommentService;
import com.dgut.service.NotificationService;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;
import com.dgut.vo.CommentVO;
import com.dgut.vo.UserVO;

/**
 * @ClassName: CommentController
 * @Description: 主评论相关控制器
 * @author 闲明苑
 * @date 2017年9月29日 下午1:55:31
 */
@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private NotificationService notificationService;

	/**
	 * @Title: getComments
	 * @Description: 获取一页主评论数据
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "comments" }, method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getComments(Integer currentpage, Integer articleId) {
		System.out.println("currentpage : " + currentpage);
		System.out.println("articleId : " + articleId);
		Map<String, Object> map = new HashMap<>();
		try {
			Page page = new Page(15, currentpage, 0);
			Comment comment = new Comment();
			comment.setArticleId(articleId);
			List<CommentVO> comments = commentService.getComments(page, comment);
			map.put("status", "success");
			map.put("comments", comments);

			map.put("page", comments.size() >= 15 ? currentpage : 0);
			return map;
		} catch (Exception e) {
			// TODO: handle exception
		}
		map.put("status", "fail");
		return map;
	}

	/**
	 * @Title: comment
	 * @Description: 发表主评论
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "comment" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> comment(Comment comment) {
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			try {
				UserVO user = (UserVO) subject.getSession().getAttribute("user");
				comment.setPublishTime(new Date());
				comment.setUserId(user.getId());
				comment.setReplies(0);
				commentService.addComment(comment);
				// 发送通知给文章作者
				try {
					ArticleVO article = articleService.getArticle(comment.getArticleId());
					if (!article.getAuthor().getId().equals(user.getId())) {
						// 不是评论自己的文章
						StringBuilder message = new StringBuilder();
						message.append("<a href='/homepage?userId=").append(article.getAuthor().getId()).append("'>")
								.append(article.getAuthor().getName()).append("</a>")
								.append("评论了您的文章，标题为：<a href='article?articleId=").append(article.getId()).append("'>")
								.append(article.getTitle()).append("</a>");
						Notification notification = new Notification(null, message.toString(), "", new Date(), false,
								article.getAuthor().getId(), article.getAuthor().getId());
						notificationService.addNotification(notification);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				map.put("status", "success");
				map.put("comment", comment);
				return map;
			} catch (Exception e) {
			}
		}
		map.put("status", "fail");
		return map;
	}

	/**
	 * @Title: deleteComment
	 * @Description: 当前用户删除自己的某一条主评论
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "comment" }, method = { RequestMethod.DELETE })
	@ResponseBody
	public Map<String, Object> deleteComment(Integer commentId) {
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			try {
				Integer userId = ((User) subject.getSession().getAttribute("user")).getId();
				commentService.deleteComment(commentId, userId);
				map.put("status", "success");
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("status", "fail");
		return map;
	}

	/**
	 * @Title: getCommentsPage
	 * @Description: 获得一页主评论数据（在个人主页中显示）
	 * @param currentpage
	 * @param userId
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "commentsPage" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getCommentsPage(@RequestParam(defaultValue = "1") Integer currentpage, Integer userId) {
		Map<String, Object> map = new HashMap<>();
		try {
			Page page = new Page(15, currentpage, 0);
			Comment comment = new Comment();
			comment.setUserId(userId);
			System.out.println("page：：：" + page.toString());
			System.out.println("comment:::" + comment.getUserId());
			System.out.println(commentService.getComments(page, comment));
			List<CommentVO> comments = commentService.getComments(page, comment);
			map.put("status", "success");
			map.put("comments", comments);
			map.put("page", comments.size() >= 15 ? currentpage : 0);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "fail");
		return map;
	}
}
