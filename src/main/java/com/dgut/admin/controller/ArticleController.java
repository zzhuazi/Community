package com.dgut.admin.controller;

import java.util.Date;
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

import com.dgut.po.Article;
import com.dgut.service.ArticleService;
import com.dgut.service.SectionService;
import com.dgut.service.UserService;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;
import com.dgut.vo.SectionVO;
import com.dgut.vo.UserVO;

/**
 * @ClassName: ArticleController
 * @Description: 文章管理控制器
 * @author 闲明苑
 * @date 2017年9月28日 下午5:00:34
 */
@Controller("adminArticleController")
public class ArticleController {

	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;
	@Autowired
	private SectionService sectionService;

	/**
	 * @Title: getIndexPage
	 * @Description: type 0 待审核、1 通过审核、2 未通过审核、null全部
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "admin/articles" }, method = { RequestMethod.GET })
	public String getIndexPage(Integer type, @RequestParam(defaultValue = "1") Integer currentpage,
			HttpServletRequest request) {
		try {
			Article article = new Article();
			article.setStatus(type);
			Page page = new Page(10, currentpage, 0);
			List<ArticleVO> articles = articleService.getArticles(page, article);
			request.setAttribute("articles", articles);
			request.setAttribute("page", currentpage);
			request.setAttribute("type", type);
			int size = articleService.getArticles(null, article).size();
			int total = (size / 10 + (size % 10 != 0 ? 1 : 0));
			System.out.println("total : " + total);
			request.setAttribute("total", total);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/articles";
	}

	/**
	 * @Title: updateArticles
	 * @Description: status 0 待审核、1 通过审核、2 未通过审核
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "admin/articles" }, method = { RequestMethod.POST }, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody()
	public Map<String, Object> updateArticles(@RequestParam(value = "articleIds[]") List<Integer> articleIds,
			Integer status) {
		Map<String, Object> map = new HashMap<>();
		try {
			for (int i = 0; i < articleIds.size(); i++) {
				Article article = new Article();
				article.setId(articleIds.get(i));
				article.setStatus(status);
				articleService.updateArticle(article);
				article = articleService.getArticle(article.getId());
				if (status == 1) {
					// 更新用户文章数
					UserVO user = userService.getUser(article.getUserId());
					System.out.println("user : " + user);
					System.out.println("user.getArticles() : " + user.getArticles());
					user.setArticles(user.getArticles() + 1);
					userService.updateUser(user);
					// 更新版块文章数和上次发表文章时间
					SectionVO section = sectionService.getSection(article.getSectionId());
					section.setArticles(section.getArticles() + 1);
					section.setLastPublishArticleTime(new Date());
					sectionService.updateSection(section);
				}
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
