package com.travel.hotel.base.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.hotel.base.api.dto.HotelContactDTO;
import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.domain.ContactType;
import com.travel.hotel.base.entity.Hotel;
import com.travel.hotel.base.entity.HotelContact;
import com.travel.hotel.base.exception.ElementExistsException;
import com.travel.hotel.base.exception.ElementUpdatedException;
import com.travel.hotel.base.exception.InputMappingException;
import com.travel.hotel.base.repository.HotelContactRepository;

@Service
public class HotelContactCrudServiceImpl {

	private static final Logger LOGGER = LoggerFactory.getLogger(HotelContactCrudServiceImpl.class);

	private HotelContactRepository hotelContactRepository;
	private HotelCrudServiceImpl hotelCrudServiceImpl;

	@Autowired
	public HotelContactCrudServiceImpl(HotelContactRepository hotelContactRepository,
			HotelCrudServiceImpl hotelCrudServiceImpl) {
		super();
		this.hotelContactRepository = hotelContactRepository;
		this.hotelCrudServiceImpl = hotelCrudServiceImpl;
	}

	public HotelContact createHotelContact(HotelContactDTO input, String hotelCode) {
		Hotel hotel = validateAndGetHotel(input, hotelCode);
		
		if(!input.getIsPrimary()) {
			return hotelContactRepository
					.save(new HotelContact.HotelContactBuilder().withHotel(hotel).withType(input.getContactType())
							.withIsPrimary(input.getIsPrimary()).withValue(input.getContactValue()).withCreatedBy(input.getUser()).build());
		} else {
			HotelContact primaryContact = getPrimaryContactByType(hotel, input.getContactType());
			if(primaryContact == null) {
				return hotelContactRepository
						.save(new HotelContact.HotelContactBuilder().withHotel(hotel).withType(input.getContactType())
								.withIsPrimary(input.getIsPrimary()).withValue(input.getContactValue()).withCreatedBy(input.getUser()).build());
			} else {
				throw new ElementExistsException(Constants.PRIMARTY_HOTEL_CONTACT_EXISTS);
			}
		}
	}

	public HotelContact updateHotelContact(HotelContactDTO input, String hotelCode) {

		Hotel hotel = validateAndGetHotel(input, hotelCode);
		HotelContact hotelContact = getContactByType(hotel, input.getContactType());

		if (hotelContact == null) {
			throw new NoSuchElementException(Constants.HOTEL_CONTACT_NOT_EXISTS);

		} else {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Latest Update Date time {}", hotelContact.getModifiedAt());
			}

			if (StringUtils.isNotBlank(hotelContact.getModifiedAt()) || StringUtils.isBlank(input.getReadDateTime())) {
				throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);
			} else if (StringUtils.isBlank(hotelContact.getModifiedAt())
					|| input.getReadDateTime().equals(hotelContact.getModifiedAt())) {
				if(StringUtils.isNotBlank(input.getContactValue())) {
					hotelContact.setValue(input.getContactValue());
				}
				hotelContact.setIsPrimary(input.getIsPrimary());
				hotelContact.setModifiedAt();
				hotelContact.setModifiedBy(input.getUser());

				return hotelContactRepository.save(hotelContact);

			} else {
				throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);
			}
		}
	}

	public void deleteAllHotelContact(String hotelCode) {

		Hotel hotel = hotelCrudServiceImpl.getHotelByCode(hotelCode);
		if (hotel == null) {
			throw new ElementExistsException(Constants.RESOURCE_UPDATED);
		} else {
			List<HotelContact> hotelContacts = hotelContactRepository.findByHotel(hotel);
			hotelContactRepository.deleteAll(hotelContacts);
		}

	}

	public void deleteHotelContact(String hotelCode, String conatctType) {
		Hotel hotel = hotelCrudServiceImpl.getHotelByCode(hotelCode);
		if (hotel == null) {
			throw new ElementExistsException(Constants.HOTEL_NOT_EXISTS);
		} else {
			HotelContact hotelContact = getContactByType(hotel, conatctType);
			if (hotelContact == null) {
				throw new NoSuchElementException(Constants.HOTEL_CONTACT_NOT_EXISTS);
			} else {
				hotelContactRepository.delete(hotelContact);
			}
		}
	}

	private HotelContact getContactByType(Hotel hotel, String contactType) {

		if (hotelContactRepository.findByHotelAndType(hotel, ContactType.findByLabel(contactType)).size() == 0) {
			return null;
		} else if (hotelContactRepository.findByHotelAndType(hotel, ContactType.findByLabel(contactType)).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the contact type " + contactType);
		} else {
			HotelContact hotelContact = hotelContactRepository
					.findByHotelAndType(hotel, ContactType.findByLabel(contactType)).get(0);
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Hotel contact details {}", hotelContact);
			}
			return hotelContact;
		}
	}
	
	private HotelContact getPrimaryContactByType(Hotel hotel, String contactType) {

		if (hotelContactRepository.findByHotelAndTypeAndIsPrimary(hotel, ContactType.findByLabel(contactType), Boolean.TRUE).size() == 0) {
			return null;
		} else if (hotelContactRepository.findByHotelAndTypeAndIsPrimary(hotel, ContactType.findByLabel(contactType), Boolean.TRUE).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the contact type " + contactType);
		} else {
			HotelContact hotelContact = hotelContactRepository
					.findByHotelAndTypeAndIsPrimary(hotel, ContactType.findByLabel(contactType), Boolean.TRUE).get(0);
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Hotel contact details {}", hotelContact);
			}
			return hotelContact;
		}
	}

	private Hotel validateAndGetHotel(HotelContactDTO input, String hotelCode) {
		if (StringUtils.isBlank(input.getContactType())) {
			throw new InputMappingException(Constants.HOTEL_CONTACT_TYPE_MISSING);

		} else if (input.getIsPrimary() == null) {
			throw new InputMappingException(Constants.HOTEL_CONTACT_PRIMARY_MISSING);
			
		} else if (StringUtils.isBlank(input.getContactValue())) {
			throw new InputMappingException(Constants.HOTEL_CONTACT_MISSING);

		} else if (StringUtils.isBlank(input.getUser())) {
			throw new InputMappingException(Constants.USER_MISSING);

		} else if (hotelCrudServiceImpl.getHotelByCode(hotelCode) == null) {
			throw new NoSuchElementException(Constants.HOTEL_NOT_EXISTS);
		} else {
			return hotelCrudServiceImpl.getHotelByCode(hotelCode);
		}
	}
}
