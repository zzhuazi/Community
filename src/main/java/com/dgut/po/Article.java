package com.dgut.po;

import java.util.Date;

import com.dgut.jsonSerializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Article {
	private Integer id;

	private String title;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date publishTime;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date lastActiveTime;

	private Integer comments;

	private Integer status;// 0 审核中、1 通过审核、2 未通过审核

	private Integer userId;

	private Integer sectionId;

	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getLastActiveTime() {
		return lastActiveTime;
	}

	public void setLastActiveTime(Date lastActiveTime) {
		this.lastActiveTime = lastActiveTime;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public Article() {
	}

	public Article(Integer id, String title, Date publishTime, Date lastActiveTime, Integer comments, Integer status,
			Integer userId, Integer sectionId, String content) {
		super();
		this.id = id;
		this.title = title;
		this.publishTime = publishTime;
		this.lastActiveTime = lastActiveTime;
		this.comments = comments;
		this.status = status;
		this.userId = userId;
		this.sectionId = sectionId;
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", publishTime=" + publishTime + ", lastActiveTime="
				+ lastActiveTime + ", comments=" + comments + ", status=" + status + ", userId=" + userId
				+ ", sectionId=" + sectionId + ", content=" + content + "]";
	}

}