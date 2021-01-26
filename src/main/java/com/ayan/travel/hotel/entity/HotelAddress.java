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

import com.ayan.travel.hotel.domain.AddressType;

@Entity
@Table(name= "hotel_address")
public class HotelAddress {
	
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "hotel_id")
	private Hotel hotel;
	
	@Column(name = "address_type", nullable = false)
	private AddressType adressType;
	
	@Column(name = "address_line_1", nullable = false)
	private String addressLine1;
	
	@Column(name = "address_line_2")
	private String addressLine2;
	
	@Column(name = "address_line_3")
	private String addressLine3;
	
	@Column(name = "address_line_4")
	private String addressLine4;
	
	@Column(name = "state", nullable = false)
	private String state;
	
	@Column(name = "postcode", nullable = false)
	private String postCode;
	
	@Column(name = "country", nullable = false)
	private String country;
	
	@Column(name = "created_at", nullable = false)
	private String createdAt;
	
	@Column(name = "created_by", nullable = false)
	private String createdBy;
	
	@Column(name = "modified_at")
	private String modifiedAt;
	
	@Column(name = "modified_by")
	private String modifiedBy;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public AddressType getAdressType() {
		return adressType;
	}

	public void setAdressType(AddressType adressType) {
		this.adressType = adressType;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getAddressLine4() {
		return addressLine4;
	}

	public void setAddressLine4(String addressLine4) {
		this.addressLine4 = addressLine4;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
