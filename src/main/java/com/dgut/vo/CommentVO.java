package com.dgut.vo;

import com.dgut.po.Comment;
import com.dgut.po.User;

public class CommentVO extends Comment {

	private User author;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
