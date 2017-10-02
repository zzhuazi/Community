package com.dgut.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgut.po.Article;
import com.dgut.po.User;
import com.dgut.service.ArticleService;
import com.dgut.service.UserService;
import com.dgut.util.MD5Util;
import com.dgut.util.Page;
import com.dgut.vo.ArticleVO;
import com.dgut.vo.UserVO;

/**
 * @ClassName: UserController
 * @Description: 
 * @author 闲明苑
 * @date 2017年9月25日 下午8:57:31
 */
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;

	/**
	 * @Title: getRegisterPage
	 * @Description: 获取注册页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "register" }, method = { RequestMethod.GET })
	public String getRegisterPage() {
		return "register";
	}

	/**
	 * @Title: register
	 * @Description: 提交注册表单注册
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "register" }, method = { RequestMethod.POST })
	public String register(User user) {
		try {
			user.setAvatar("img/45_avatar_max.jpg");
			user.setIntroduce("");
			user.setPhone("");
			user.setSex("保密");
			user.setArticles(0);
			user.setPassword(MD5Util.encrypt(user.getPassword()));
			userService.register(user);
			return "redirect:/login";
		} catch (Exception e) {
		}
		return "register";
	}

	/**
	 * @Title: getLoginPage
	 * @Description: 获取登录页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "login" }, method = { RequestMethod.GET })
	public String getLoginPage(String url, HttpServletRequest request) {
		request.setAttribute("url", url);
		return "login";
	}

	/**
	 * @Title: login
	 * @Description: 提交表单进行登录
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "login" }, method = { RequestMethod.POST })
	public String login(User user, String url) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			AuthenticationToken token = new UsernamePasswordToken(user.getEmail(), MD5Util.encrypt(user.getPassword()));
			try {
				subject.login(token);

				// 登录成功，将当前登录的user信息放置到session域中
				User likeUser = new User();
				likeUser.setEmail(user.getEmail());
				List<UserVO> users = userService.getUsers(null, likeUser);
				Session session = subject.getSession();
				if (users.size() > 0) {
					session.setAttribute("user", users.get(0));
					if (null != url && !url.equals("")) {
						return "redirect:" + url;
					}
					return "redirect:/sections";
				}
			} catch (UnknownAccountException e) {
				// 账号不存在
			} catch (IncorrectCredentialsException e) {
				// 密码错误
			} catch (LockedAccountException e) {
				// 当前用户不允许被登录
			} catch (AuthenticationException e) {
				// 其他认证错误
			} catch (Exception e) {
			}
			return "login";
		}
		return "redirect:/sections";
	}

	/**
	 * @Title: logout
	 * @Description: 退出当前登录的用户
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "logout" }, method = { RequestMethod.GET })
	public String logout() {
		SecurityUtils.getSubject().logout();
		return "redirect:/login";
	}

	/**
	 * @Title: getForgotPasswordPage
	 * @Description: 获取找回密码页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "forgotPassword" }, method = { RequestMethod.GET })
	public String getForgotPasswordPage() {
		return "";
	}

	/**
	 * @Title: getProfilePage
	 * @Description: 获取个人信息页面(个人信息设置页面)
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "profile" }, method = { RequestMethod.GET })
	public String getProfilePage(@ModelAttribute(value = "profile") User profile, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return "redirect:/login";
		}
		try {
			Integer userId = ((UserVO) subject.getSession().getAttribute("user")).getId();
			UserVO user = userService.getUser(userId);
			profile.setSex(user.getSex());
			profile.setIntroduce(user.getIntroduce());
			profile.setPhone(user.getPhone());
			profile.setAvatar(user.getAvatar());
			request.setAttribute("userVo", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "profile";
	}

	/**
	 * @Title: updateProfile
	 * @Description: 提交修改的个人信息表单，更新个人信息
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "profile" }, method = { RequestMethod.POST })
	public String updateProfile(@ModelAttribute(value = "profile") User profile) {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			return "redirect:/login";
		}
		try {
			if (null != profile.getAvatar() && profile.getAvatar().equals("")) {
				profile.setAvatar(null);
			}
			Integer userId = ((UserVO) subject.getSession().getAttribute("user")).getId();
			profile.setId(userId);
			userService.updateUser(profile);
			profile = userService.getUser(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/profile";
	}

	/**
	 * @Title: getHomepagePage
	 * @Description: 获取个人主页页面
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "homepage" }, method = { RequestMethod.GET })
	public String getHomepagePage(Integer userId, HttpServletRequest request) {
		// 参数处理
		if (null == userId) {
			return "admin/404";
		}

		try {
			UserVO userVO = userService.getUser(userId);
			request.setAttribute("userVO", userVO);
			Page page = new Page(15, 1, 0);
			Article article = new Article();
			article.setUserId(userVO.getId());
			// 如果当前用户已登录，并且查看的是自己的主要，则显示所有文章，否则只显示已发表的文章
			Subject subject = SecurityUtils.getSubject();
			if (subject.isAuthenticated()) {
				Integer id = ((UserVO) subject.getSession().getAttribute("user")).getId();
				if (!id.equals(userId)) {
					article.setStatus(1);
				}
			} else {
				article.setStatus(1);
			}

			List<ArticleVO> articles = articleService.getArticles(page, article);
			request.setAttribute("articleVOs", articles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "homepage";
	}

	/**
	 * @Title: getUsersPage
	 * @Description: 获取一页用户数据页面（分页）
	 * @return String
	 * @throws
	 */
	@RequestMapping(path = { "users" }, method = { RequestMethod.GET })
	public String getUsersPage(HttpServletRequest request) {
		Page page = new Page(5, 1, 0);
		List<UserVO> users = userService.getUsers(page, null);
		request.setAttribute("users", users);
		return "users";
	}

	@RequestMapping(path = { "users" }, method = { RequestMethod.POST })
	@ResponseBody
	public Map<String, Object> getUsers(Integer currentPage) {
		Map<String, Object> map = new HashMap<>();
		try {
			Page page = new Page(5, currentPage, 0);
			List<UserVO> users = userService.getUsers(page, null);
			map.put("stauts", "success");
			map.put("users", users);
			map.put("page", users.size() >= 5 ? currentPage : 0);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("stauts", "fail");
		return map;

	}
}
