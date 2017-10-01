package com.dgut.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: FileService
 * @Description: 文件业务类，包括文件上传
 * @author 闲明苑
 * @date 2017年9月28日 上午9:57:17
 */
public interface FileService {

	/**
	 * @Title: upload
	 * @Description: 保存文件到 path 目录下，保存失败返回null，成功返回name(/img/avatar.jpg)
	 * @return String
	 * @throws
	 */
	String upload(MultipartFile file, String path, String name);

}
