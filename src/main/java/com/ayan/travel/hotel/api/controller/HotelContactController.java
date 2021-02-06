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

import com.ayan.travel.hotel.api.service.HotelContactService;
import com.ayan.travel.hotel.entity.HotelContact;
import com.ayan.travel.hotel.exception.ElementExistsException;
import com.ayan.travel.hotel.exception.ElementUpdatedException;
import com.ayan.travel.hotel.exception.InputMappingException;

@RestController
@RequestMapping(path = "hotel/{hotel_id}/contact")
public class HotelContactController {

	private HotelContactService hotelContactService;

	@Autowired
	public HotelContactController(HotelContactService hotelContactService) {
		this.hotelContactService = hotelContactService;
	}

	protected HotelContactController() {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HotelContact createHotelContact(@RequestBody @Validated HotelContactRequestDTO input,
			@PathVariable("hotel_id") String hotelId) {
		return hotelContactService.createHotelContact(input, Long.parseLong(hotelId));
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<HotelContact> getHotelContact(@PathVariable("hotel_id") String hotelId) {
		return hotelContactService.getAllContacts(Long.parseLong(hotelId));
	}
	
	@GetMapping(params = "address_type")
	@ResponseStatus(HttpStatus.OK)
	public HotelContact getHotelContact(@PathVariable("hotel_id") String hotelId, @RequestParam String address_type) {
		return hotelContactService.getContactByType(Long.parseLong(hotelId), address_type);
	}
	
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public HotelContact updateHotelContact(@RequestBody @Validated HotelContactRequestDTO input,
			@PathVariable("hotel_id") String hotelId) {
		return hotelContactService.updateHotelContact(input, Long.parseLong(hotelId));
	}

	@DeleteMapping()
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllHotelContact(@PathVariable("hotel_id") String hotelId) {
		hotelContactService.deleteAllHotelContacts(Long.parseLong(hotelId));
	}

	@DeleteMapping(params = { "address_type" })
	@ResponseStatus(HttpStatus.OK)
	public void deleteHotelContact(@PathVariable("hotel_id") String hotelId, @RequestParam String address_type) {
		hotelContactService.deleteHotelContact(Long.parseLong(hotelId), address_type);
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
