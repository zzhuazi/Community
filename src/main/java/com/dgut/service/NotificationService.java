package com.dgut.service;

import java.util.List;

import com.dgut.po.Notification;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.NotificationVO;

/**
 * @ClassName: NotificationService
 * @Description: 通知业务
 * @author 闲明苑
 * @date 2017年9月29日 下午1:58:10
 */
public interface NotificationService {

	/**
	 * @Title: getNotifications
	 * @Description: 
	 * page为null则不分页查询（注意，跟获取全部是有区别的）
	 * isReaded == null 则查询全部
	 * publish_time 最新排前规则
	 * @param page
	 * @return List<NotificationVO>
	 * @throws
	 */
	List<NotificationVO> getNotifications(Page page, Integer userId, Boolean isReaded);

	/**
	 * @Title: addNotification
	 * @Description: 
	 * 添加一条，添加失败则抛出ServiceException 及其子类异常
	 * @param notification void
	 * @throws
	 */
	void addNotification(Notification notification) throws ServiceException;

	/**
	 * @Title: deleteNotification
	 * @Description: 
	 * 删除对应notificationId的记录，并返回删除的记录数
	 * @param notificationId
	 * @return int
	 * @throws
	 */
	int deleteNotification(Integer notificationId);

	/**
	 * @Title: countUnreadNotification
	 * @Description: 获取userId对应的用户未读通知数量
	 * @param userId
	 * @return int
	 * @throws
	 */
	int countUnreadNotification(Integer userId);

	/**
	 * @Title: updateNotification
	 * @Description: 当all为true是,将所属userid的所有通知的isReaded设置为true(设置所有为已读)
	 * 					否则，设置notificationId的记录为已读
	 * @return int
	 * @throws
	 */
	int updateNotification(Integer userId, boolean all, Integer notificationId);
}
