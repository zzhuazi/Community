package com.dgut.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: TockenController
 * @Description: 防止表单重复提交tocken生成控制器
 * @author 闲明苑
 * @date 2017年9月30日 上午11:47:47
 */
@Controller
public class TockenController {

	@RequestMapping(path = { "tocken" }, method = { RequestMethod.GET })
	@ResponseBody
	public Map<String, Object> name() {
		Map<String, Object> map = new HashMap<>();
		Session session = SecurityUtils.getSubject().getSession();
		String tocken = UUID.randomUUID().toString();
		session.setAttribute("tocken", tocken);
		map.put("tocken", tocken);
		map.put("status", "success");
		return map;
	}

}
