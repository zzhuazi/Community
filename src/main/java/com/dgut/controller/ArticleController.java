package com.dgut.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.po.Article;
import com.dgut.po.Comment;
import com.dgut.service.ArticleService;
import com.dgut.service.CommentService;
import com.dgut.service.SectionService;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;
import com.dgut.vo.CommentVO;
import com.dgut.vo.SectionVO;
import com.dgut.vo.UserVO;

/**
 * @ClassName: ArticleController
 * @Description: 文章相关控制器
 * @author 闲明苑
 * @date 2017年9月29日 下午1:55:15
 */
@Controller
public class ArticleController {

	@Autowired
	private SectionService sectionService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;

	/**
	 * @Title: getArticlePage
	 * @Description: 获取文章页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "article" }, method = { RequestMethod.GET })
	public String getArticlePage(Integer articleId, HttpServletRequest request) {

		try {
			ArticleVO article = articleService.getArticle(articleId);
			// 文章未通过发表申请、或者文章属于当前登录用户
			if (!article.getStatus().equals(1)) {
				Subject subject = SecurityUtils.getSubject();
				if (!subject.isAuthenticated()) {
					return "admin/404";
				}

			}
			request.setAttribute("article", article);
			// 获取主评论
			Page page = new Page(15, 1, 0);
			Comment comment = new Comment();
			comment.setArticleId(articleId);
			List<CommentVO> comments = commentService.getComments(page, comment);
			request.setAttribute("comments", comments);
		} catch (Exception e) {
		}
		return "article";
	}

	/**
	 * @Title: getPublishArticlePage
	 * @Description: 获取发表文章页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "publishArticle" }, method = { RequestMethod.GET })
	public String getPublishArticlePage(HttpServletRequest request) {
		try {
			// 获取所有版块
			List<SectionVO> sections = sectionService.getSections(null, null, true);
			request.setAttribute("sections", sections);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "publish_article";
	}

	/**
	 * @Title: publishArticle
	 * @Description: 提交文章表单信息发表文章,返回json数据
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "publishArticle" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> publishArticle(Article article) {

		Map<String, Object> map = new HashMap<>();

		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			try {
				boolean isUnLegal = null == article.getTitle() || article.getTitle().equals("")
						|| null == article.getSectionId();
				if (isUnLegal) {
					map.put("status", "fail");
					return map;
				}
				UserVO user = (UserVO) subject.getSession().getAttribute("user");
				article.setComments(0);
				article.setPublishTime(new Date());
				article.setLastActiveTime(new Date());
				article.setStatus(0);
				article.setUserId(user.getId());
				articleService.addArticle(article);
				map.put("status", "success");
				map.put("articleId", article.getId());
				return map;
			} catch (Exception e) {
			}
		}
		map.put("status", "fail");
		return map;
	}

	/**
	 * @Title: getArticles
	 * @Description: 获取sectionId对应版块的第currentpage页文章
	 * @return Map<String,Object>
	 * @throws
	 */
	private int pageSize = 15;

	@RequestMapping(path = { "/articles" }, method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getArticles(Integer sectionId, Integer currentpage, Article article) {
		Map<String, Object> map = new HashMap<>();
		try {
			Page page = new Page(pageSize, currentpage, 0);
			List<ArticleVO> articles = articleService.getArticles(page, article);
			map.put("articles", articles);
			if (articles.size() < pageSize) {
				currentpage = -1;
			}
			map.put("stauts", "success");
			map.put("page", currentpage);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("stauts", "fail");
		return map;
	}

}
