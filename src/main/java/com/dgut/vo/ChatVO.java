package com.dgut.vo;

import com.dgut.po.Chat;
import com.dgut.po.User;

public class ChatVO extends Chat {
	private User receiver;

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
