package com.ayan.travel.hotel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ayan.travel.hotel.domain.ContactType;

@Entity
@Table(name = "hotel_contact")
public class HotelContact {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contact_id")
	private Long contactId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "hotel_id")
	private Hotel hotel;
	
	@Column(name = "contact_type", nullable = false)
	private ContactType contactType;
	
	@Column(name = "contact_value", nullable = false)
	private String contactValue;
	
	@Column(name = "created_at", nullable = false)
	private String createdAt;
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	
	@Column(name = "modified_at")
	private String modifiedAt;
	
	@Column(name = "modified_by")
	private String modifiedBy;

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
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
