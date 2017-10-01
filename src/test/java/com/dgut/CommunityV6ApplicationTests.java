package com.dgut;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dgut.dao.ArticleMapper;
import com.dgut.dao.UserMapper;
import com.dgut.po.ArticleExample;
import com.dgut.po.Comment;
import com.dgut.po.Reply;
import com.dgut.po.User;
import com.dgut.service.CommentService;
import com.dgut.service.EmailService;
import com.dgut.service.NotificationService;
import com.dgut.service.PermissionService;
import com.dgut.service.ReplyService;
import com.dgut.service.UserService;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;
import com.dgut.vo.CommentVO;
import com.dgut.vo.NotificationVO;
import com.dgut.vo.PermissionVO;
import com.dgut.vo.ReplyVO;
import com.dgut.vo.UserVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommunityV6ApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(CommunityV6ApplicationTests.class);

	@Autowired
	UserMapper userMapper;
	@Autowired
	ArticleMapper articleMapper;

	@Autowired
	UserService userService;

	@Test
	public void user() {
		// User record = new User(1, null, "/img/default.jpg", null, "123456",
		// null, null, null, null, null);
		// userMapper.insert(record);
		// userService.updateUser(record);
		User user = new User();
		user.setId(1);
		Page page = new Page(10, 1, 0);
		List<UserVO> users = userService.getUsers(page, null);
		for (int i = 0; i < users.size(); i++) {
			UserVO userVO = users.get(i);
			log.debug(userVO.getAvatar());
		}
	}

	@Test
	public void article() {
		// Article record = new Article(null, "测试标题", new Date(), new Date(), 0,
		// 0, 1, 0, "测试文章内容");
		// articleMapper.insert(record);
		Page page = new Page(10, 1, 0);
		ArticleExample example = new ArticleExample();
		List<ArticleVO> articles = articleMapper.selectArticleVOs(page, example);
		for (int i = 0; i < articles.size(); i++) {
			ArticleVO article = articles.get(i);
			System.out.println(article.getTitle());
			System.out.println(article.getAuthor().getName());
		}
	}

	@Autowired
	CommentService commentService;

	@Test
	public void comment() {
		// Comment comment = new Comment(null, "你好", new Date(), 0, 1, 1);
		// commentService.addComment(comment);
		Comment likeComment = new Comment();
		Page page = new Page(10, 1, 0);
		List<CommentVO> comments = commentService.getComments(page, likeComment);
		for (int i = 0; i < comments.size(); i++) {
			CommentVO comment = comments.get(i);
			log.debug(comment.getContent());
			log.debug(comment.getAuthor().getName());
			commentService.deleteComment(comment.getId(), null);
		}
	}

	@Autowired
	ReplyService replyService;

	@Test
	public void reply() {
		Reply reply = new Reply();
		reply.setCommentId(7);
		Page page = new Page(10, 1, 0);
		List<ReplyVO> replies = replyService.getReplies(page, reply);
		System.out.println(replies);
		// ReplyVO reply = replyService.getReply(35);
		// System.out.println(reply);
	}

	@Autowired
	NotificationService notificationService;

	@Test
	public void notification() {
		Page page = new Page(10, 1, 0);
		List<NotificationVO> notifications = notificationService.getNotifications(page, 1, false);
		System.out.println(notifications.size());
	}

	@Autowired
	EmailService emailService;

	@Test
	public void email() {
		try {
			String template = "email_demo";
			Map<String, String> map = new HashMap<>();
			map.put("code", "woj");
			String sendTo = "675620982@qq.com";
			String subject = "社会找回密码";
			emailService.sendEmail(template, map, sendTo, subject);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	@Autowired
	PermissionService permissionService;

	@Test
	public void permission() {
		Set<PermissionVO> permissionsBy = permissionService.getPermissionsBy(2);
		Iterator<PermissionVO> iterator = permissionsBy.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

}
