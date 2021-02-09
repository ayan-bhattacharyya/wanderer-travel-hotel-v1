package com.ayan.travel.hotel.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayan.travel.hotel.api.dto.HotelRequestDTO;
import com.ayan.travel.hotel.api.service.HotelApiServiceImpl;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.exception.ElementExistsException;
import com.ayan.travel.hotel.exception.ElementUpdatedException;
import com.ayan.travel.hotel.exception.InputMappingException;

@RestController
@RequestMapping(path = "/hotel")
public class HotelController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelController.class);

	private HotelApiServiceImpl hotelApiServiceImpl;

	@Autowired
	public HotelController(HotelApiServiceImpl hotelApiServiceImpl) {
		this.hotelApiServiceImpl = hotelApiServiceImpl;
	}

	protected HotelController() {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Hotel createHotel(@RequestBody @Validated HotelRequestDTO input) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Input for hotel address Create {}", input);
		}
		
		return hotelApiServiceImpl.createHotel(input);
	}
	
	@GetMapping(params = "hotel_code")
	@ResponseStatus(HttpStatus.OK)
	public Hotel getHotelByHotelCode(@RequestParam String hotel_code) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel code {}", hotel_code);
		}
		
		return hotelApiServiceImpl.getHotelByCode(hotel_code);
	}
	
	@GetMapping(params = "hotel_name")
	@ResponseStatus(HttpStatus.OK)
	public List<Hotel> getHotelByName(@RequestParam String hotel_name) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel name {}", hotel_name);
		}
		
		return hotelApiServiceImpl.getHotelByName(hotel_name);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public Hotel updateHotel(@RequestBody @Validated HotelRequestDTO input) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Input for hotel address Create {}", input);
		}
		
		return hotelApiServiceImpl.updateHotel(input);

	}
	
	@DeleteMapping("/{hotel_code}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteHotel(@PathVariable("hotel_code") String hotelCode) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel code {}", hotelCode);
		}
		
		hotelApiServiceImpl.deleteHotel(hotelCode);
	}

	@PatchMapping("/deactivate")
	@ResponseStatus(HttpStatus.OK)
	public Hotel decativateHotel(@RequestBody @Validated HotelRequestDTO input) {
		/*
		 * TODO Implement the method
		 */

		return null;
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
