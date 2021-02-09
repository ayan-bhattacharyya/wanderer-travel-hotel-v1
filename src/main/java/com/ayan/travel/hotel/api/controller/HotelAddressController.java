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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ayan.travel.hotel.api.dto.HotelAddressRequestDTO;

import com.ayan.travel.hotel.api.service.HotelAddressApiServiceImpl;

import com.ayan.travel.hotel.entity.HotelAddress;
import com.ayan.travel.hotel.exception.ElementExistsException;
import com.ayan.travel.hotel.exception.ElementUpdatedException;
import com.ayan.travel.hotel.exception.InputMappingException;


@RestController
@RequestMapping(path = "hotel/{hotel_code}/address")
public class HotelAddressController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelAddressController.class);

	private HotelAddressApiServiceImpl hotelAddressApiServiceImpl;

	@Autowired
	public HotelAddressController(HotelAddressApiServiceImpl hotelAddressApiServiceImpl) {
		this.hotelAddressApiServiceImpl = hotelAddressApiServiceImpl;
	}

	protected HotelAddressController() {

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HotelAddress createHotelAddress(@RequestBody @Validated HotelAddressRequestDTO input,
			@PathVariable("hotel_code") String hotelCode) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Input for hotel address Create {}", input);
			LOGGER.info("Hotel code {}", hotelCode);
		}
		return hotelAddressApiServiceImpl.createHotelAddress(input, hotelCode);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<HotelAddress> getHotelAddress(@PathVariable("hotel_code") String hotelCode) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel code {}", hotelCode);
		}
		
		return hotelAddressApiServiceImpl.getAllAddresses(hotelCode);
	}
	
	@GetMapping(params = "address_type")
	@ResponseStatus(HttpStatus.OK)
	public HotelAddress getHotelAddress(@PathVariable("hotel_code") String hotelCode, @RequestParam String address_type) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Address Type {}", address_type);
			LOGGER.info("Hotel code {}", hotelCode);
		}
		return hotelAddressApiServiceImpl.getAddressByType(hotelCode, address_type);
	}
	
	
	@PutMapping
	@ResponseStatus(HttpStatus.OK)
	public HotelAddress updateHotelAddress(@RequestBody @Validated HotelAddressRequestDTO input,
			@PathVariable("hotel_code") String hotelCode) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Input for hotel address Create {}", input);
			LOGGER.info("Hotel code {}", hotelCode);
		}
		
		return hotelAddressApiServiceImpl.updateHotelAddress(input, hotelCode);
	}

	@DeleteMapping()
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllHotelAddress(@PathVariable("hotel_code") String hotelCode) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel code {}", hotelCode);
		}
		
		hotelAddressApiServiceImpl.deleteAllHotelAddresses(hotelCode);
	}

	@DeleteMapping(params = { "address_type" })
	@ResponseStatus(HttpStatus.OK)
	public void deleteHotelAddress(@PathVariable("hotel_code") String hotelCode, @RequestParam String address_type) {
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Address Type {}", address_type);
			LOGGER.info("Hotel code {}", hotelCode);
		}
		
		hotelAddressApiServiceImpl.deleteHotelAddress(hotelCode, address_type);
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
