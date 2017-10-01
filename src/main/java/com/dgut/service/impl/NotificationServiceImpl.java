package com.dgut.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgut.dao.NotificationMapper;
import com.dgut.po.Notification;
import com.dgut.po.NotificationExample;
import com.dgut.po.NotificationExample.Criteria;
import com.dgut.service.NotificationService;
import com.dgut.service.exception.ServiceException;
import com.dgut.util.Page;
import com.dgut.vo.NotificationVO;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationMapper notificationMapper;

	@Override
	public List<NotificationVO> getNotifications(Page page, Integer userId, Boolean isReaded) {
		return notificationMapper.selectNotificationVOs(page, userId, isReaded);
	}

	@Override
	public void addNotification(Notification notification) throws ServiceException {
		notificationMapper.insert(notification);
	}

	@Override
	public int deleteNotification(Integer notificationId) {
		return notificationMapper.deleteByPrimaryKey(notificationId);
	}

	@Override
	public int countUnreadNotification(Integer userId) {
		NotificationExample example = new NotificationExample();
		Criteria criteria = example.createCriteria();
		criteria.andReceiverIdEqualTo(userId);
		criteria.andIsReadedEqualTo(false);
		return notificationMapper.countByExample(example);
	}

	@Override
	public int updateNotification(Integer userId, boolean all, Integer notificationId) {
		Notification record = new Notification();
		record.setIsReaded(true);
		NotificationExample example = new NotificationExample();
		Criteria criteria = example.createCriteria();
		if (all) {
			criteria.andReceiverIdEqualTo(userId);
		} else {
			criteria.andIdEqualTo(notificationId);
		}
		return notificationMapper.updateByExampleSelective(record, example);
	}

}
