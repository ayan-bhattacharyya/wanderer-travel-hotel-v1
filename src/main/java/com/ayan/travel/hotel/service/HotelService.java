package com.ayan.travel.hotel.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
	
	public Map<Hotel, Map<HttpStatus, String>> createHotel(HotelRequestDTO input) {
		
		Map<HttpStatus, String> httpResoponse = new HashMap<HttpStatus, String>();
		Map<Hotel, Map<HttpStatus, String>> methodResponse = new HashMap<Hotel, Map<HttpStatus, String>>();
		
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if(StringUtils.isBlank(input.getHotelCode())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel code' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getHotelName())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel name' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getResponsibleUser())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'User' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(findHotelByCode(input.getHotelCode()) != null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " already exists");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		Hotel newHotel =  hotelRepository.save(new Hotel(input.getHotelCode(), input.getHotelName(), Status.A, createdAt, 
				input.getResponsibleUser()));
		httpResoponse.put(HttpStatus.CREATED, "");
		methodResponse.put(newHotel, httpResoponse);
		return methodResponse;
	}
	
	public Map<HotelAddress, Map<HttpStatus, String>> createHotelAddress(HotelRequestDTO input) {
		Map<HttpStatus, String> httpResoponse = new HashMap<HttpStatus, String>();
		Map<HotelAddress, Map<HttpStatus, String>> methodResponse = new HashMap<HotelAddress, Map<HttpStatus, String>>();
		
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if(StringUtils.isBlank(input.getHotelCode())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel code' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getAddressType())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address type' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getAddressLine1())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address line 1' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getAddressLine4())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address line 4' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getState())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address state' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getPostcode())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address postcode' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getCountry())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address country' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getResponsibleUser())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'User' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		Hotel hotel = findHotelByCode(input.getHotelCode());
		if(hotel == null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " doesn't exists");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		HotelAddress hotelAddress = findHotelAddressByAddressType(hotel, 
				AddressType.valueOfLabel(input.getAddressType()));
		if(hotelAddress != null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel address with hotel code " + input.getHotelCode() 
			+" and address type " + input.getAddressType() + "already exists");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		HotelAddress newHotelAddress = hotelAddressRepository.save(new HotelAddress(hotel, 
				AddressType.valueOfLabel(input.getAddressType()), 
				input.getAddressLine1(), input.getAddressLine2(), input.getAddressLine3(), 
				input.getAddressLine4(),input.getState(), input.getPostcode(), input.getCountry(), 
				createdAt, input.getResponsibleUser()));
		httpResoponse.put(HttpStatus.CREATED, "");
		methodResponse.put(newHotelAddress, httpResoponse);
		return methodResponse;
	}
						
	public Map<HotelContact, Map<HttpStatus, String>> createHotelContact(HotelRequestDTO input) {
		
		Map<HttpStatus, String> httpResoponse = new HashMap<HttpStatus, String>();
		Map<HotelContact, Map<HttpStatus, String>> methodResponse = new HashMap<HotelContact, Map<HttpStatus, String>>();
		
		String createdAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if(StringUtils.isBlank(input.getHotelCode())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel code' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getContactType())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel contact type' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getContactValue())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel contact value' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getResponsibleUser())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'User' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		Hotel hotel = findHotelByCode(input.getHotelCode());
		if(hotel == null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " doesn't exists");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		HotelContact hotelContact = findHotelContactByContactType(hotel, ContactType.valueOfLabel(input.getContactType()));
		if(hotelContact != null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel address with hotel code " + input.getHotelCode() 
			+" and contact type " + input.getContactType() + "already exists");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		HotelContact newHotelContact = hotelContactRepository.save(new HotelContact(hotel, 
				ContactType.valueOfLabel(input.getContactType()), 
				input.getContactValue(), createdAt, input.getResponsibleUser()));
		httpResoponse.put(HttpStatus.CREATED, "");
		methodResponse.put(newHotelContact, httpResoponse);
		return methodResponse;
		
	}
	
	public Map<Hotel, Map<HttpStatus, String>> updateHotel(HotelRequestDTO input) {
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		Map<HttpStatus, String> httpResoponse = new HashMap<HttpStatus, String>();
		Map<Hotel, Map<HttpStatus, String>> methodResponse = new HashMap<Hotel, Map<HttpStatus, String>>();
		
		if(StringUtils.isBlank(input.getHotelCode())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel code' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getHotelName())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel name' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getResponsibleUser())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'User' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		Hotel hotel = findActiveHotelByCode(input.getHotelCode());
		
		if(hotel == null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " doesn't exist or the hotel is currectly not active");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} else if(! hotel.getModifiedAt().equalsIgnoreCase(input.getModifiedAt())){
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " details has already been updated by other user/resouce");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} else {
			hotel.setHotelName(input.getHotelName());
			hotel.setModifiedAt(updatedAt);
			hotel.setModifiedBy(input.getResponsibleUser());
			Hotel updatedHotel = hotelRepository.save(hotel);
			
			httpResoponse.put(HttpStatus.OK, "");
			methodResponse.put(updatedHotel, httpResoponse);
			return methodResponse;
		}
		
	}
	
	public Map<HotelAddress, Map<HttpStatus, String>> updateHotelAddress(HotelRequestDTO input) {
		
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);

		Map<HttpStatus, String> httpResoponse = new HashMap<HttpStatus, String>();
		Map<HotelAddress, Map<HttpStatus, String>> methodResponse = new HashMap<HotelAddress, Map<HttpStatus, String>>();
		
		if(StringUtils.isBlank(input.getHotelCode())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel code' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getAddressType())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address type' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getAddressLine1())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address line 1' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getAddressLine4())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address line 4' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getState())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address state' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getPostcode())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address postcode' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getCountry())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel address country' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getResponsibleUser())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'User' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		Hotel hotel = findActiveHotelByCode(input.getHotelCode());
		
		if(hotel == null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " doesn't exist or the hotel is currectly not active");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} 
		
		HotelAddress hotelAddress = findHotelAddressByAddressType(hotel, 
				AddressType.valueOfLabel(input.getAddressType()));
		if(hotelAddress == null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel address with hotel code " + input.getHotelCode() 
			+" and address type " + input.getAddressType() + "doesn't exist");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} else if(! hotelAddress.getModifiedAt().equalsIgnoreCase(input.getModifiedAt())){
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel Address with hotel code " + input.getHotelCode() + " details has already been updated by other user/resouce");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
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
			
			HotelAddress updatedHotelAddress = hotelAddressRepository.save(hotelAddress);
			
			httpResoponse.put(HttpStatus.OK, "");
			methodResponse.put(updatedHotelAddress, httpResoponse);
			return methodResponse;	
		}
	}
	
	public Map<HotelContact, Map<HttpStatus, String>> updateHotelContact(HotelRequestDTO input) {
		
		Map<HttpStatus, String> httpResoponse = new HashMap<HttpStatus, String>();
		Map<HotelContact, Map<HttpStatus, String>> methodResponse = new HashMap<HotelContact, Map<HttpStatus, String>>();
		
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		if(StringUtils.isBlank(input.getHotelCode())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel code' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getContactType())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel contact type' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getContactValue())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'Hotel contact value' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		if(StringUtils.isBlank(input.getResponsibleUser())) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "A mandatory field 'User' is missing");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		}
		
		Hotel hotel = findActiveHotelByCode(input.getHotelCode());
		
		if(hotel == null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " doesn't exist or the hotel is currectly not active");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} 
		
		HotelContact hotelContact = findHotelContactByContactType(hotel, 
				ContactType.valueOfLabel(input.getContactType()));
		if(hotelContact == null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel contact with hotel code " + input.getHotelCode() 
			+" and contact type " + input.getContactType() + "doesn't exist");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} else if(! hotelContact.getModifiedAt().equalsIgnoreCase(input.getModifiedAt())){
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel Contact with hotel code " + input.getHotelCode() + " details has already been updated by other user/resouce");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} else {
			hotelContact.setContactValue(input.getContactValue());
			hotelContact.setModifiedAt(updatedAt);
			hotelContact.setModifiedBy(input.getResponsibleUser());
			
			HotelContact updatedHotelConatc = hotelContactRepository.save(hotelContact);
			
			httpResoponse.put(HttpStatus.OK, "");
			methodResponse.put(updatedHotelConatc, httpResoponse);
			return methodResponse;	
		}
	}
	
	public Map<Hotel, Map<HttpStatus, String>> inactiveHotel(HotelRequestDTO input) {
		Map<HttpStatus, String> httpResoponse = new HashMap<HttpStatus, String>();
		Map<Hotel, Map<HttpStatus, String>> methodResponse = new HashMap<Hotel, Map<HttpStatus, String>>();
		
		String updatedAt = dateTimeService.getDateTime(DATE_FORMATTER);
		
		Hotel hotel = findActiveHotelByCode(input.getHotelCode());
		
		if(hotel == null) {
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " doesn't exist or the hotel is currectly not active");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} else if(! hotel.getModifiedAt().equalsIgnoreCase(input.getModifiedAt())){
			httpResoponse.put(HttpStatus.BAD_REQUEST, "Hotel with hotel code " + input.getHotelCode() + " details has already been updated by other user/resouce");
			methodResponse.put(null, httpResoponse);
			return methodResponse;
		} else {
			hotel.setStatus(Status.I);
			hotel.setModifiedAt(updatedAt);
			hotel.setModifiedBy(input.getResponsibleUser());
			
			Hotel updatedHotel = hotelRepository.save(hotel);
			
			httpResoponse.put(HttpStatus.OK, "");
			methodResponse.put(updatedHotel, httpResoponse);
			return methodResponse;
		}
	}
	
	private Hotel findActiveHotelByCode(String hotelCode) {	
		if(hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).isEmpty()) {
			return null;
		} else if(hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).size() > 1){
			throw new RuntimeException("More than one active hotel exists for the hotel code " + hotelCode);
		} else {
			return hotelRepository.findByHotelCodeAndStatus(hotelCode, Status.A).get(0);
		}
	}
	
	private Hotel findHotelByCode(String hotelCode) {	
		if(hotelRepository.findByHotelCode(hotelCode).isEmpty()) {
			return null;
		} else if(hotelRepository.findByHotelCode(hotelCode).size() > 1){
			throw new RuntimeException("More than one active hotel exists for the hotel code " + hotelCode);
		} else {
			return hotelRepository.findByHotelCode(hotelCode).get(0);
		}
	}
	
	private HotelAddress findHotelAddressByAddressType(Hotel hotel, AddressType addressType) {
		if(hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).isEmpty()) {
			return null;
		} else if(hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the adress type " + addressType);
		} else {
			return hotelAddressRepository.findByHotelAndAddressType(hotel, addressType).get(0);
		}
	}
	
	private HotelContact findHotelContactByContactType(Hotel hotel, ContactType contactType) {
		if(hotelContactRepository.findByHotelAndContactType(hotel, contactType).isEmpty()) {
			return null;
		} else if(hotelContactRepository.findByHotelAndContactType(hotel, contactType).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the contact type " + contactType);
		} else {
			return hotelContactRepository.findByHotelAndContactType(hotel, contactType).get(0);
		}
	}
}
