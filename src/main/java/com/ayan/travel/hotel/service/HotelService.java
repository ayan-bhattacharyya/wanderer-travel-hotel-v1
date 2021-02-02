package com.ayan.travel.hotel.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ayan.travel.hotel.domain.AddressType;
import com.ayan.travel.hotel.domain.ContactType;
import com.ayan.travel.hotel.domain.dto.HotelRequestDTO;
import com.ayan.travel.hotel.domain.Status;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelAddress;
import com.ayan.travel.hotel.entity.HotelContact;
import com.ayan.travel.hotel.repository.HotelAddressRepository;
import com.ayan.travel.hotel.repository.HotelContactRepository;
import com.ayan.travel.hotel.repository.HotelRepository;

public class HotelService {

	private static final String DATE_FORMATTER = "dd-MM-yyyy'T'HH:mm:ss'Z'";
	private static final String HOTEL_CODE_MISSING = "A mandatory field 'Hotel code' is missing";
	private static final String HOTEL_NAME_MISSING = "A mandatory field 'Hotel name' is missing";

	private static final String HOTEL_ADDRESS_TYPE_MISSING = "A mandatory field 'Hotel address type' is missing";
	private static final String HOTEL_ADDRESS_LINE_1_MISSING = "A mandatory field 'Hotel address line 1' is missing";
	private static final String HOTEL_ADDRESS_LINE_4_MISSING = "A mandatory field 'Hotel address line 4' is missing";
	private static final String HOTEL_ADDRESS_STATE_MISSING = "A mandatory field 'Hotel address state' is missing";
	private static final String HOTEL_ADDRESS_POSTCODE_MISSING = "A mandatory field 'Hotel address postcode' is missing";
	private static final String HOTEL_ADDRESS_COUNTRY_MISSING = "A mandatory field 'Hotel address country' is missing";

	private static final String HOTEL_CONTACT_TYPE_MISSING = "A mandatory field 'Contact type' is missing";
	private static final String HOTEL_CONTACT_VALUE_MISSING = "A mandatory field 'Hotel contact value' is missing";

	private static final String USER_MISSING = "A mandatory field 'User' is missing";

	private static final String RESORCE_ALREADY_UPDATED = "The requested resource has already been updated by another user";
	private static final String RESORCE_ALREADY_EXISTS = "The requested resource is already exists in the system";
	private static final String RESORCE_NOT_EXISTS = "The requested resource doesn't exist in the system";

	private HotelRepository hotelRepository;
	private HotelAddressRepository hotelAddressRepository;
	private HotelContactRepository hotelContactRepository;
	private DateTimeService dateTimeService;

	@Autowired
	public HotelService(HotelRepository hotelRepository, HotelAddressRepository hotelAddressRepository,
			HotelContactRepository hotelContactRepository, DateTimeService dateTimeService) {
		super();
		this.hotelRepository = hotelRepository;
		this.hotelAddressRepository = hotelAddressRepository;
		this.hotelContactRepository = hotelContactRepository;
		this.dateTimeService = dateTimeService;
	}

	public Hotel createHotel(HotelRequestDTO input) {
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (StringUtils.isBlank(input.getHotelCode())) {
			throw new RuntimeException(HOTEL_CODE_MISSING);

		} else if (StringUtils.isBlank(input.getHotelName())) {
			throw new RuntimeException(HOTEL_NAME_MISSING);

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new RuntimeException(USER_MISSING);

		} else if (findHotelByCode(input.getHotelCode()) != null) {
			throw new RuntimeException(RESORCE_ALREADY_EXISTS);

		} else {
			return hotelRepository.save(new Hotel(input.getHotelCode(), input.getHotelName(), Status.A, createdAt,
					input.getResponsibleUser()));

		}
	}

	public HotelAddress createHotelAddress(HotelRequestDTO input) {
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (StringUtils.isBlank(input.getHotelCode())) {
			throw new RuntimeException(HOTEL_CODE_MISSING);

		} else if (StringUtils.isBlank(input.getAddressType())) {
			throw new RuntimeException(HOTEL_ADDRESS_TYPE_MISSING);

		} else if (StringUtils.isBlank(input.getAddressLine1())) {
			throw new RuntimeException(HOTEL_ADDRESS_LINE_1_MISSING);

		} else if (StringUtils.isBlank(input.getAddressLine4())) {
			throw new RuntimeException(HOTEL_ADDRESS_LINE_4_MISSING);

		} else if (StringUtils.isBlank(input.getState())) {
			throw new RuntimeException(HOTEL_ADDRESS_STATE_MISSING);

		} else if (StringUtils.isBlank(input.getPostcode())) {
			throw new RuntimeException(HOTEL_ADDRESS_POSTCODE_MISSING);

		} else if (StringUtils.isBlank(input.getCountry())) {
			throw new RuntimeException(HOTEL_ADDRESS_COUNTRY_MISSING);

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new RuntimeException(USER_MISSING);

		} else if (findHotelByCode(input.getHotelCode()) == null) {
			throw new RuntimeException(RESORCE_NOT_EXISTS);

		} else if (findHotelAddressByAddressType(findHotelByCode(input.getHotelCode()),
				AddressType.valueOfLabel(input.getAddressType())) != null) {
			throw new RuntimeException(RESORCE_ALREADY_EXISTS);

		} else {
			return hotelAddressRepository.save(new HotelAddress(findHotelByCode(input.getHotelCode()),
					AddressType.valueOfLabel(input.getAddressType()), input.getAddressLine1(), input.getAddressLine2(),
					input.getAddressLine3(), input.getAddressLine4(), input.getState(), input.getPostcode(),
					input.getCountry(), createdAt, input.getResponsibleUser()));
		}
	}

	public HotelContact createHotelContact(HotelRequestDTO input) {
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (StringUtils.isBlank(input.getHotelCode())) {
			throw new RuntimeException(HOTEL_CODE_MISSING);

		} else if (StringUtils.isBlank(input.getContactType())) {
			throw new RuntimeException(HOTEL_CONTACT_TYPE_MISSING);

		} else if (StringUtils.isBlank(input.getContactValue())) {
			throw new RuntimeException(HOTEL_CONTACT_VALUE_MISSING);

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new RuntimeException(USER_MISSING);

		} else if (findHotelByCode(input.getHotelCode()) == null) {
			throw new RuntimeException(RESORCE_NOT_EXISTS);

		} else if (findHotelContactByContactType(findHotelByCode(input.getHotelCode()),
				ContactType.valueOfLabel(input.getContactType())) != null) {
			throw new RuntimeException(RESORCE_ALREADY_EXISTS);

		} else {
			return hotelContactRepository.save(new HotelContact(findHotelByCode(input.getHotelCode()),
					ContactType.valueOfLabel(input.getContactType()), input.getContactValue(), createdAt,
					input.getResponsibleUser()));
		}
	}

	public Hotel updateHotel(HotelRequestDTO input) {
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (StringUtils.isBlank(input.getHotelCode())) {
			throw new RuntimeException(HOTEL_CODE_MISSING);

		} else if (StringUtils.isBlank(input.getHotelName())) {
			throw new RuntimeException(HOTEL_NAME_MISSING);

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new RuntimeException(USER_MISSING);

		} else if (findActiveHotelByCode(input.getHotelCode()) == null) {
			throw new RuntimeException(RESORCE_NOT_EXISTS);

		} else if (!findActiveHotelByCode(input.getHotelCode()).getModifiedAt()
				.equalsIgnoreCase(input.getModifiedAt())) {
			throw new RuntimeException(RESORCE_ALREADY_UPDATED);

		} else {
			Hotel hotel = findActiveHotelByCode(input.getHotelCode());
			hotel.setHotelName(input.getHotelName());
			hotel.setModifiedAt(updatedAt);
			hotel.setModifiedBy(input.getResponsibleUser());
			return hotelRepository.save(hotel);

		}
	}

	public HotelAddress updateHotelAddress(HotelRequestDTO input) {
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (StringUtils.isBlank(input.getHotelCode())) {
			throw new RuntimeException(HOTEL_CODE_MISSING);

		} else if (StringUtils.isBlank(input.getAddressType())) {
			throw new RuntimeException(HOTEL_ADDRESS_TYPE_MISSING);

		} else if (StringUtils.isBlank(input.getAddressLine1())) {
			throw new RuntimeException(HOTEL_ADDRESS_LINE_1_MISSING);

		} else if (StringUtils.isBlank(input.getAddressLine4())) {
			throw new RuntimeException(HOTEL_ADDRESS_LINE_4_MISSING);

		} else if (StringUtils.isBlank(input.getState())) {
			throw new RuntimeException(HOTEL_ADDRESS_STATE_MISSING);

		} else if (StringUtils.isBlank(input.getPostcode())) {
			throw new RuntimeException(HOTEL_ADDRESS_POSTCODE_MISSING);

		} else if (StringUtils.isBlank(input.getCountry())) {
			throw new RuntimeException(HOTEL_ADDRESS_COUNTRY_MISSING);

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new RuntimeException(USER_MISSING);

		} else if (findActiveHotelByCode(input.getHotelCode()) == null) {
			throw new RuntimeException(RESORCE_NOT_EXISTS);

		} else {
			HotelAddress hotelAddress = findHotelAddressByAddressType(findActiveHotelByCode(input.getHotelCode()),
					AddressType.valueOfLabel(input.getAddressType()));
			if (hotelAddress == null) {
				throw new RuntimeException(RESORCE_NOT_EXISTS);

			} else if (!hotelAddress.getModifiedAt().equalsIgnoreCase(input.getModifiedAt())) {
				throw new RuntimeException(RESORCE_ALREADY_UPDATED);

			} else {
				hotelAddress.setAddressLine1(input.getAddressLine1());
				hotelAddress.setAddressLine2(input.getAddressLine2());
				hotelAddress.setAddressLine3(input.getAddressLine3());
				hotelAddress.setAddressLine4(input.getAddressLine4());
				hotelAddress.setState(input.getState());
				hotelAddress.setPostcode(input.getPostcode());
				hotelAddress.setCountry(input.getCountry());
				hotelAddress.setModifiedAt(updatedAt);
				hotelAddress.setModifiedBy(input.getResponsibleUser());

				return hotelAddressRepository.save(hotelAddress);

			}
		}
	}

	public HotelContact updateHotelContact(HotelRequestDTO input) {
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		if (StringUtils.isBlank(input.getHotelCode())) {
			throw new RuntimeException(HOTEL_CODE_MISSING);

		} else if (StringUtils.isBlank(input.getContactType())) {
			throw new RuntimeException(HOTEL_CONTACT_TYPE_MISSING);

		} else if (StringUtils.isBlank(input.getContactValue())) {
			throw new RuntimeException(HOTEL_CONTACT_VALUE_MISSING);

		} else if (StringUtils.isBlank(input.getResponsibleUser())) {
			throw new RuntimeException(USER_MISSING);

		} else if (findActiveHotelByCode(input.getHotelCode()) == null) {
			throw new RuntimeException(RESORCE_NOT_EXISTS);

		} else {
			HotelContact hotelContact = findHotelContactByContactType(findActiveHotelByCode(input.getHotelCode()),
					ContactType.valueOfLabel(input.getContactType()));
			if (hotelContact == null) {
				throw new RuntimeException(RESORCE_NOT_EXISTS);

			} else if (!hotelContact.getModifiedAt().equalsIgnoreCase(input.getModifiedAt())) {
				throw new RuntimeException(RESORCE_ALREADY_UPDATED);

			} else {
				hotelContact.setContactValue(input.getContactValue());
				hotelContact.setModifiedAt(updatedAt);
				hotelContact.setModifiedBy(input.getResponsibleUser());

				return hotelContactRepository.save(hotelContact);
			}
		}
	}

	public Hotel inactiveHotel(HotelRequestDTO input) {
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		Hotel hotel = findActiveHotelByCode(input.getHotelCode());

		if (hotel == null) {
			throw new RuntimeException(RESORCE_NOT_EXISTS);

		} else if (!hotel.getModifiedAt().equalsIgnoreCase(input.getModifiedAt())) {
			throw new RuntimeException(RESORCE_ALREADY_UPDATED);

		} else if (input.getResponsibleUser() == null) {
			throw new RuntimeException(USER_MISSING);
		} else {
			hotel.setStatus(Status.I);
			hotel.setModifiedAt(updatedAt);
			hotel.setModifiedBy(input.getResponsibleUser());
			return hotelRepository.save(hotel);
		}
	}

	private Hotel findActiveHotelByCode(String hotelCode) {
		if (hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).size() == 0) {
			return null;
		} else if (hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).size() > 1) {
			throw new RuntimeException("More than one active hotel exists for the hotel code " + hotelCode);
		} else {
			return hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).get(0);
		}
	}

	private Hotel findHotelByCode(String hotelCode) {
		if (hotelRepository.findByHotelCode(hotelCode).size() == 0) {
			return null;
		} else if (hotelRepository.findByHotelCode(hotelCode).size() > 1) {
			throw new RuntimeException("More than one hotel exists for the hotel code " + hotelCode);
		} else {
			return hotelRepository.findByHotelCode(hotelCode).get(0);
		}
	}

	private HotelAddress findHotelAddressByAddressType(Hotel hotel, AddressType addressType) {
		if (hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).size() == 0) {
			return null;
		} else if (hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the adress type " + addressType);
		} else {
			return hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).get(0);
		}
	}

	private HotelContact findHotelContactByContactType(Hotel hotel, ContactType contactType) {
		if (hotelContactRepository.findByHotelAndContactType(hotel, contactType).size() == 0) {
			return null;
		} else if (hotelContactRepository.findByHotelAndContactType(hotel, contactType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the contact type " + contactType);
		} else {
			return hotelContactRepository.findByHotelAndContactType(hotel, contactType).get(0);
		}
	}
}
