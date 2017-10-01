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
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.po.Comment;
import com.dgut.po.Notification;
import com.dgut.po.Reply;
import com.dgut.po.User;
import com.dgut.service.ArticleService;
import com.dgut.service.CommentService;
import com.dgut.service.NotificationService;
import com.dgut.service.ReplyService;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;
import com.dgut.vo.ReplyVO;
import com.dgut.vo.UserVO;

/**
 * @ClassName: ReplyController
 * @Description: 次评论相关控制器
 * @author 闲明苑
 * @date 2017年9月29日 下午1:56:03
 */
@Controller
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private NotificationService notificationService;

	/**
	 * @Title: getReplies
	 * @Description: 获取一页次评论数据
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "replies" }, method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getReplies(Integer commentId, Integer currentpage) {
		Map<String, Object> map = new HashMap<>();
		try {
			Reply reply = new Reply();
			reply.setCommentId(commentId);
			Page page = new Page(10, currentpage, 0);
			List<ReplyVO> replies = replyService.getReplies(page, reply);
			map.put("status", "success");
			map.put("replies", replies);
			map.put("page", replies.size() == 10 ? currentpage + 1 : 0);
			return map;
		} catch (Exception e) {
		}
		map.put("status", "fail");
		return map;
	}

	/**
	 * @Title: reply
	 * @Description: 发表次评论
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "reply" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> reply(Reply reply) {
		Map<String, Object> map = new HashMap<>();
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			try {
				UserVO user = (UserVO) subject.getSession().getAttribute("user");

				// 被回复的评论id
				Integer repliedId = null;
				// 被回复的用户
				User repliedUser = null;
				if (null != reply.getRepliedId()) {
					// 被回复的评论
					ReplyVO r = replyService.getReply(reply.getRepliedId());
					if (!r.getAuthor().getId().equals(user.getId())) {
						repliedId = r.getId();
						repliedUser = r.getAuthor();
					}
				}
				reply.setRepliedId(repliedId);
				reply.setPublishTime(new Date());
				reply.setUserId(user.getId());
				replyService.addReply(reply);
				// 发送通知给被回复的人
				try {
					// 获取所属的主评论
					Comment comment = new Comment();
					comment.setId(reply.getCommentId());
					comment = commentService.getComments(null, comment).get(0);
					// 获取所属的文章
					ArticleVO article = articleService.getArticle(comment.getArticleId());
					// 通知被回复的人
					if (null != repliedId) {
						StringBuilder message = new StringBuilder();
						message.append("<a href='/homepage?userId=").append(user.getId()).append("'>")
								.append(user.getName()).append("</a>").append("回复了你在   <a href='/article?articleId=")
								.append(article.getId()).append("'>").append(article.getTitle()).append("</a>的评论");
						Notification notification = new Notification(null, message.toString(), "", new Date(), false,
								repliedUser.getId(), repliedUser.getId());
						notificationService.addNotification(notification);
					}
					// 回复的不是自己的主评论
					// 通知主评论作者
					if (!comment.getUserId().equals(user.getId())) {
						StringBuilder message = new StringBuilder();
						message.append("<a href='/homepage?userId=").append(user.getId()).append("'>")
								.append(user.getName()).append("</a>").append("回复了你在   <a href='/article?articleId=")
								.append(article.getId()).append("'>").append(article.getTitle()).append("</a>的评论");
						Notification notification = new Notification(null, message.toString(), "", new Date(), false,
								comment.getUserId(), comment.getUserId());
						notificationService.addNotification(notification);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				map.put("status", "success");
				map.put("replies", replyService.getReply(reply.getId()));
				return map;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		map.put("status", "fail");
		return map;
	}

}
