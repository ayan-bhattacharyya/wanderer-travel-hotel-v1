package com.ayan.travel.hotel.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelRequestDTO {

	@JsonProperty("HotelCode")
	private String hotelCode;

	@JsonProperty("HotelName")
	private String hotelName;

	@JsonProperty("Status")
	private String status;

	@JsonProperty("ModifiedDateTime")
	private String modifiedAt;

	@JsonProperty("ResponsibleUser")
	private String responsibleUser;

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
