package com.dgut.po;

public class User {
	private Integer id;

	private String name;

	private String avatar;

	private String email;

	private String password;

	private String sex;

	private String introduce;

	private String phone;

	private Integer articles;

	private Integer roleId;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce == null ? null : introduce.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Integer getArticles() {
		return articles;
	}

	public void setArticles(Integer articles) {
		this.articles = articles;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public User() {
	}

	public User(Integer id, String name, String avatar, String email, String password, String sex, String introduce,
			String phone, Integer articles, Integer roleId) {
		super();
		this.id = id;
		this.name = name;
		this.avatar = avatar;
		this.email = email;
		this.password = password;
		this.sex = sex;
		this.introduce = introduce;
		this.phone = phone;
		this.articles = articles;
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", avatar=" + avatar + "]";
	}

}