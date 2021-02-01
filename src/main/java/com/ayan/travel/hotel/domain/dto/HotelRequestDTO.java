package com.ayan.travel.hotel.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelRequestDTO {
	
	@JsonProperty("EventType")
	private String eventType;
	
	@JsonProperty("HotelCode")
	private String hotelCode;
	
	@JsonProperty("HotelName")
	private String hotelName;
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("AddressType")
	private String addressType;
	
	@JsonProperty("AddressLine1")
	private String addressLine1;
	
	@JsonProperty("AddressLine2")
	private String addressLine2;
	
	@JsonProperty("AddressLine3")
	private String addressLine3;
	
	@JsonProperty("AddressLine4")
	private String addressLine4;
	
	@JsonProperty("State")
	private String state;
	
	@JsonProperty("Postcode")
	private String postcode;
	
	@JsonProperty("Country")
	private String country;
	
	@JsonProperty("ContactType")
	private String contactType;
	
	@JsonProperty("ContactValue")
	private String contactValue;
	
	@JsonProperty("ModifiedDateTime")
	private String modifiedAt;
	
	@JsonProperty("ResponsibleUser")
	private String responsibleUser;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
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

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getResponsibleUser() {
		return responsibleUser;
	}

	public void setResponsibleUser(String responsibleUser) {
		this.responsibleUser = responsibleUser;
	}
	
}
