package com.travel.hotel.base.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.domain.ContactType;
import com.travel.hotel.base.service.DateTimeService;

@Entity
@Table(name = "hotel_contact")
public class HotelContact implements Serializable {

	private static final long serialVersionUID = -3511707557787401229L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	@Column(name = "is_primary", nullable = false)
	private Boolean isPrimary;

	@Column(name = "type", nullable = false)
	private ContactType type;

	@Column(name = "value", nullable = false)
	private String value;

	@Column(name = "created_at", nullable = false)
	private String createdAt;

	@Column(name = "created_by", nullable = false)
	private String createdBy;

	@Column(name = "modified_at")
	private String modifiedAt;

	@Column(name = "modified_by")
	private String modifiedBy;

	protected HotelContact() {}
	
	protected HotelContact(Hotel hotel, ContactType type, String value, Boolean isPrimary, String createdBy) {
		this.hotel = hotel;
		this.type = type;
		this.value = value;
		this.isPrimary = isPrimary;
		this.createdAt = new DateTimeService().getDateTime(Constants.DATE_FORMAT);
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public ContactType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public Boolean getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
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

	public static class HotelContactBuilder {
		private Hotel hotel;
		private String type;
		private String value;
		private Boolean isPrimary;
		private String createdBy;

		public HotelContactBuilder withHotel(final Hotel hotel) {
			this.hotel = hotel;
			return this;
		}

		public HotelContactBuilder withType(final String type) {
			this.type = type;
			return this;
		}

		public HotelContactBuilder withValue(final String value) {
			this.value = value;
			return this;
		}
		
		public HotelContactBuilder withIsPrimary(final Boolean isPrimary) {
			this.isPrimary = isPrimary;
			return this;
		}

		public HotelContactBuilder withCreatedBy(final String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public HotelContact build() {
			return new HotelContact(hotel, ContactType.findByLabel(type), value, isPrimary, createdBy);
		}

	}

	@Override
	public String toString() {
		return "Hotel Contact{" + ", Hotel Contact Id ='" + id + '\'' + ", Hotel Contact Type ='" + type + '\''
				+ ", Hotel Contact Value ='" + value + '\'' + ", Primary Hotel Address Type ='" + isPrimary + '\''
				+ ", Created at ='" + createdAt + '\'' + ", Created by ='"
				+ createdBy + '\'' + ", Modified at ='" + modifiedAt + '\'' + ", Modified by ='" + modifiedBy + '\''
				+ '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HotelContact hotelContact = (HotelContact) o;
		return Objects.equals(id, hotelContact.id) && Objects.equals(hotel, hotelContact.hotel)
				&& type == hotelContact.type && Objects.equals(value, hotelContact.value)
				&& Objects.equals(isPrimary, hotelContact.isPrimary)
				&& Objects.equals(createdAt, hotelContact.createdAt)
				&& Objects.equals(createdBy, hotelContact.createdBy)
				&& Objects.equals(modifiedAt, hotelContact.modifiedAt)
				&& Objects.equals(modifiedBy, hotelContact.modifiedBy);
	}

}
