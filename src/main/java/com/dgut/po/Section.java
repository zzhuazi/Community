package com.dgut.po;

import java.util.Date;

import com.dgut.jsonSerializer.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Section {
	private Integer id;

	private String name;

	private String avatar;

	private String introduce;

	private Integer articles;

	@JsonSerialize(using = JsonDateSerializer.class)
	private Date lastPublishArticleTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce == null ? null : introduce.trim();
	}

	public Integer getArticles() {
		return articles;
	}

	public void setArticles(Integer articles) {
		this.articles = articles;
	}

	public Date getLastPublishArticleTime() {
		return lastPublishArticleTime;
	}

	public void setLastPublishArticleTime(Date lastPublishArticleTime) {
		this.lastPublishArticleTime = lastPublishArticleTime;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", name=" + name + ", avatar=" + avatar + ", introduce=" + introduce
				+ ", articles=" + articles + ", lastPublishArticleTime=" + lastPublishArticleTime + "]";
	}

}