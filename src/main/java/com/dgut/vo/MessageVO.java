package com.dgut.vo;

import com.dgut.po.Message;
import com.dgut.po.User;

public class MessageVO extends Message {

	private User receiver;

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
