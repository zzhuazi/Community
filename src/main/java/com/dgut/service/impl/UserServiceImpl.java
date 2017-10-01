package com.dgut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgut.dao.UserMapper;
import com.dgut.po.User;
import com.dgut.po.UserExample;
import com.dgut.po.UserExample.Criteria;
import com.dgut.service.UserService;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public void register(User user) throws ServiceException {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		// 检查用户名
		criteria.andNameEqualTo(user.getName());
		List<User> users = userMapper.selectByExample(example);
		if (users.size() > 0) {
			// 用户名已存在
			throw new ServiceException("用户名已存在");
		}
		// 检查邮箱
		example.clear();
		criteria = null;
		criteria = example.createCriteria();
		criteria.andEmailEqualTo(user.getEmail());
		users = null;
		users = userMapper.selectByExample(example);
		if (users.size() > 0) {
			// 邮箱已注册
			throw new ServiceException("邮箱已注册");
		}
		userMapper.insert(user);
	}

	@Override
	public void updateUser(User user) throws ServiceException {
		userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public UserVO getUser(Integer userId) {
		return userMapper.selectUserVO(userId);
	}

	@Override
	public List<UserVO> getUsers(Page page, User likeUser) {
		// 构造example
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if (null != likeUser) {
			if (null != likeUser.getRoleId()) {
				criteria.andRoleIdEqualTo(likeUser.getRoleId());
			}
			if (null != likeUser.getEmail()) {
				criteria.andEmailLike("%" + likeUser.getEmail() + "%");
			}
			if (null != likeUser.getIntroduce()) {
				criteria.andIntroduceLike("%" + likeUser.getIntroduce() + "%");
			}
			if (null != likeUser.getName()) {
				criteria.andNameLike("%" + likeUser.getName() + "%");
			}
			if (null != likeUser.getPhone()) {
				criteria.andPhoneLike("%" + likeUser.getPhone() + "%");
			}
			if (null != likeUser.getSex()) {
				criteria.andSexLike("%" + likeUser.getSex() + "%");
			}

		}
		return userMapper.selectUserVOs(page, example);
	}

}
