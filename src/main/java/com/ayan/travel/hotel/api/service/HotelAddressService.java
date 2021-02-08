package com.ayan.travel.hotel.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayan.travel.hotel.api.dto.HotelAddressRequestDTO;
import com.ayan.travel.hotel.domain.AddressType;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelAddress;
import com.ayan.travel.hotel.exception.ElementExistsException;
import com.ayan.travel.hotel.exception.ElementUpdatedException;
import com.ayan.travel.hotel.exception.InputMappingException;
import com.ayan.travel.hotel.repository.HotelAddressRepository;
import com.ayan.travel.hotel.service.DateTimeService;

@Service
public class HotelAddressService {

	private static final String DATE_FORMATTER = "dd-MM-yyyy'T'HH:mm:ss'Z'";
	private static final String HOTEL_ADDRESS_TYPE_MISSING = "A mandatory field 'Hotel address type' is missing";
	private static final String HOTEL_ADDRESS_LINE_1_MISSING = "A mandatory field 'Hotel address line 1' is missing";
	private static final String HOTEL_CITY_MISSING = "A mandatory field 'Hotel address city' is missing";
	private static final String HOTEL_ADDRESS_STATE_MISSING = "A mandatory field 'Hotel address state' is missing";
	private static final String HOTEL_ADDRESS_POSTCODE_MISSING = "A mandatory field 'Hotel address postcode' is missing";
	private static final String HOTEL_ADDRESS_COUNTRY_MISSING = "A mandatory field 'Hotel address country' is missing";
	private static final String USER_MISSING = "A mandatory field 'User' is missing";
	private static final String RESORCE_ALREADY_UPDATED = "The requested resource has already been updated by another user since it is been read";
	private static final String HOTEL_ADDRESS_ALREADY_EXISTS = "A hotel address with requested hotel code and address type exists in the system";
	private static final String HOTEL_ADDRESS_NOT_EXISTS = "A hotel address with requested hotel code and address type doesn't exist in the system";
	private static final String HOTEL_NOT_EXISTS = "A hotel with requested hotel code doesn't exists in the system";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelAddressService.class);
	
	private HotelAddressRepository hotelAddressRepository;
	private HotelService hotelService;

	@Autowired
	public HotelAddressService(HotelAddressRepository hotelAddressRepository, HotelService hotelService) {
		this.hotelAddressRepository = hotelAddressRepository;
		this.hotelService = hotelService;
	}

	public HotelAddress createHotelAddress(HotelAddressRequestDTO input, String hotelCode) {
		DateTimeService dateTimeService = new DateTimeService();
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel address create operation started at {}", createdAt);
		}
		
		validateMandatoryFields(input, hotelCode);
		
		Long hotelId = hotelService.getHotelByCode(hotelCode).getHotelId();
		Hotel hotel = hotelService.getHotelById(hotelId);
		
		if(getAddressByType(hotelCode, input.getAddressType()) != null) {
			throw new ElementExistsException(HOTEL_ADDRESS_ALREADY_EXISTS);
			
		} else {
			return hotelAddressRepository.save(new HotelAddress(hotel,
					AddressType.findByLabel(input.getAddressType()), input.getAddressLine1(), input.getAddressLine2(),
					input.getAddressLine3(), input.getCity(), input.getState(), input.getPostcode(),
					input.getCountry(), createdAt, input.getResponsibleUser()));
		}
	}

	public HotelAddress updateHotelAddress(HotelAddressRequestDTO input, String hotelCode) {
		DateTimeService dateTimeService = new DateTimeService();
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel address update operation started at {}", updatedAt);
		}
		
		validateMandatoryFields(input, hotelCode);
		
		HotelAddress hotelAddress = getAddressByType(hotelCode, input.getAddressType());
		
		if(hotelAddress == null) {
			throw new NoSuchElementException(HOTEL_ADDRESS_NOT_EXISTS);
			
		} else {
			
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Latest Update Date time {}", hotelAddress.getModifiedAt());
			}
			
			if(StringUtils.isBlank(input.getModifiedAt()) && 
					StringUtils.isNotBlank(hotelAddress.getModifiedAt())) {
				throw new ElementUpdatedException(RESORCE_ALREADY_UPDATED);
				
			} else if (StringUtils.isBlank(hotelAddress.getModifiedAt())
					|| input.getModifiedAt().equals(hotelAddress.getModifiedAt())) {
				hotelAddress.setAddressLine1(input.getAddressLine1());
				hotelAddress.setAddressLine2(input.getAddressLine2());
				hotelAddress.setAddressLine3(input.getAddressLine3());
				hotelAddress.setCity(input.getCity());
				hotelAddress.setState(input.getState());
				hotelAddress.setPostcode(input.getPostcode());
				hotelAddress.setCountry(input.getCountry());
				hotelAddress.setModifiedAt(updatedAt);
				hotelAddress.setModifiedBy(input.getResponsibleUser());

				return hotelAddressRepository.save(hotelAddress);
				
			} else {
				throw new ElementUpdatedException(RESORCE_ALREADY_UPDATED);
			}
		}
	}
	
	public void deleteAllHotelAddresses(String hotelCode) {
		
		DateTimeService dateTimeService = new DateTimeService();
		String deletedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel delete operation started at {}", deletedAt);
		}
		
		if (hotelService.getHotelByCode(hotelCode) == null) {
			throw new NoSuchElementException(HOTEL_NOT_EXISTS);
		}
		
		Hotel hotel = hotelService.getHotelByCode(hotelCode);
		List<HotelAddress> hotelAddresses = hotelAddressRepository.findByHotel(hotel);
		hotelAddressRepository.deleteAll(hotelAddresses);
		
	}
	
	public void deleteHotelAddress(String hotelCode, String addressType) {
		
		DateTimeService dateTimeService = new DateTimeService();
		String deletedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel delete operation started at {}", deletedAt);
		}
		
		if (hotelService.getHotelByCode(hotelCode) == null) {
			throw new NoSuchElementException(HOTEL_NOT_EXISTS);
		}
		
		HotelAddress hotelAddress = getAddressByType(hotelCode, addressType);
		hotelAddressRepository.delete(hotelAddress);
	}

	public HotelAddress getAddressByType(String hotelCode, String addressTypeStr) {
		
		if (hotelService.getHotelByCode(hotelCode) == null) {
			throw new NoSuchElementException(HOTEL_NOT_EXISTS);
		}
		
		Hotel hotel = hotelService.getHotelById(hotelService.getHotelByCode(hotelCode).getHotelId());
		AddressType addressType = AddressType.findByLabel(addressTypeStr);
		
		if (hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).size() == 0) {
			return null;
		} else if (hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the adress type " + addressTypeStr);
		} else {
			HotelAddress hotelAddress = hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).get(0);
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Hotel address details {}", hotelAddress);
			}
			return hotelAddress;
		}
	}
	
	public List<HotelAddress> getAllAddresses(String hotelCode) {
		
		if (hotelService.getHotelByCode(hotelCode) == null) {
			throw new NoSuchElementException(HOTEL_NOT_EXISTS);
		}
		
		return hotelAddressRepository.findByHotel(hotelService.getHotelByCode(hotelCode));
		
	}
	
	private Boolean validateMandatoryFields(HotelAddressRequestDTO input, String hotelCode) {
		if (hotelService.getHotelByCode(hotelCode) == null) {
			throw new NoSuchElementException(HOTEL_NOT_EXISTS);

		} else if (StringUtils.isBlank(input.getAddressType())) {
			throw new InputMappingException(HOTEL_ADDRESS_TYPE_MISSING);

		} else if (StringUtils.isBlank(input.getAddressLine1())) {
			throw new InputMappingException(HOTEL_ADDRESS_LINE_1_MISSING);

		} else if (StringUtils.isBlank(input.getCity())) {
			throw new InputMappingException(HOTEL_CITY_MISSING);

		} else if (StringUtils.isBlank(input.getState())) {
			throw new InputMappingException(HOTEL_ADDRESS_STATE_MISSING);

		} else if (StringUtils.isBlank(input.getPostcode())) {
			throw new InputMappingException(HOTEL_ADDRESS_POSTCODE_MISSING);

		} else if (StringUtils.isBlank(input.getCountry())) {
			throw new InputMappingException(HOTEL_ADDRESS_COUNTRY_MISSING);

		} else if(StringUtils.isBlank(input.getResponsibleUser())) {
			throw new InputMappingException(USER_MISSING);

		} else {
			return Boolean.TRUE;
		}
	}
	
	
}
