package com.ayan.travel.hotel.domain.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResponseDTO {
	
	@JsonProperty("HotelCode")
	private String hotelCode;
	
	@JsonProperty("HotelName")
	private String hotelName;
	
	@JsonProperty("HotelAddresses")
	private List<HotelAddressDTO> addresses;
	
	@JsonProperty("HotelContacts")
	private List<HotelContactDTO> contacts;

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

	public List<HotelAddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<HotelAddressDTO> addresses) {
		this.addresses = addresses;
	}

	public List<HotelContactDTO> getContacts() {
		return contacts;
	}

	public void setContacts(List<HotelContactDTO> contacts) {
		this.contacts = contacts;
	}

}
