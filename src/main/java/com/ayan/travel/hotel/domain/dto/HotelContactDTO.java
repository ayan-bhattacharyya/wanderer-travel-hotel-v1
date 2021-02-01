package com.ayan.travel.hotel.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelContactDTO {
	
	@JsonProperty("ContactType")
	private String contactType;
	
	@JsonProperty("ContactValue")
	private String contactValue;

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

}
