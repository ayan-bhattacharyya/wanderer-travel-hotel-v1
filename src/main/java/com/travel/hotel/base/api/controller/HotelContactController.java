package com.travel.hotel.base.api.controller;

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

import com.travel.hotel.base.api.dto.HotelContactDTO;
import com.travel.hotel.base.api.service.HotelContactCrudServiceImpl;
import com.travel.hotel.base.entity.HotelContact;
import com.travel.hotel.base.exception.ElementExistsException;
import com.travel.hotel.base.exception.ElementUpdatedException;
import com.travel.hotel.base.exception.InputMappingException;

@RestController
@RequestMapping(path = "hotel/{hotel_code}/contacts")
public class HotelContactController {

	private HotelContactCrudServiceImpl hotelContactCrudServiceImpl;

	@Autowired
	public HotelContactController(HotelContactCrudServiceImpl hotelContactCrudServiceImpl) {
		this.hotelContactCrudServiceImpl = hotelContactCrudServiceImpl;
	}

	protected HotelContactController() {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HotelContact createHotelContact(@RequestBody @Validated HotelContactDTO input,
			@PathVariable("hotel_code") String hotelCode) {
		return hotelContactCrudServiceImpl.createHotelContact(input, hotelCode);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<HotelContact> getHotelContact(@PathVariable("hotel_code") String hotelCode) {
		//TODO - To be implemented
		return null;
	}
	
	@GetMapping(params = "params")
	@ResponseStatus(HttpStatus.OK)
	public HotelContact getHotelContact(@PathVariable("hotel_code") String hotelCode, @RequestParam String params) {
		//TODO - To be implemented
		return null;
	}
	
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public HotelContact updateHotelContact(@RequestBody @Validated HotelContactDTO input,
			@PathVariable("hotel_code") String hotelCode) {
		return hotelContactCrudServiceImpl.updateHotelContact(input, hotelCode);
	}

	@DeleteMapping()
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllHotelContact(@PathVariable("hotel_code") String hotelCode) {
		hotelContactCrudServiceImpl.deleteAllHotelContact(hotelCode);
	}

	@DeleteMapping(params = { "address_type" })
	@ResponseStatus(HttpStatus.OK)
	public void deleteHotelContact(@PathVariable("hotel_code") String hotelCode, @RequestParam String address_type) {
		hotelContactCrudServiceImpl.deleteHotelContact(hotelCode, address_type);
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
