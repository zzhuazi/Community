package com.dgut.po;

import java.util.Date;

import com.dgut.jsonSerializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Chat {
	private Integer id;

	private Integer messages;

	private String lastActiveContent;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date lastActiveTime;

	private Integer unread;

	private Integer userId;

	private Integer receiverId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMessages() {
		return messages;
	}

	public void setMessages(Integer messages) {
		this.messages = messages;
	}

	public String getLastActiveContent() {
		return lastActiveContent;
	}

	public void setLastActiveContent(String lastActiveContent) {
		this.lastActiveContent = lastActiveContent == null ? null : lastActiveContent.trim();
	}

	public Date getLastActiveTime() {
		return lastActiveTime;
	}

	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public Integer getUnread() {
		return unread;
	}

	public void setUnread(Integer unread) {
		this.unread = unread;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Chat() {
	}

	public Chat(Integer id, Integer messages, String lastActiveContent, Date lastActiveTime, Integer unread,
			Integer userId, Integer receiverId) {
		super();
		this.id = id;
		this.messages = messages;
		this.lastActiveContent = lastActiveContent;
		this.lastActiveTime = lastActiveTime;
		this.unread = unread;
		this.userId = userId;
		this.receiverId = receiverId;
	}

}