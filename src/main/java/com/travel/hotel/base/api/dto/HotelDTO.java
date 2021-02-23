package com.travel.hotel.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelDTO {

	@JsonProperty("hotelCode")
	private String hotelCode;

	@JsonProperty("hotelName")
	private String hotelName;

	@JsonProperty("status")
	private String status;

	@JsonProperty("readDateTime")
	private String readDateTime;

	@JsonProperty("user")
	private String user;

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
