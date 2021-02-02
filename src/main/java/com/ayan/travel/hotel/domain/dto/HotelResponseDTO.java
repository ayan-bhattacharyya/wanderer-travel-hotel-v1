package com.ayan.travel.hotel.domain.dto;

import java.util.List;

import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelAddress;
import com.ayan.travel.hotel.entity.HotelContact;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelResponseDTO {
	
	@JsonProperty("Hotel")
	private Hotel hotel;
	
	@JsonProperty("HotelAddress")
	private HotelAddress hotelAddress;
	
	@JsonProperty("HotelContact")
	private HotelContact hotelContact;
	
	@JsonProperty("ErrorMessage")
	private String errorMeesage;

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public HotelAddress getHotelAddress() {
		return hotelAddress;
	}

	public void setHotelAddress(HotelAddress hotelAddress) {
		this.hotelAddress = hotelAddress;
	}

	public HotelContact getHotelContact() {
		return hotelContact;
	}

	public void setHotelContact(HotelContact hotelContact) {
		this.hotelContact = hotelContact;
	}

	public String getErrorMeesage() {
		return errorMeesage;
	}

	public void setErrorMeesage(String errorMeesage) {
		this.errorMeesage = errorMeesage;
	}

}
