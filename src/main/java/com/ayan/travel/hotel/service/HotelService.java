package com.ayan.travel.hotel.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.ayan.travel.hotel.domain.AddressType;
import com.ayan.travel.hotel.domain.ContactType;
import com.ayan.travel.hotel.domain.dto.HotelDTO;
import com.ayan.travel.hotel.domain.Status;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelAddress;
import com.ayan.travel.hotel.entity.HotelContact;
import com.ayan.travel.hotel.repository.HotelAddressRepository;
import com.ayan.travel.hotel.repository.HotelContactRepository;
import com.ayan.travel.hotel.repository.HotelRepository;

public class HotelService {
	
	private static final String DATE_FORMATTER= "dd-MM-yyyy'T'HH:mm:ss'Z'";
	
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
	
	public Map<Boolean, String> createHotelDetails(HotelDTO input) {
		
		Map<Boolean, String> response = new HashMap<Boolean, String>();
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		HotelAddress newHotelAddress = null;
		HotelContact newHotelContact = null;
		
		if(findHotelByCode(input.getHotelCode()) != null) {
			response.put(Boolean.FALSE, "Hotel with hotel code " + input.getHotelCode() + " already exists");
			return response;
		}
		
		Hotel newHotel =  createHotel(input.getHotelCode(), input.getHotelName(), Status.A, createdAt, 
				input.getResponsibleUser());	
		if(newHotel == null) {
			response.put(Boolean.FALSE, "Create Hotel operation failed");
			return response;
		}
		
		newHotelAddress = createHotelAddress(newHotel,
				AddressType.valueOfLabel(input.getAddressType()), input.getAddressLine1(), 
				input.getAddressLine2(), input.getAddressLine3(), input.getAddressLine4(), 
				input.getState(), input.getPostcode(), input.getCountry(),
				createdAt, input.getResponsibleUser());
		if(newHotelAddress == null) {
			response.put(Boolean.FALSE, "Create Hotel address operation failed");
			return response;
		}
						
		newHotelContact = createHotelContact(newHotel, 
				ContactType.valueOfLabel(input.getContactType()), input.getContactValue(),
				createdAt, input.getResponsibleUser());
		if(newHotelContact == null) {
			response.put(Boolean.FALSE, "Create Hotel contact operation failed");
			return response;
		}
		
		response.put(Boolean.TRUE, "create Hotel operation successful");
		return response;
	}
	
	public Map<Boolean, String> updateHotelDetails(HotelDTO input) {
		String dateTime = dateTimeService.getDateTime(DATE_FORMATTER);
		Map<Boolean, String> response = new HashMap<Boolean, String>();
		
		Hotel existingHotel = null;
		Hotel updatedHotel = null;
		HotelAddress hotelAddress = null;
		HotelContact hotelContact = null;
		
		existingHotel = findHotelByCode(input.getHotelCode());
		if(existingHotel == null) {
			response.put(Boolean.FALSE, "There is no hotel exists with the hotel code " + input.getHotelCode());
			return response;
		} else {
			updatedHotel = updateHotel(existingHotel, input.getHotelName(), dateTime, input.getResponsibleUser());	
			if(updatedHotel == null) {
				response.put(Boolean.FALSE, "Update Hotel operation failed");
				return response;
			}
		}
		
		HotelAddress existingHotelAddress = findHotelAddressByHotelAndAddressType(existingHotel, AddressType.valueOfLabel(input.getAddressType()));
		if(existingHotelAddress == null) {
			hotelAddress = createHotelAddress(existingHotel, AddressType.valueOfLabel(input.getAddressType()), input.getAddressLine1(),
					input.getAddressLine2(), input.getAddressLine3(), input.getAddressLine4(), input.getState(), 
					input.getPostcode(), input.getCountry(), dateTime, input.getResponsibleUser());
		} else {
			hotelAddress = updateHotelAddress(existingHotelAddress, input.getAddressLine1(),
					input.getAddressLine2(), input.getAddressLine3(), input.getAddressLine4(), input.getState(), 
					input.getPostcode(), input.getCountry(), dateTime, input.getResponsibleUser());
		}
		
		if(hotelAddress == null) {
			response.put(Boolean.FALSE, "Update Hotel address operation failed");
			return response;
		}
		
		HotelContact existingHotelContact = findHotelContactByHotelAndContactType(existingHotel, ContactType.valueOfLabel(input.getContactType()));
		
		if(existingHotelContact == null) {
			hotelContact = createHotelContact(existingHotel, ContactType.valueOfLabel(input.getContactType()), input.getContactValue(), 
					dateTime, input.getResponsibleUser());
		} else {
			hotelContact = updateHotelContact(existingHotelContact, input.getContactValue(),
					dateTime, input.getResponsibleUser());
		}
		
		if(hotelContact == null) {
			response.put(Boolean.FALSE, "Update Hotel Contact operation failed");
			return response;
		} 
		
		response.put(Boolean.TRUE, "Hotel update successful");
		return response;
	}
	
	public Hotel inactiveHotel(Hotel hotel, Status status, String modifiedAt, String modifiedBy) {
		hotel.setStatus(status);
		hotel.setModifiedAt(modifiedAt);
		hotel.setModifiedBy(modifiedBy);
		return hotelRepository.save(hotel);
	}
	
	private Hotel createHotel(String hotelCode, String hotelName, Status status, String createdAt, String createdBy) {
		return hotelRepository.save(new Hotel(hotelCode, hotelName, status, createdAt, createdBy));
	}
	
	private HotelAddress createHotelAddress(Hotel hotel, AddressType addressType, String addressLine1, 
			String addressLine2, String addressLine3, String addressLine4,
			String state, String postcode, String country, String createdAt, String createdBy) {
		return hotelAddressRepository.save(new HotelAddress(hotel, addressType, addressLine1, 
				addressLine2, addressLine3, addressLine4, state, postcode, country,
				createdAt, createdBy));
	}
	
	private HotelContact createHotelContact(Hotel hotel, ContactType contactType, String contactValue, 
			String createdAt, String createdBy) {
		return hotelContactRepository.save(new HotelContact(hotel, contactType, contactValue, 
				createdAt, createdBy));
	}
	
	private Hotel findHotelByCode(String hotelCode) {	
		if(hotelRepository.findByHotelCode(hotelCode).isEmpty()) {
			return null;
		} else if(hotelRepository.findByHotelCode(hotelCode).size() > 1){
			throw new RuntimeException("More than one hotel exists for the hotel code " + hotelCode);
		} else {
			return hotelRepository.findByHotelCode(hotelCode).get(0);
		}
	}
	
	private HotelAddress findHotelAddressByHotelAndAddressType(Hotel hotel, AddressType addressType) {
		if(hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).isEmpty()) {
			return null;
		} else if(hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the adress type " + addressType);
		} else {
			return hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).get(0);
		}
	}
	
	private HotelContact findHotelContactByHotelAndContactType(Hotel hotel, ContactType contactType) {
		if(hotelContactRepository.findByHotelAndContactType(hotel, contactType).isEmpty()) {
			return null;
		} else if(hotelContactRepository.findByHotelAndContactType(hotel, contactType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the contact type " + contactType);
		} else {
			return hotelContactRepository.findByHotelAndContactType(hotel, contactType).get(0);
		}
	}
	
	private Hotel updateHotel(Hotel hotel, String hotelName, String modifiedAt, String modifiedBy) {
		hotel.setHotelName(hotelName);
		hotel.setModifiedAt(modifiedAt);
		hotel.setModifiedBy(modifiedBy);
		return hotelRepository.save(hotel);
	}
	
	private HotelAddress updateHotelAddress(HotelAddress hotelAddress, String addressLine1, 
			String addressLine2, String addressLine3, String addressLine4,
			String state, String postcode, String country, String modifiedAt, String modifiedBy) {
		hotelAddress.setAddressLine1(addressLine1);
		hotelAddress.setAddressLine2(addressLine2);
		hotelAddress.setAddressLine3(addressLine3);
		hotelAddress.setAddressLine4(addressLine4);
		hotelAddress.setState(state);
		hotelAddress.setPostcode(postcode);
		hotelAddress.setCountry(country);
		hotelAddress.setModifiedAt(modifiedAt);
		hotelAddress.setModifiedBy(modifiedBy);
		
		return hotelAddressRepository.save(hotelAddress);
	}
	
	private HotelContact updateHotelContact(HotelContact hotelContact, String contactValue, String modifiedAt, String modifiedBy) {
		hotelContact.setContactValue(contactValue);
		return hotelContactRepository.save(hotelContact);
	}
	
	
	
	
	
	

}
