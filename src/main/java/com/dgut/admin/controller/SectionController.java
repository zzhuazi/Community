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
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.po.Section;
import com.dgut.service.SectionService;
import com.dgut.vo.SectionVO;

/**
 * @ClassName: SectionController
 * @Description: 版块管理控制器
 * @author 闲明苑
 * @date 2017年9月29日 下午2:01:00
 */
@Controller("adminSectionController")
public class SectionController {

	@Autowired
	private SectionService sectionService;

	/**
	 * @Title: getSectionPage
	 * @Description: 获取版块管理页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "admin/sections" }, method = { RequestMethod.GET })
	public String getSectionPage(HttpServletRequest request) {
		try {
			List<SectionVO> sections = sectionService.getSections(null, null, false);
			request.setAttribute("sections", sections);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "admin/sections";
	}

	/**
	 * @Title: getSection
	 * @Description: 根据sectionid获取section信息
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "admin/section" }, method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> getSection(Integer sectionId) {
		Map<String, Object> map = new HashMap<>();
		try {
			SectionVO section = sectionService.getSection(sectionId);
			map.put("status", "success");
			map.put("section", section);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "fail");
		return map;
	}

	/**
	 * @Title: updateSection
	 * @Description: 更新版块信息
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "admin/section" }, method = { RequestMethod.POST })
	public String updateSection(Section section) {
		System.out.println("SectionController.updateSection()");
		System.out.println(section);
		try {
			if (null != section.getAvatar() && section.getAvatar().equals("")) {
				section.setAvatar(null);
			}
			if (null == section.getId()) {
				section.setArticles(0);
				section.setLastPublishArticleTime(new Date());
				sectionService.addSection(section);
			} else {
				sectionService.updateSection(section);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/sections";
	}

}
