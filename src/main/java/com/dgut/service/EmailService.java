package com.dgut.service;

import java.util.Map;

import javax.mail.MessagingException;

/**
 * @ClassName: EmailService
 * @Description: 邮件业务接口
 * @author 闲明苑
 * @date 2017年9月29日 下午9:10:55
 */
public interface EmailService {

	/**
	 * @Title: sendEmail
	 * @Description: 发送邮件接口，成功返回true
	 * @return boolean
	 * @throws
	 * template : "index" as index.html
	 * subject: 标题
	 * sendTo : admin@admin.com
	 * map.put("text", "5646456")
	 * <body>
	 * 	<p th:text="${text}"></>
	 * </body>
	 */
	boolean sendEmail(String template, Map<String, String> map, String sendTo, String subject)
			throws MessagingException;

}
