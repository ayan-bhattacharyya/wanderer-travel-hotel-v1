package com.travel.hotel.base.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.hotel.base.api.dto.HotelAddressDTO;
import com.travel.hotel.base.domain.AddressType;
import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.entity.Hotel;
import com.travel.hotel.base.entity.HotelAddress;
import com.travel.hotel.base.exception.ElementExistsException;
import com.travel.hotel.base.exception.ElementUpdatedException;
import com.travel.hotel.base.exception.InputMappingException;
import com.travel.hotel.base.repository.HotelAddressRepository;

@Service
public class HotelAddressCrudServiceImpl {
	private static final Logger LOGGER = LoggerFactory.getLogger(HotelAddressCrudServiceImpl.class);

	private HotelAddressRepository hotelAddressRepository;
	private HotelCrudServiceImpl hotelCrudServiceImpl;

	@Autowired
	public HotelAddressCrudServiceImpl(HotelAddressRepository hotelAddressRepository,
			HotelCrudServiceImpl hotelCrudServiceImpl) {
		this.hotelAddressRepository = hotelAddressRepository;
		this.hotelCrudServiceImpl = hotelCrudServiceImpl;
	}

	/**
	 * This method creates a new Hotel Address Entity
	 * 
	 * @param {Hotel Request} {@link} Please see
	 *               com.travel.hotel.base.api.dto.HotelAddressDTO for more details
	 */
	public HotelAddress createHotelAddress(HotelAddressDTO input, String hotelCode) {

		Hotel hotel = validateAndGetHotel(input, hotelCode);
		
		if(!input.getIsPrimary()) {
		return hotelAddressRepository.save(new HotelAddress.HotelAddressBuilder().withHotel(hotel)
				.withType(input.getAddressType()).withIsPrimary(input.getIsPrimary()).withAddressLine1(input.getAddressLine1())
				.withAddressLine2(input.getAddressLine2()).withAddressLine3(input.getAddressLine3())
				.withCity(input.getCity()).withState(input.getState()).withPostcode(input.getPostcode())
				.withCountry(input.getCountry()).withCreatedBy(input.getUser()).build());
		} else {
			HotelAddress primaryAddress = getPrimaryAddressByType(hotel, input.getAddressType());
			if(primaryAddress == null) {
				return hotelAddressRepository.save(new HotelAddress.HotelAddressBuilder().withHotel(hotel)
						.withType(input.getAddressType()).withIsPrimary(input.getIsPrimary()).withAddressLine1(input.getAddressLine1())
						.withAddressLine2(input.getAddressLine2()).withAddressLine3(input.getAddressLine3())
						.withCity(input.getCity()).withState(input.getState()).withPostcode(input.getPostcode())
						.withCountry(input.getCountry()).withCreatedBy(input.getUser()).build());
			} else {
				throw new ElementExistsException(Constants.PRIMARY_HOTEL_ADDRESS_EXISTS);
			}
		}

	}

	public HotelAddress updateHotelAddress(HotelAddressDTO input, String hotelCode) {

		Hotel hotel = validateAndGetHotel(input, hotelCode);

		HotelAddress hotelAddress = getAddressByType(hotel, input.getAddressType());

		if (hotelAddress == null) {
			throw new NoSuchElementException(Constants.HOTEL_ADDRESS_NOT_EXISTS);

		} else {

			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Latest Update Date time {}", hotelAddress.getModifiedAt());
			}

			if (StringUtils.isBlank(input.getReadDateTime()) && StringUtils.isNotBlank(hotelAddress.getModifiedAt())) {
				throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);

			} else if (StringUtils.isBlank(hotelAddress.getModifiedAt())
					|| input.getReadDateTime().equals(hotelAddress.getModifiedAt())) {
				
				if(StringUtils.isNotBlank(input.getAddressLine1())) {
					hotelAddress.setAddressLine1(input.getAddressLine1());
				}
				if(StringUtils.isNotBlank(input.getAddressLine2())){
					hotelAddress.setAddressLine2(input.getAddressLine2());
				}
				if(StringUtils.isNotBlank(input.getAddressLine3())){
					hotelAddress.setAddressLine3(input.getAddressLine3());
				}
				if(StringUtils.isNotBlank(input.getCity())){
					hotelAddress.setCity(input.getCity());
				}
				if(StringUtils.isNotBlank(input.getState())){
					hotelAddress.setState(input.getState());
				}
				if(StringUtils.isNotBlank(input.getPostcode())){
					hotelAddress.setPostcode(input.getPostcode());
				}
				if(StringUtils.isNotBlank(input.getCountry())){
					hotelAddress.setCountry(input.getCountry());
				}
				hotelAddress.setIsPrimary(input.getIsPrimary());
				hotelAddress.setModifiedAt();
				hotelAddress.setModifiedBy(input.getUser());

				return hotelAddressRepository.save(hotelAddress);

			} else {
				throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);
			}
		}
	}

	public void deleteAllHotelAddress(String hotelCode) {
		Hotel hotel = hotelCrudServiceImpl.getHotelByCode(hotelCode);

		if (hotel == null) {
			throw new NoSuchElementException(Constants.HOTEL_NOT_EXISTS);
		} else {
			List<HotelAddress> hotelAddresses = hotelAddressRepository.findByHotel(hotel);
			hotelAddressRepository.deleteAll(hotelAddresses);
		}
	}

	public void deleteHotelAddress(String hotelCode, String addressType) {
		Hotel hotel = hotelCrudServiceImpl.getHotelByCode(hotelCode);

		if (hotel == null) {
			throw new NoSuchElementException(Constants.HOTEL_NOT_EXISTS);
		}

		HotelAddress hotelAddress = getAddressByType(hotel, addressType);
		if (hotelAddress == null) {
			throw new NoSuchElementException(Constants.HOTEL_ADDRESS_NOT_EXISTS);

		} else {
			hotelAddressRepository.delete(hotelAddress);
		}
	}

	private HotelAddress getAddressByType(Hotel hotel, String addressType) {

		if (hotelAddressRepository.findByHotelAndType(hotel, AddressType.findByLabel(addressType)).size() == 0) {
			return null;

		} else if (hotelAddressRepository.findByHotelAndType(hotel, AddressType.findByLabel(addressType)).size() > 1) {
			throw new RuntimeException("More than one hotel Address exists for the adress type " + addressType);

		} else {
			HotelAddress hotelAddress = hotelAddressRepository
					.findByHotelAndType(hotel, AddressType.findByLabel(addressType)).get(0);

			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Hotel address details {}", hotelAddress);
			}
			return hotelAddress;
		}
	}
	
	private HotelAddress getPrimaryAddressByType(Hotel hotel, String addressType) {
		if (hotelAddressRepository.findByHotelAndTypeAndIsPrimary(hotel, AddressType.findByLabel(addressType), Boolean.TRUE).size() == 0) {
			return null;

		} else if (hotelAddressRepository.findByHotelAndTypeAndIsPrimary(hotel, AddressType.findByLabel(addressType), Boolean.TRUE).size() > 1) {
			throw new RuntimeException("More than one primary hotel Address exists for the adress type " + addressType);

		} else {
			HotelAddress hotelAddress = hotelAddressRepository
					.findByHotelAndTypeAndIsPrimary(hotel, AddressType.findByLabel(addressType), Boolean.TRUE).get(0);

			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("Hotel address details {}", hotelAddress);
			}
			return hotelAddress;
		}
	}

	private Hotel validateAndGetHotel(HotelAddressDTO input, String hotelCode) {
		if (StringUtils.isBlank(input.getAddressType())) {
			throw new InputMappingException(Constants.HOTEL_ADDRESS_TYPE_MISSING);

		} else if (input.getIsPrimary() == null) {
			throw new InputMappingException(Constants.HOTEL_ADDRESS_PRIMARY_MISSING);
			
		} else if (StringUtils.isBlank(input.getAddressLine1())) {
			throw new InputMappingException(Constants.HOTEL_ADDRESS_LINE1_MISSING);

		} else if (StringUtils.isBlank(input.getCity())) {
			throw new InputMappingException(Constants.HOTEL_CITY_MISSING);

		} else if (StringUtils.isBlank(input.getState())) {
			throw new InputMappingException(Constants.HOTEL_STATE_MISSING);

		} else if (StringUtils.isBlank(input.getPostcode())) {
			throw new InputMappingException(Constants.HOTEL_POSTCODE_MISSING);

		} else if (StringUtils.isBlank(input.getCountry())) {
			throw new InputMappingException(Constants.HOTEL_COUNTRY_MISSING);

		} else if (StringUtils.isBlank(input.getUser())) {
			throw new InputMappingException(Constants.USER_MISSING);

		} else if (hotelCrudServiceImpl.getHotelByCode(hotelCode) == null) {
			throw new NoSuchElementException(Constants.HOTEL_NOT_EXISTS);
		} else {
			return hotelCrudServiceImpl.getHotelByCode(hotelCode);
		}
	}

}
