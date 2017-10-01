package com.dgut.vo;

import com.dgut.po.Article;
import com.dgut.po.User;

public class ArticleVO extends Article {

	private User author;

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

}
