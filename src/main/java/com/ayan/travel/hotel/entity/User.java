package com.ayan.travel.hotel.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1740869414999752409L;

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "mobile", nullable = false)
	private String mobile;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	public User() {
		
	}
	
	public User(String username, String password, String email, String mobile, String createdAt, String createdBy) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "User {" + ", User Id ='" + userId + '\'' + ", Username ='"
				+ username + '\'' + ", email ='" + email + '\'' + ", Mobile ='"
				+ mobile + '\'' + ", Created at ='" + createdAt + '\'' + ", Created by ='" + createdBy + '\''
				+ ", Modified at ='" + modifiedAt + '\'' + ", Modified by ='" + modifiedBy + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return Objects.equals(userId, user.userId) 
				&& Objects.equals(username, user.username)
				&& Objects.equals(email, user.email)
				&& Objects.equals(mobile, user.mobile)
				&& Objects.equals(createdAt, user.createdAt)
				&& Objects.equals(createdBy, user.createdBy)
				&& Objects.equals(modifiedAt, user.modifiedAt)
				&& Objects.equals(modifiedBy, user.modifiedBy);
	}

}
