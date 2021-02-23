package com.travel.hotel.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelContactDTO {

	@JsonProperty("contactType")
	private String contactType;

	@JsonProperty("contactValue")
	private String contactValue;

	@JsonProperty("readDateTime")
	private String readDateTime;

	@JsonProperty("user")
	private String user;

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

	public String getReadDateTime() {
		return readDateTime;
	}

	public void setReadDateTime(String readDateTime) {
		this.readDateTime = readDateTime;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
