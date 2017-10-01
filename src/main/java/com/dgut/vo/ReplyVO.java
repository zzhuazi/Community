package com.dgut.vo;

import com.dgut.po.Reply;
import com.dgut.po.User;

public class ReplyVO extends Reply {

	private User author;

	private User receiver;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	@Override
	public String toString() {
		return "ReplyVO [" + "receiver=" + receiver + "]";
	}

}
