package com.ayan.travel.hotel.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayan.travel.hotel.api.dto.HotelAddressRequestDTO;

import com.ayan.travel.hotel.api.service.HotelAddressService;

import com.ayan.travel.hotel.entity.HotelAddress;
import com.ayan.travel.hotel.exception.ElementExistsException;
import com.ayan.travel.hotel.exception.ElementUpdatedException;
import com.ayan.travel.hotel.exception.InputMappingException;

@RestController
@RequestMapping(path = "hotel/{hotel_id}/address")
public class HotelAddressController {

	private HotelAddressService hotelAddressService;

	@Autowired
	public HotelAddressController(HotelAddressService hotelAddressService) {
		this.hotelAddressService = hotelAddressService;
	}

	protected HotelAddressController() {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HotelAddress createHotelAddress(@RequestBody @Validated HotelAddressRequestDTO input,
			@PathVariable("hotel_id") String hotelId) {
		return hotelAddressService.createHotelAddress(input, Long.parseLong(hotelId));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<HotelAddress> getHotelAddress(@PathVariable("hotel_id") String hotelId) {
		return hotelAddressService.getAllAddress(Long.parseLong(hotelId));
	}
	
	@GetMapping(params = "address_type")
	@ResponseStatus(HttpStatus.OK)
	public HotelAddress getHotelAddress(@PathVariable("hotel_id") String hotelId, @RequestParam String address_type) {
		return hotelAddressService.getAddressByType(Long.parseLong(hotelId), address_type);
	}
	
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public HotelAddress updateHotelAddress(@RequestBody @Validated HotelAddressRequestDTO input,
			@PathVariable("hotel_id") String hotelId) {
		return hotelAddressService.updateHotelAddress(input, Long.parseLong(hotelId));
	}

	@DeleteMapping()
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllHotelAddress(@PathVariable("hotel_id") String hotelId) {
		hotelAddressService.deleteHotel(Long.parseLong(hotelId));
	}

	@DeleteMapping(params = { "address_type" })
	@ResponseStatus(HttpStatus.OK)
	public void deleteHotelAddress(@PathVariable("hotel_id") String hotelId, @RequestParam String address_type) {
		hotelAddressService.deleteHotel(Long.parseLong(hotelId), address_type);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String noElementFound(NoSuchElementException ex) {
		return ex.getMessage();
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ElementUpdatedException.class)
	public String elementUpdateConflict(ElementUpdatedException ex) {
		return ex.getMessage();
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InputMappingException.class)
	public String elementUpdateConflict(InputMappingException ex) {
		return ex.getMessage();
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(ElementExistsException.class)
	public String elementExistsConflict(ElementExistsException ex) {
		return ex.getMessage();
	}

}
