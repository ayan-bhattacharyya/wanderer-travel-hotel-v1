package com.travel.hotel.base.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.service.DateTimeService;

@Entity
@Table(name = "user")
public class ApplicationUser implements Serializable {

	private static final long serialVersionUID = 1740869414999752409L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password")
	@JsonIgnore
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

	protected ApplicationUser() {

	}

	protected ApplicationUser(String username, String password, String email, String mobile, String createdBy) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.createdAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt() {
		this.modifiedAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public static class UserBuilder {
		private String username;
		private String password;
		private String email;
		private String mobile;
		private String createdBy;

		public UserBuilder withUsername(final String username) {
			this.username = username;
			return this;
		}

		public UserBuilder withPassword(final String password) {
			this.password = password;
			return this;
		}

		public UserBuilder withEmail(final String email) {
			this.email = email;
			return this;
		}

		public UserBuilder withMobile(final String mobile) {
			this.mobile = mobile;
			return this;
		}

		public UserBuilder withCreatedBy(final String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public ApplicationUser build() {
			return new ApplicationUser(username, password, email, mobile, createdBy);
		}

	}

	@Override
	public String toString() {
		return "ApplicationUser {" + ", ApplicationUser Id ='" + id + '\'' + ", Username ='" + username + '\''
				+ ", email ='" + email + '\'' + ", Mobile ='" + mobile + '\'' + ", Created at ='" + createdAt + '\''
				+ ", Created by ='" + createdBy + '\'' + ", Modified at ='" + modifiedAt + '\'' + ", Modified by ='"
				+ modifiedBy + '\'' + '}';
	}

}
