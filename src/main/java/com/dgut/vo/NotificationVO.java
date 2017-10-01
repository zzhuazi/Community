package com.dgut.vo;

import com.dgut.po.Notification;
import com.dgut.po.User;

public class NotificationVO extends Notification {

	private User sender;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

}
