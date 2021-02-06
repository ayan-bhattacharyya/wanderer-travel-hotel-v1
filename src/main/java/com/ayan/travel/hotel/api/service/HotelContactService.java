package com.ayan.travel.hotel.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
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
public class HotelContactService {

	private static final String DATE_FORMATTER = "dd-MM-yyyy'T'HH:mm:ss'Z'";
	private static final String HOTEL_ID_MISSING = "A mandatory field 'Hotel Id' is missing";
	private static final String HOTEL_CONTACT_TYPE_MISSING = "A mandatory field 'Contact type' is missing";
	private static final String HOTEL_CONTACT_VALUE_MISSING = "A mandatory field 'Hotel contact value' is missing";
	private static final String USER_MISSING = "A mandatory field 'User' is missing";
	private static final String RESORCE_ALREADY_UPDATED = "The requested resource has already been updated by another user since it is been read";
	private static final String HOTEL_CONTACT_ALREADY_EXISTS = "A hotel contact with requested hotel code and contact type exists in the system";
	private static final String HOTEL_CONTACT_NOT_EXISTS = "A hotel contact with requested hotel code and contact type doesn't exist in the system";

	private HotelContactRepository hotelContactRepository;
	private HotelService hotelService;

	@Autowired
	public HotelContactService(HotelContactRepository hotelContactRepository, HotelService hotelService) {
		super();
		this.hotelContactRepository = hotelContactRepository;
		this.hotelService = hotelService;
	}

	public HotelContact createHotelContact(HotelContactRequestDTO input, Long hotelId) {
		DateTimeService dateTimeService = new DateTimeService();
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);

		validateMandatoryFields(input, hotelId);
		Hotel hotel = hotelService.getHotelById(hotelId);

		if (getContactByType(hotelId, input.getContactType()) != null) {
			throw new ElementExistsException(HOTEL_CONTACT_ALREADY_EXISTS);

		} else {
			return hotelContactRepository.save(new HotelContact(hotel, ContactType.findByLabel(input.getContactType()),
					input.getContactValue(), createdAt, input.getResponsibleUser()));
		}
	}

	public HotelContact updateHotelContact(HotelContactRequestDTO input, Long hotelId) {
		DateTimeService dateTimeService = new DateTimeService();
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		validateMandatoryFields(input, hotelId);
		HotelContact hotelContact = getContactByType(hotelId, input.getContactType());

		if (hotelContact == null) {
			throw new NoSuchElementException(HOTEL_CONTACT_NOT_EXISTS);

		} else {
			if (StringUtils.isBlank(hotelContact.getModifiedAt()) || StringUtils.isNotBlank(input.getModifiedAt())) {
				hotelContact.setContactValue(input.getContactValue());
				hotelContact.setModifiedAt(updatedAt);
				hotelContact.setModifiedBy(input.getResponsibleUser());

				return hotelContactRepository.save(hotelContact);

			} else {
				throw new ElementUpdatedException(RESORCE_ALREADY_UPDATED);
			}
		}
	}

	public void deleteAllHotelContacts(Long hotelId) {
		Hotel hotel = hotelService.getHotelById(hotelId);
		List<HotelContact> hotelContacts = hotelContactRepository.findByHotel(hotel);
		hotelContactRepository.deleteAll(hotelContacts);

	}

	public void deleteHotelContact(Long hotelId, String conatctType) {
		HotelContact hotelContact = getContactByType(hotelId, conatctType);
		hotelContactRepository.delete(hotelContact);
	}

	public HotelContact getContactByType(Long hotelId, String contactTypeStr) {
		Hotel hotel = hotelService.getHotelById(hotelId);
		ContactType contactType = ContactType.findByLabel(contactTypeStr);
		if (hotelContactRepository.findByHotelAndContactType(hotel, contactType).size() == 0) {
			return null;
		} else if (hotelContactRepository.findByHotelAndContactType(hotel, contactType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the contact type " + contactType);
		} else {
			return hotelContactRepository.findByHotelAndContactType(hotel, contactType).get(0);
		}
	}
	
	public List<HotelContact> getAllContacts(Long hotelId) {
		return hotelContactRepository.findByHotel(hotelService.getHotelById(hotelId));
		
	}

	private Boolean validateMandatoryFields(HotelContactRequestDTO input, Long hotelId) {
		if (hotelId == null) {
			throw new InputMappingException(HOTEL_ID_MISSING);

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
