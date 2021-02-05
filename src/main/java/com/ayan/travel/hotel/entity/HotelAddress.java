package com.ayan.travel.hotel.entity;

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

import com.ayan.travel.hotel.domain.AddressType;

@Entity
@Table(name = "hotel_address")
public class HotelAddress implements Serializable {

	private static final long serialVersionUID = 1740869414999752409L;

	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long addressId;
	
	@ManyToOne
    @JoinColumn(name="hotel_id")
	private Hotel hotel;

	@Column(name = "address_type", nullable = false)
	private AddressType addressType;

	@Column(name = "address_line_1", nullable = false)
	private String addressLine1;

	@Column(name = "address_line_2")
	private String addressLine2;

	@Column(name = "address_line_3")
	private String addressLine3;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "postcode", nullable = false)
	private String postcode;

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

	public HotelAddress(Hotel hotel, AddressType addressType, String addressLine1, String addressLine2,
			String addressLine3, String city, String state, String postcode, String country, String createdAt,
			String createdBy, String modifiedAt, String modifiedBy) {
		this.hotel = hotel;
		this.addressType = addressType;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.country = country;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.modifiedAt = modifiedAt;
		this.modifiedBy = modifiedBy;
	}
	
	public HotelAddress() {
		
	}

	public HotelAddress(Hotel hotel, AddressType addressType, String addressLine1, String addressLine2,
			String addressLine3, String city, String state, String postcode, String country, String createdAt,
			String createdBy) {
		this.hotel = hotel;
		this.addressType = addressType;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressLine3 = addressLine3;
		this.city = city;
		this.state = state;
		this.postcode = postcode;
		this.country = country;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}

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

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
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

	@Override
	public String toString() {
		return "Hotel Address{" + ", Hotel Code ='" + hotel.getHotelCode() + '\'' + ", Hotel Name ='"
				+ hotel.getHotelName() + '\'' + ", Hotel Address Id ='" + addressId + '\'' + ", Hotel Address Type ='"
				+ addressType + '\'' + ", Hotel Address Line 1 ='" + addressLine1 + '\'' + ", Hotel Address Line 2 ='"
				+ addressLine2 + '\'' + ", Hotel Address Line 3 ='" + addressLine3 + '\'' + ", city ='"
				+ city + '\'' + ", State ='" + state + '\'' + ", Postcode ='" + postcode + '\'' + ", Country ='"
				+ country + '\'' + ", Created at ='" + createdAt + '\'' + ", Created by ='" + createdBy + '\''
				+ ", Modified at ='" + modifiedAt + '\'' + ", Modified by ='" + modifiedBy + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HotelAddress hotelAddress = (HotelAddress) o;
		return Objects.equals(addressId, hotelAddress.addressId) && Objects.equals(hotel, hotelAddress.hotel)
				&& addressType == hotelAddress.addressType && Objects.equals(addressLine1, hotelAddress.addressLine1)
				&& Objects.equals(addressLine2, hotelAddress.addressLine2)
				&& Objects.equals(addressLine3, hotelAddress.addressLine3)
				&& Objects.equals(city, hotelAddress.city) && Objects.equals(state, hotelAddress.state)
				&& Objects.equals(postcode, hotelAddress.postcode) && Objects.equals(country, hotelAddress.country)
				&& Objects.equals(createdAt, hotelAddress.createdAt)
				&& Objects.equals(createdBy, hotelAddress.createdBy)
				&& Objects.equals(modifiedAt, hotelAddress.modifiedAt)
				&& Objects.equals(modifiedBy, hotelAddress.modifiedBy);
	}
	

}
