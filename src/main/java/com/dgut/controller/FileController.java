package com.dgut.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dgut.service.FileService;

/**
 * @ClassName: FileController
 * @Description: 文件控制器
 * @author 闲明苑
 * @date 2017年9月28日 上午9:58:16
 */
@Controller
public class FileController {

	@Autowired
	private FileService fileService;

	/**
	 * @Title: avatar
	 * @Description: 修改用户头像，需要登录权限
	 * @return Map<String,Object>
	 * @throws
	 */
	@RequestMapping(path = { "/avatar/upload" }, method = { RequestMethod.POST }, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> uploadFiles(HttpServletRequest httpServletRequest) {
		Map<String, Object> map = new HashMap<>();
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
		try {
			String path = "src/main/resources/templates";
			List<String> list = new ArrayList<>();
			Iterator<String> fileNames = request.getFileNames();
			while (fileNames.hasNext()) {
				String fileName = fileNames.next();
				List<MultipartFile> files = request.getFiles(fileName);
				for (int i = 0; i < files.size(); i++) {
					MultipartFile file = files.get(i);
					String name = "/img/" + generatorFileName() + getFileType(file);
					String upload = fileService.upload(file, path, name);
					list.add(upload);
				}
			}
			map.put("status", "success");
			map.put("list", list);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("status", "fail");
		return map;
	}

	/**
	 * @Title: generatorFileName
	 * @Description: 获取当前时间字符串
	 * @return String
	 * @throws
	 */
	private String generatorFileName() {
		return Calendar.getInstance().getTimeInMillis() + "";
	}

	/**
	 * @Title: getFileType
	 * @Description: 获取文件类型，无类型返回""，有则返回带点类型 eg ".txt"
	 * @return String
	 * @throws
	 */
	private String getFileType(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		String[] split = originalFilename.split("\\.");
		return split.length > 1 ? "." + split[split.length - 1] : "";
	}

}
