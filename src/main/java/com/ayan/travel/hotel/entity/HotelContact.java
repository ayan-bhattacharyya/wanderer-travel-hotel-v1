package com.ayan.travel.hotel.entity;

import java.io.Serializable;
import java.util.Objects;

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
public class HotelContact implements Serializable {
	
	private static final long serialVersionUID = -3511707557787401229L;

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

	public HotelContact(Hotel hotel, ContactType contactType, String contactValue, String createdAt, String createdBy,
			String modifiedAt, String modifiedBy) {
		this.hotel = hotel;
		this.contactType = contactType;
		this.contactValue = contactValue;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.modifiedAt = modifiedAt;
		this.modifiedBy = modifiedBy;
	}
	
	public HotelContact(Hotel hotel, ContactType contactType, String contactValue, String createdAt, String createdBy) {
		this.hotel = hotel;
		this.contactType = contactType;
		this.contactValue = contactValue;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

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
	
	
	@Override
	public String toString() {
		return "Hotel Contact{" +
                ", Hotel Code ='" + hotel.getHotelCode() + '\'' +
                ", Hotel Name ='" + hotel.getHotelName() + '\'' +
                ", Hotel Contact Id ='" + contactId + '\'' +
                ", Hotel Contact Type ='" + contactType + '\'' +
                ", Hotel Contact Value ='" + contactValue + '\'' +
                ", Created at ='" + createdAt + '\'' +
                ", Created by ='" + createdBy + '\'' +
                ", Modified at ='" + modifiedAt + '\'' +
                ", Modified by ='" + modifiedBy + '\'' +
                '}';
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		HotelContact hotelContact = (HotelContact) o;
		return Objects.equals(contactId, hotelContact.contactId) && 
				Objects.equals(hotel, hotelContact.hotel) &&
				contactType == hotelContact.contactType &&
				Objects.equals(contactValue, hotelContact.contactValue) &&
				Objects.equals(createdAt, hotelContact.createdAt) &&
				Objects.equals(createdBy, hotelContact.createdBy) &&
				Objects.equals(modifiedAt, hotelContact.modifiedAt) &&
				Objects.equals(modifiedBy, hotelContact.modifiedBy);
	}
	
	@Override
    public int hashCode() {
        return Objects.hash(contactId, hotel, contactType, contactValue,
        		createdAt, createdBy, modifiedAt, modifiedBy);
    }

}
