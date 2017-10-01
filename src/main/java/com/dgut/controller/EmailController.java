package com.dgut.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgut.po.User;
import com.dgut.service.EmailService;
import com.dgut.service.UserService;
import com.dgut.util.MD5Util;
import com.dgut.vo.UserVO;

@Controller
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	@Autowired
	private UserService userService;
	
	/**
	 * @Title: getForgotPage
	 * @Description: 获得找回密码界面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path={"forgot"}, method={RequestMethod.GET})
	public String getForgotPage(){
		return "forgot";
	}
	
	/**
	 * @Title: sendEmail
	 * @Description: TODO
	 * @param sendTo
	 * @return String
	 * @throws
	 */
	@RequestMapping(path={"forgot"}, method={RequestMethod.POST})
	public String sendEmail(String sendTo){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		User likeUser = new User();
		likeUser.setEmail(sendTo);
		List<UserVO> users = userService.getUsers(null, likeUser);
		if (users.size()>0) {
			Map<String, String> map = new HashMap<>();
			map.put("code", "1222");
			session.setAttribute("email", sendTo);
			session.setAttribute("code", "1222");
			try {
				emailService.sendEmail("code", map, sendTo, "社区找回密码");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return "verify_code";
		}else{
			//用户邮箱不存在
		}
		return "login";
	}
	
	/**
	 * @Title: verityCode
	 * @Description: 验证验证码是否正确
	 * @param code
	 * @return String
	 * @throws
	 */
	@RequestMapping(path={"code"}, method={RequestMethod.POST})
	public String verityCode(String code){
		Subject subject = SecurityUtils.getSubject();
		try {
			Session session = subject.getSession();
			String sessionCode = (String) session.getAttribute("code");
			if (sessionCode.equals(code)) {
				//加标志,判断是否验证码是否通过，通过为success
				session.setAttribute("verifyCode", "success");
				//验证码正确则跳转到设置密码页面
				return "set_password";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//否则跳到？？，重新获取验证码
		return "forgot";
	}
	
	@RequestMapping(path={"setPassword"}, method={RequestMethod.POST})
	public String setPassword(String password){
		Subject subject = SecurityUtils.getSubject();
		try {
			Session session = subject.getSession();
			String verifyCode = (String) session.getAttribute("verifyCode");
			if (verifyCode != null &&verifyCode.equals("success") ) {
				String email = (String) session.getAttribute("email");
				System.out.println("email:::::" + email);
				User likeUser = new User();
				likeUser.setEmail(email);
				List<UserVO> users = userService.getUsers(null, likeUser);
				System.out.println("users.size::::"+users.size());
				User user = (User)users.get(0);
				System.out.println("userPassword:beforeset:::"+user.getPassword());
				user.setId(users.get(0).getId());
				user.setPassword(MD5Util.encrypt(password));
				System.out.println("userPassword:afterset:::"+user.getPassword());
				userService.updateUser(user); //?更新失败
				return "login";
			} else {
				return "forgot";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forgot";
	}
}
