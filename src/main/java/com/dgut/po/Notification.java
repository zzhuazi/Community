package com.dgut.po;

import java.util.Date;

import com.dgut.jsonSerializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Notification {
	private Integer id;

	private String content;

	private String url;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date publishTime;

	private Boolean isReaded;

	private Integer senderId;

	private Integer receiverId;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Boolean getIsReaded() {
		return isReaded;
	}

	public void setIsReaded(Boolean isReaded) {
		this.isReaded = isReaded;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Notification() {
	}

	public Notification(Integer id, String content, String url, Date publishTime, Boolean isReaded, Integer senderId,
			Integer receiverId) {
		super();
		this.id = id;
		this.content = content;
		this.url = url;
		this.publishTime = publishTime;
		this.isReaded = isReaded;
		this.senderId = senderId;
		this.receiverId = receiverId;
	}

}