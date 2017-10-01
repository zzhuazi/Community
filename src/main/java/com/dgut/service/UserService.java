package com.dgut.service;

import java.util.List;

import com.dgut.po.User;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.UserVO;

/**
 * @ClassName: UserService
 * @Description: 用户业务
 * @author 闲明苑
 * @date 2017年9月29日 下午1:58:47
 */
public interface UserService {

	/**
	 * @Title: register
	 * @Description:
	 * 注册成功则无异常抛出
	 * 1.用户名已被使用则抛出ServiceException
	 * 2.邮箱已注册则抛出ServiceException 
	 * @return void
	 * @throws
	 */
	void register(User user) throws ServiceException;

	/**
	 * @Title: updateUser
	 * @Description: 
	 * 需要更新的user属性不为null，否则为null，id不能为null
	 * @return void
	 * @throws
	 */
	void updateUser(User user) throws ServiceException;

	/**
	 * @Title: getUser
	 * @Description: 
	 * 根据userid 获取对应的UserVO对象
	 * 获取成功返回对应实例，否则返回null
	 * @return UserVO
	 * @throws
	 */
	UserVO getUser(Integer userId);

	/**
	 * @Title: getUsers
	 * @Description: 
	 * page为null则不分页查询（注意，跟获取全部是有区别的）
	 * likeUser 为null时，则不进行模糊查询，
	 * 否则，若它属性不为null时，则对某个属性进行模糊查询
	 * @return List<UserVO>
	 * @throws
	 */
	List<UserVO> getUsers(Page page, User likeUser);

}
