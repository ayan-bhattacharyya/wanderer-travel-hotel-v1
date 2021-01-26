package com.ayan.travel.hotel.entity;

import javax.persistence.Table;

import com.ayan.travel.hotel.domain.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="hotel")
public class Hotel {
	
	@Id
	@Column(name = "hotel_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long hotelId;
	
	@Column(name = "hotel_code")
	private String hotelCode;
	
	@Column(name = "hotel_name")
	private String hotelName;
	
	@Column(name = "status")
	private Status status;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "modified_at")
	private String modifiedAt;
	
	@Column(name = "modified_by")
	private String modifiedBy;

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
	

}
