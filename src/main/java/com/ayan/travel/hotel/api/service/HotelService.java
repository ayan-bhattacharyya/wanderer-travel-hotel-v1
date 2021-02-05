package com.ayan.travel.hotel.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayan.travel.hotel.api.dto.HotelAddressRequestDTO;
import com.ayan.travel.hotel.api.dto.HotelRequestDTO;
import com.ayan.travel.hotel.domain.Status;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.exception.ElementExistsException;
import com.ayan.travel.hotel.exception.ElementUpdatedException;
import com.ayan.travel.hotel.exception.InputMappingException;
import com.ayan.travel.hotel.repository.HotelAddressRepository;
import com.ayan.travel.hotel.repository.HotelContactRepository;
import com.ayan.travel.hotel.repository.HotelRepository;
import com.ayan.travel.hotel.service.DateTimeService;

@Service
public class HotelService {

	private static final String DATE_FORMATTER = "dd-MM-yyyy'T'HH:mm:ss'Z'";
	private static final String HOTEL_CODE_MISSING = "A mandatory field 'Hotel code' is missing";
	private static final String HOTEL_NAME_MISSING = "A mandatory field 'Hotel name' is missing";
	private static final String USER_MISSING = "A mandatory field 'User' is missing";
	private static final String RESORCE_ALREADY_UPDATED = "The requested resource has already been updated by another user since it is been read";
	private static final String HOTEL_ALREADY_EXISTS = "A hotel with requested hotel code exists in the system";
	private static final String HOTEL_NOT_EXISTS = "A Hotel with requested hotel code either not exists or incative";

	private HotelRepository hotelRepository;
	private HotelAddressRepository hotelAddressRepository;
	private HotelContactRepository hotelContactRepository;

	@Autowired
	public HotelService(HotelRepository hotelRepository, HotelAddressRepository hotelAddressRepository,
			HotelContactRepository hotelContactRepository) {
		this.hotelRepository = hotelRepository;
		this.hotelAddressRepository = hotelAddressRepository;
		this.hotelContactRepository = hotelContactRepository;
	}

	public Hotel createHotel(HotelRequestDTO input) {
		DateTimeService dateTimeService = new DateTimeService();
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);

		 if (hotelRepository.findByHotelCode(input.getHotelCode()).size() > 0) {
			throw new ElementExistsException(HOTEL_ALREADY_EXISTS);

		} else if (StringUtils.isBlank(input.getStatus())) {
			return hotelRepository.save(new Hotel(input.getHotelCode(), input.getHotelName(), Status.A, createdAt,
					input.getResponsibleUser()));
		} else {
			return hotelRepository.save(new Hotel(input.getHotelCode(), input.getHotelName(),
					Status.findByLabel(input.getStatus()), createdAt, input.getResponsibleUser()));
		}
	}

	public Hotel updateHotel(HotelRequestDTO input) {
		DateTimeService dateTimeService = new DateTimeService();
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if (getHotelByCode(input.getHotelCode()) == null) {
			throw new NoSuchElementException(HOTEL_NOT_EXISTS);

		} else if (StringUtils.isBlank(input.getModifiedAt())) {
			throw new ElementUpdatedException(RESORCE_ALREADY_UPDATED);

		} else {
			Hotel hotel = getHotelByCode(input.getHotelCode());
			if(StringUtils.isBlank(hotel.getModifiedAt())) {
				hotel.setHotelName(input.getHotelName());
				hotel.setModifiedAt(updatedAt);
				hotel.setModifiedBy(input.getResponsibleUser());
				return hotelRepository.save(hotel);
			} else {
				throw new ElementUpdatedException(RESORCE_ALREADY_UPDATED);
			}
		}
	}

	public void deleteHotel(String hotelCode) {
		if (hotelRepository.findByHotelCode(hotelCode).size() == 0) {
			throw new NoSuchElementException(HOTEL_NOT_EXISTS);

		} else if (hotelRepository.findByHotelCode(hotelCode).size() > 1) {
			throw new RuntimeException("More than one hotel exists for the hotel code " + hotelCode);

		} else {
			Hotel hotel = hotelRepository.findByHotelCode(hotelCode).get(0);
			if (hotelAddressRepository.findByHotel(hotel).size() > 0) {
				throw new RuntimeException("Hotel Address Exists");

			} else if (hotelContactRepository.findByHotel(hotel).size() > 0) {
				throw new RuntimeException("Hotel Contact Exists");

			} else {
				hotelRepository.delete(hotel);
			}
		}

	}

	public Hotel getHotelByCode(String hotelCode) {
		if (hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).size() == 0) {
			return null;
		} else if (hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).size() > 1) {
			throw new RuntimeException("More than one active hotel exists for the hotel code " + hotelCode);
		} else {
			return hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).get(0);
		}
	}

	public List<Hotel> getHotelByName(String hotelName) {
		return hotelRepository.findByHotelNameAndStatus(hotelName, Status.A);
	}

	public Hotel getHotelById(Long id) {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("No Hotel exists for this Id: " + id));
	}
	
	private void validateMandatoryFields(HotelRequestDTO input) {
		if (StringUtils.isBlank(input.getHotelCode())) {
			throw new InputMappingException(HOTEL_CODE_MISSING);

		} else if (StringUtils.isBlank(input.getHotelName())) {
			throw new InputMappingException(HOTEL_NAME_MISSING);

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new InputMappingException(USER_MISSING);

		}
	}
}
