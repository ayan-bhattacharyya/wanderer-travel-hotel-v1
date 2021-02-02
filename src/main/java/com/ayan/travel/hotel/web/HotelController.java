package com.ayan.travel.hotel.web;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayan.travel.hotel.domain.dto.HotelResponseDTO;
import com.ayan.travel.hotel.domain.dto.HotelRequestDTO;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelAddress;
import com.ayan.travel.hotel.entity.HotelContact;
import com.ayan.travel.hotel.service.HotelService;

@RestController
public class HotelController {
	
	private HotelService hotelService;
	private HotelResponseDTO response;
	
	@Autowired
	public HotelController(HotelService hotelService, HotelResponseDTO response) {
		this.hotelService = hotelService;
		this.response = response;
	}
	
	protected HotelController() {
		
	}
	
	@PostMapping("/hotel")
	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public HotelResponseDTO createHotel(@RequestBody @Validated HotelRequestDTO input) {
		
		response.setHotel(hotelService.createHotel(input));
		response.setHotelAddress(hotelService.createHotelAddress(input));
		response.setHotelContact(hotelService.updateHotelContact(input));
		
		return response;
		
	}
	
	@PostMapping("/hotel/address")
	@ResponseStatus(HttpStatus.CREATED)
	public HotelResponseDTO createHotelAddress(@RequestBody @Validated HotelRequestDTO input) {
		response.setHotelAddress(hotelService.createHotelAddress(input));
		
		return response;
	}
	
	@PostMapping("/hotel/contact")
	@ResponseStatus(HttpStatus.CREATED)
	public HotelResponseDTO createHotelContact(@RequestBody @Validated HotelRequestDTO input) {
		response.setHotelContact(hotelService.createHotelContact(input));
		
		return response;
	}
	
	
	@PutMapping("/hotel")
	@ResponseStatus(HttpStatus.OK)
	public HotelResponseDTO updateHotel(@RequestBody @Validated HotelRequestDTO input) {
		response.setHotel(hotelService.updateHotel(input));
		return response;
	}
	
	@PutMapping("/hotel/address")
	@ResponseStatus(HttpStatus.OK)
	public HotelResponseDTO updateHotelAddress(@RequestBody @Validated HotelRequestDTO input) {
		response.setHotelAddress(hotelService.updateHotelAddress(input));
		
		return response;
	}
	
	@PutMapping("/hotel/contact")
	@ResponseStatus(HttpStatus.OK)
	public HotelResponseDTO updateHotelContact(@RequestBody @Validated HotelRequestDTO input) {
		response.setHotelContact(hotelService.updateHotelContact(input));
		
		return response;
	}
	
	@PatchMapping("/hotel/deactivate")
	@ResponseStatus(HttpStatus.OK)
	public HotelResponseDTO decativateHotel(@RequestBody @Validated HotelRequestDTO input) {
		response.setHotel(hotelService.inactiveHotel(input));
		
		return response;
	}

}
