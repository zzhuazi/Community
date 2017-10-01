package com.dgut.po;

import java.util.Date;

import com.dgut.jsonSerializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Comment {
	private Integer id;

	private String content;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date publishTime;

	private Integer replies;

	private Integer userId;

	private Integer articleId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Integer getReplies() {
		return replies;
	}

	public void setReplies(Integer replies) {
		this.replies = replies;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Comment() {
	}

	public Comment(Integer id, String content, Date publishTime, Integer replies, Integer userId, Integer articleId) {
		super();
		this.id = id;
		this.content = content;
		this.publishTime = publishTime;
		this.replies = replies;
		this.userId = userId;
		this.articleId = articleId;
	}

}