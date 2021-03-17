package com.travel.hotel.base.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoomDTO {

	@JsonProperty("name")
	private String name;

	@JsonProperty("number")
	private String number;

	@JsonProperty("type")
	private String type;

	@JsonProperty("tariff")
	private double tariff;

	@JsonProperty("status")
	private String status;

	@JsonProperty("readDateTime")
	private String readDateTime;

	@JsonProperty("user")
	private String user;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getTariff() {
		return tariff;
	}

	public void setTariff(double tariff) {
		this.tariff = tariff;
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
