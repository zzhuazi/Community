package com.dgut.po;

import java.util.Date;

import com.dgut.jsonSerializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Message {
	private Integer id;

	private String content;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date sendTime;

	private Integer userId;

	private Integer receiverId;

	private Integer senderId;

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

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Message() {
	}

	public Message(Integer id, String content, Date sendTime, Integer userId, Integer receiverId, Integer senderId) {
		super();
		this.id = id;
		this.content = content;
		this.sendTime = sendTime;
		this.userId = userId;
		this.receiverId = receiverId;
		this.senderId = senderId;
	}

}