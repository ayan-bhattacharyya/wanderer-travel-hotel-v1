package com.travel.hotel.base.entity;

import javax.persistence.Table;

import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.domain.Status;
import com.travel.hotel.base.service.DateTimeService;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

	private static final long serialVersionUID = 2132956606968141760L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "code", unique = true, nullable = false)
	private String code;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "status", nullable = false)
	private Status status;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;
	
	protected Hotel() {}

	protected Hotel(String code, String name, Status status, String createdBy) {
		this.code = code;
		this.name = name;
		this.status = status;
		this.createdAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public static class HotelBuilder {
		private String code;
		private String name;
		private String status;
		private String createdBy;

		public HotelBuilder withCode(final String code) {
			this.code = code;
			return this;
		}

		public HotelBuilder withName(final String name) {
			this.name = name;
			return this;
		}

		public HotelBuilder withStatus(final String status) {
			this.status = status;
			return this;
		}

		public HotelBuilder withCreatedBy(final String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Hotel build() {
			return new Hotel(code, name, Status.findByLabel(status), createdBy);

		}

	}

	@Override
	public String toString() {
		return "Hotel {" + "Id ='" + id + '\'' + ", Hotel Code ='" + code + '\'' + ", Hotel Name ='" + name + '\''
				+ ", Status ='" + status + '\'' + ", Created at ='" + createdAt + '\'' + ", Created by ='" + createdBy
				+ '\'' + ", Modified at ='" + modifiedAt + '\'' + ", Modified by ='" + modifiedBy + '\'' + '}';
	}

}
