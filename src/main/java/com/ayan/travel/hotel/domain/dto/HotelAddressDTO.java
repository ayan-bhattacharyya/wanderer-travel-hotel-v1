package com.ayan.travel.hotel.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelAddressDTO {

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

}
