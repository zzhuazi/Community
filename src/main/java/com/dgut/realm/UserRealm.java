package com.dgut.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.dgut.dao.UserMapper;
import com.dgut.po.User;
import com.dgut.po.UserExample;
import com.dgut.po.UserExample.Criteria;

/**
 * @ClassName: UserRealm
 * @Description: shiro 认证授权 数据源接口
 * @author 闲明苑
 * @date 2017年9月29日 下午1:57:14
 */
public class UserRealm extends AuthorizingRealm {

	private final String realmName = "UserRealm.class";
	@Autowired
	private UserMapper userMapper;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object principal = principals.getPrimaryPrincipal();
		try {
			if (principal instanceof String) {
			}
		} catch (Exception e) {
		}

		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		Object principal = token.getPrincipal();
		if (principal instanceof String) {
			String email = (String) principal;
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andEmailEqualTo(email);
			List<User> users = userMapper.selectByExample(example);
			if (null != users && users.size() > 0) {
				User user = users.get(0);
				return new SimpleAccount(user.getEmail(), user.getPassword(), realmName);
			}
		}
		return null;
	}

}
