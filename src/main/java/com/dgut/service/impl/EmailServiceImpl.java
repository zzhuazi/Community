package com.dgut.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import com.dgut.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	@Value("${spring.mail.username}")
	private String fromEmailAddr;
	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Override
	public boolean sendEmail(String template, Map<String, String> map, String sendTo, String subject)
			throws MessagingException {
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		// 开启带附件true
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true);
		IContext context = new Context();
		// 获取模板html代码
		String process = templateEngine.process(template, context);
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			process = process.replace(next.getKey(), next.getValue());
		}
		try {
			messageHelper.setFrom(fromEmailAddr);
			messageHelper.setTo(sendTo);
			messageHelper.setSubject(subject);
			messageHelper.setText(process, true);
			// FileSystemResource avatar = new FileSystemResource(new
			// File("F:\\workspace\\SpringBootEmail\\src\\main\\resources\\templates\\img\\bappy.jpg"));
			// <img src="cid:avatar" />

			javaMailSender.send(mailMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return false;
	}

}
