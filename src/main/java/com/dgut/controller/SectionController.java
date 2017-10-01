package com.dgut.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dgut.po.Article;
import com.dgut.service.ArticleService;
import com.dgut.service.SectionService;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;
import com.dgut.vo.SectionVO;

/**
 * @ClassName: SectionController
 * @Description: 版块相关控制器
 * @author 闲明苑
 * @date 2017年9月29日 下午1:56:17
 */
@Controller
public class SectionController {

	@Autowired
	private SectionService sectionService;
	@Autowired
	private ArticleService articleService;

	private int pageSize = 15;// 文章页数

	/**
	 * @Title: getSectionsPage
	 * @Description: 获取版块列表页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "sections" }, method = { RequestMethod.GET })
	public String getSectionsPage(HttpServletRequest request) {
		try {
			List<SectionVO> sections = sectionService.getSections(null, null, true);
			request.setAttribute("sections", sections);
		} catch (Exception e) {
		}
		return "sections";
	}

	/**
	 * @Title: getSectionPage
	 * @Description: 获取某一版块页面（获取文章列表页面）
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "section" }, method = { RequestMethod.GET })
	public String getSectionPage(@RequestParam(defaultValue = "1") Integer currentpage, HttpServletRequest request,
			@ModelAttribute Article article) {
		// 参数处理
		if (null == article.getSectionId()) {
			return "admin/404";
		}
		//
		try {
			SectionVO section = sectionService.getSection(article.getSectionId());
			if (null == section) {
				return "admin/404";
			}
			request.setAttribute("section", section);
			Page page = new Page(pageSize, currentpage, 0);
			article.setStatus(1);
			List<ArticleVO> articles = articleService.getArticles(page, article);
			int size = articleService.getArticles(null, article).size();
			int total = (size / pageSize + (size % pageSize != 0 ? 1 : 0));
			request.setAttribute("page", currentpage);
			request.setAttribute("total", total);
			request.setAttribute("articles", articles);
			return "section";
		} catch (Exception e) {
		}
		return "admin/500";
	}

}
