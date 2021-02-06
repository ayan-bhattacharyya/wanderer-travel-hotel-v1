package com.ayan.travel.hotel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelAddressRequestDTO {
	
	@JsonProperty("AddressType")
	private String addressType;

	@JsonProperty("AddressLine1")
	private String addressLine1;

	@JsonProperty("AddressLine2")
	private String addressLine2;

	@JsonProperty("AddressLine3")
	private String addressLine3;

	@JsonProperty("City")
	private String city;

	@JsonProperty("State")
	private String state;

	@JsonProperty("Postcode")
	private String postcode;

	@JsonProperty("Country")
	private String country;
	
	@JsonProperty("ModifiedDateTime")
	private String modifiedAt;
	
	@JsonProperty("ResponsibleUser")
	private String responsibleUser;

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
