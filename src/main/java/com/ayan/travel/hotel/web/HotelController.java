package com.ayan.travel.hotel.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayan.travel.hotel.domain.dto.HotelRequestDTO;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.service.HotelService;

@RestController
public class HotelController {
	
	private HotelService hotelService;
	
	@Autowired
	public HotelController(HotelService hotelService) {
		this.hotelService = hotelService;
	}
	
	protected HotelController() {
		
	}
	
	@PostMapping("/hotel")
	public void createHotel(@RequestBody @Validated HotelRequestDTO input) {
		Map<Hotel, Map<HttpStatus, String>> response = hotelService.createHotel(input);
	}
	
	@PutMapping("/hotel")
	@ResponseStatus(HttpStatus.OK)
	public void updateHotel(@RequestBody @Validated HotelRequestDTO input) {
		hotelService.updateHotel(input);
	}
	
	@PatchMapping("/hotel/deactivate")
	@ResponseStatus(HttpStatus.OK)
	public void decativateHotel(@RequestBody @Validated HotelRequestDTO input) {
		hotelService.inactiveHotel(input);
	}

}
