package com.dgut.po;

import java.util.Date;

import com.dgut.jsonSerializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Reply {
	private Integer id;

	private String content;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date publishTime;

	private Integer userId;

	private Integer commentId;

	private Integer repliedId;

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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getRepliedId() {
		return repliedId;
	}

	public void setRepliedId(Integer repliedId) {
		this.repliedId = repliedId;
	}

	public Reply() {
	}

	public Reply(Integer id, String content, Date publishTime, Integer userId, Integer commentId, Integer repliedId) {
		super();
		this.id = id;
		this.content = content;
		this.publishTime = publishTime;
		this.userId = userId;
		this.commentId = commentId;
		this.repliedId = repliedId;
	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", publishTime=" + publishTime + ", userId=" + userId
				+ ", commentId=" + commentId + ", repliedId=" + repliedId + "]";
	}

}