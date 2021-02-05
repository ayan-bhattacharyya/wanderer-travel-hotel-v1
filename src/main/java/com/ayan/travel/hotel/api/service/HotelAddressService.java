package com.ayan.travel.hotel.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
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
	private static final String HOTEL_ID_MISSING = "A mandatory field 'Hotel Id' is missing";
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

	private HotelAddressRepository hotelAddressRepository;
	private HotelService hotelService;

	@Autowired
	public HotelAddressService(HotelAddressRepository hotelAddressRepository, HotelService hotelService) {
		this.hotelAddressRepository = hotelAddressRepository;
		this.hotelService = hotelService;
	}

	public HotelAddress createHotelAddress(HotelAddressRequestDTO input, Long hotelId) {
		DateTimeService dateTimeService = new DateTimeService();
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);
		validateMandatoryFields(input, hotelId);
		
		Hotel hotel = hotelService.getHotelById(hotelId);
		
		if(getAddressByType(hotelId, input.getAddressType()) != null) {
			throw new ElementExistsException(HOTEL_ADDRESS_ALREADY_EXISTS);
			
		} else {
			return hotelAddressRepository.save(new HotelAddress(hotel,
					AddressType.findByLabel(input.getAddressType()), input.getAddressLine1(), input.getAddressLine2(),
					input.getAddressLine3(), input.getCity(), input.getState(), input.getPostcode(),
					input.getCountry(), createdAt, input.getResponsibleUser()));
		}
	}

	public HotelAddress updateHotelAddress(HotelAddressRequestDTO input, Long hotelId) {
		DateTimeService dateTimeService = new DateTimeService();
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);
		validateMandatoryFields(input, hotelId);
		
		if(StringUtils.isBlank(input.getModifiedAt())) {
			throw new ElementUpdatedException(RESORCE_ALREADY_UPDATED);
			
		} else if(getAddressByType(hotelId, input.getAddressType()) == null) {
			throw new NoSuchElementException(HOTEL_ADDRESS_NOT_EXISTS);
			
		} else {
			HotelAddress hotelAddress = getAddressByType(hotelId, input.getAddressType());
			
			if(StringUtils.isBlank(hotelAddress.getModifiedAt())) {
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
	
	public void deleteHotel(Long hotelId) {
		Hotel hotel = hotelService.getHotelById(hotelId);
		List<HotelAddress> hotelAddresses = hotelAddressRepository.findByHotel(hotel);
		hotelAddressRepository.deleteAll(hotelAddresses);
		
	}
	
	public void deleteHotel(Long hotelId, String addressType) {
		HotelAddress hotelAddress = getAddressByType(hotelId, addressType);
		hotelAddressRepository.delete(hotelAddress);
	}

	public HotelAddress getAddressByType(Long hotelId, String addressTypeStr) {
		Hotel hotel = hotelService.getHotelById(hotelId);
		AddressType addressType = AddressType.findByLabel(addressTypeStr);
		
		if (hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).size() == 0) {
			return null;
		} else if (hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the adress type " + addressTypeStr);
		} else {
			return hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).get(0);
		}
	}
	
	public List<HotelAddress> getAllAddress(Long hotelId) {
		return hotelAddressRepository.findByHotel(hotelService.getHotelById(hotelId));
		
	}
	
	private void validateMandatoryFields(HotelAddressRequestDTO input, Long hotelId) {
		if (hotelId == null) {
			throw new InputMappingException(HOTEL_ID_MISSING);

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

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new InputMappingException(USER_MISSING);

		}
	}
	
	
}
