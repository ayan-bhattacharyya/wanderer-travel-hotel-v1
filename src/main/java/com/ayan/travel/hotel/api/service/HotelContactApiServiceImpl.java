package com.ayan.travel.hotel.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayan.travel.hotel.api.dto.HotelContactRequestDTO;
import com.ayan.travel.hotel.domain.ContactType;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelContact;
import com.ayan.travel.hotel.exception.ElementExistsException;
import com.ayan.travel.hotel.exception.ElementUpdatedException;
import com.ayan.travel.hotel.exception.InputMappingException;
import com.ayan.travel.hotel.repository.HotelContactRepository;
import com.ayan.travel.hotel.service.DateTimeService;

@Service
public class HotelContactApiServiceImpl {

	private static final String DATE_FORMATTER = "dd-MM-yyyy'T'HH:mm:ss'Z'";
	private static final String HOTEL_CONTACT_TYPE_MISSING = "A mandatory field 'Contact type' is missing";
	private static final String HOTEL_CONTACT_VALUE_MISSING = "A mandatory field 'Hotel contact value' is missing";
	private static final String USER_MISSING = "A mandatory field 'User' is missing";
	private static final String RESORCE_ALREADY_UPDATED = "The requested resource has already been updated by another user since it is been read";
	private static final String HOTEL_CONTACT_ALREADY_EXISTS = "A hotel contact with requested hotel code and contact type exists in the system";
	private static final String HOTEL_CONTACT_NOT_EXISTS = "A hotel contact with requested hotel code and contact type doesn't exist in the system";
	private static final String HOTEL_NOT_EXISTS = "A hotel with requested hotel code doesn't exist in the system";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelContactApiServiceImpl.class);

	private HotelContactRepository hotelContactRepository;
	private HotelApiServiceImpl hotelApiServiceImpl;

	@Autowired
	public HotelContactApiServiceImpl(HotelContactRepository hotelContactRepository, HotelApiServiceImpl hotelApiServiceImpl) {
		super();
		this.hotelContactRepository = hotelContactRepository;
		this.hotelApiServiceImpl = hotelApiServiceImpl;
	}

	public HotelContact createHotelContact(HotelContactRequestDTO input, String hotelCode) {
		DateTimeService dateTimeService = new DateTimeService();
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel address create operation started at {}", createdAt);
		}

		validateMandatoryFields(input, hotelCode);
		Hotel hotel = hotelApiServiceImpl.getHotelByCode(hotelCode);

		if (getContactByType(hotelCode, input.getContactType()) != null) {
			throw new ElementExistsException(HOTEL_CONTACT_ALREADY_EXISTS);

		} else {
			return hotelContactRepository.save(new HotelContact(hotel, ContactType.findByLabel(input.getContactType()),
					input.getContactValue(), createdAt, input.getResponsibleUser()));
		}
	}

	public HotelContact updateHotelContact(HotelContactRequestDTO input, String hotelCode) {
		DateTimeService dateTimeService = new DateTimeService();
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel address update operation started at {}", updatedAt);
		}
		
		validateMandatoryFields(input, hotelCode);
		HotelContact hotelContact = getContactByType(hotelCode, input.getContactType());

		if (hotelContact == null) {
			throw new NoSuchElementException(HOTEL_CONTACT_NOT_EXISTS);

		} else {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Latest Update Date time {}", hotelContact.getModifiedAt());
			}
			
			if (StringUtils.isNotBlank(hotelContact.getModifiedAt()) || 
					StringUtils.isBlank(input.getModifiedAt())) {
				throw new ElementUpdatedException(RESORCE_ALREADY_UPDATED);
			} else if (StringUtils.isBlank(hotelContact.getModifiedAt())
					|| input.getModifiedAt().equals(hotelContact.getModifiedAt())) {
				hotelContact.setContactValue(input.getContactValue());
				hotelContact.setModifiedAt(updatedAt);
				hotelContact.setModifiedBy(input.getResponsibleUser());

				return hotelContactRepository.save(hotelContact);

			} else {
				throw new ElementUpdatedException(RESORCE_ALREADY_UPDATED);
			}
		}
	}

	public void deleteAllHotelContacts(String hotelCode) {
		DateTimeService dateTimeService = new DateTimeService();
		String deletedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel delete operation started at {}", deletedAt);
		}
		
		Hotel hotel = hotelApiServiceImpl.getHotelByCode(hotelCode);
		List<HotelContact> hotelContacts = hotelContactRepository.findByHotel(hotel);
		hotelContactRepository.deleteAll(hotelContacts);

	}

	public void deleteHotelContact(String hotelCode, String conatctType) {
		DateTimeService dateTimeService = new DateTimeService();
		String deletedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Hotel delete operation started at {}", deletedAt);
		}
		
		HotelContact hotelContact = getContactByType(hotelCode, conatctType);
		hotelContactRepository.delete(hotelContact);
	}

	public HotelContact getContactByType(String hotelCode, String contactTypeStr) {
		Hotel hotel = hotelApiServiceImpl.getHotelByCode(hotelCode);
		ContactType contactType = ContactType.findByLabel(contactTypeStr);
		
		if (hotelContactRepository.findByHotelAndContactType(hotel, contactType).size() == 0) {
			return null;
		} else if (hotelContactRepository.findByHotelAndContactType(hotel, contactType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the contact type " + contactType);
		} else {
			HotelContact hotelContact = hotelContactRepository.findByHotelAndContactType(hotel, contactType).get(0);
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Hotel contact details {}", hotelContact);
			}
			return hotelContact;
		}
	}
	
	public List<HotelContact> getAllContacts(String hotelCode) {
		return hotelContactRepository.findByHotel(hotelApiServiceImpl.getHotelByCode(hotelCode));
		
	}

	private Boolean validateMandatoryFields(HotelContactRequestDTO input, String hotelCode) {
		if (hotelApiServiceImpl.getHotelByCode(hotelCode) == null) {
			throw new InputMappingException(HOTEL_NOT_EXISTS);

		} else if (StringUtils.isBlank(input.getContactType())) {
			throw new InputMappingException(HOTEL_CONTACT_TYPE_MISSING);

		} else if (StringUtils.isBlank(input.getContactValue())) {
			throw new InputMappingException(HOTEL_CONTACT_VALUE_MISSING);

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new InputMappingException(USER_MISSING);

		} else {
			return Boolean.TRUE;
		}
	}
}
