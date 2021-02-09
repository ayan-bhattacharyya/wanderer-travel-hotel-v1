package com.ayan.travel.hotel.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

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

import com.ayan.travel.hotel.api.dto.HotelContactRequestDTO;

import com.ayan.travel.hotel.api.service.HotelContactApiServiceImpl;
import com.ayan.travel.hotel.entity.HotelContact;
import com.ayan.travel.hotel.exception.ElementExistsException;
import com.ayan.travel.hotel.exception.ElementUpdatedException;
import com.ayan.travel.hotel.exception.InputMappingException;

@RestController
@RequestMapping(path = "hotel/{hotel_code}/contact")
public class HotelContactController {

	private HotelContactApiServiceImpl hotelContactApiServiceImpl;

	@Autowired
	public HotelContactController(HotelContactApiServiceImpl hotelContactApiServiceImpl) {
		this.hotelContactApiServiceImpl = hotelContactApiServiceImpl;
	}

	protected HotelContactController() {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HotelContact createHotelContact(@RequestBody @Validated HotelContactRequestDTO input,
			@PathVariable("hotel_code") String hotelCode) {
		return hotelContactApiServiceImpl.createHotelContact(input, hotelCode);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<HotelContact> getHotelContact(@PathVariable("hotel_code") String hotelCode) {
		return hotelContactApiServiceImpl.getAllContacts(hotelCode);
	}
	
	@GetMapping(params = "address_type")
	@ResponseStatus(HttpStatus.OK)
	public HotelContact getHotelContact(@PathVariable("hotel_code") String hotelCode, @RequestParam String address_type) {
		return hotelContactApiServiceImpl.getContactByType(hotelCode, address_type);
	}
	
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public HotelContact updateHotelContact(@RequestBody @Validated HotelContactRequestDTO input,
			@PathVariable("hotel_code") String hotelCode) {
		return hotelContactApiServiceImpl.updateHotelContact(input, hotelCode);
	}

	@DeleteMapping()
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllHotelContact(@PathVariable("hotel_code") String hotelCode) {
		hotelContactApiServiceImpl.deleteAllHotelContacts(hotelCode);
	}

	@DeleteMapping(params = { "address_type" })
	@ResponseStatus(HttpStatus.OK)
	public void deleteHotelContact(@PathVariable("hotel_code") String hotelCode, @RequestParam String address_type) {
		hotelContactApiServiceImpl.deleteHotelContact(hotelCode, address_type);
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
