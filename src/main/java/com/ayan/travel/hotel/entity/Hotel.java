package com.ayan.travel.hotel.entity;

import javax.persistence.Table;

import com.ayan.travel.hotel.domain.Status;

import java.io.Serializable;
import java.util.Objects;

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
	@Column(name = "hotel_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long hotelId;

	@Column(name = "hotel_code", unique = true, nullable = false)
	private String hotelCode;

	@Column(name = "hotel_name", nullable = false)
	private String hotelName;

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
	
	public Hotel() {
	}

	public Hotel(String hotelCode, String hotelName, Status status, String createdAt, String createdBy) {
		this.hotelCode = hotelCode;
		this.hotelName = hotelName;
		this.status = status;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

	public Long getHotelId() {
		return hotelId;
	}

	public void setHotelId(Long hotelId) {
		this.hotelId = hotelId;
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
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
		return "Hotel {" + "Hotel Id ='" + hotelId + '\'' + ", Hotel Code ='" + hotelCode + '\'' + ", Hotel Name ='"
				+ hotelName + '\'' + ", Status ='" + status + '\'' + ", Created at ='" + createdAt + '\''
				+ ", Created by ='" + createdBy + '\'' + ", Modified at ='" + modifiedAt + '\'' + ", Modified by ='"
				+ modifiedBy + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Hotel hotel = (Hotel) o;
		return Objects.equals(hotelId, hotel.hotelId) && Objects.equals(hotelCode, hotel.hotelCode)
				&& Objects.equals(hotelName, hotel.hotelName) && status == hotel.status
				&& Objects.equals(createdAt, hotel.createdAt) && Objects.equals(createdBy, hotel.createdBy)
				&& Objects.equals(modifiedAt, hotel.modifiedAt) && Objects.equals(modifiedBy, hotel.modifiedBy);
	}

}
