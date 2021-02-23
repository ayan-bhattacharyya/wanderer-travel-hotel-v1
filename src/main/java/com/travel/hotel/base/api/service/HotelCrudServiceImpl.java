package com.travel.hotel.base.api.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.hotel.base.api.dto.HotelDTO;
import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.entity.Hotel;
import com.travel.hotel.base.exception.ElementExistsException;
import com.travel.hotel.base.exception.ElementUpdatedException;
import com.travel.hotel.base.exception.InputMappingException;
import com.travel.hotel.base.repository.HotelAddressRepository;
import com.travel.hotel.base.repository.HotelContactRepository;
import com.travel.hotel.base.repository.HotelRepository;

@Service
public class HotelCrudServiceImpl {

	private HotelRepository hotelRepository;
	private HotelAddressRepository hotelAddressRepository;
	private HotelContactRepository hotelContactRepository;

	@Autowired
	public HotelCrudServiceImpl(HotelRepository hotelRepository, HotelAddressRepository hotelAddressRepository,
			HotelContactRepository hotelContactRepository) {
		this.hotelRepository = hotelRepository;
		this.hotelAddressRepository = hotelAddressRepository;
		this.hotelContactRepository = hotelContactRepository;
	}

	protected Hotel getHotelByCode(String code) {
		List<Hotel> hotels = hotelRepository.findByCode(code);
		if (hotels.size() == 0) {
			return null;
		} else if (hotels.size() > 1) {
			throw new RuntimeException("More than one hotel exists corresponding to the code");
		} else {
			return hotels.get(0);
		}
	}

	/**
	 * This method creates a new Hotel Entity
	 * 
	 * @param {Hotel Request} {@link} Please see
	 *               com.travel.hotel.base.api.dto.HotelDTO for more details
	 */
	public Hotel createHotel(HotelDTO input) {

		validateMandatoryFields(input);

		if (getHotelByCode(input.getHotelCode()) != null) {
			throw new ElementExistsException(Constants.HOTEL_EXISTS);

		} else if (StringUtils.isBlank(input.getStatus())) {
			return hotelRepository.save(new Hotel.HotelBuilder().withCode(input.getHotelCode())
					.withName(input.getHotelName()).withStatus("Active").withCreatedBy(input.getUser()).build());
		} else {
			return hotelRepository
					.save(new Hotel.HotelBuilder().withCode(input.getHotelCode()).withName(input.getHotelName())
							.withStatus(input.getStatus()).withCreatedBy(input.getUser()).build());
		}
	}

	/**
	 * This method updates a Hotel Entity
	 * 
	 * @param {Hotel Request} {@link} Please see
	 *               com.travel.hotel.base.api.dto.HotelDTO for more details
	 */
	public Hotel updateHotel(HotelDTO input) {

		validateMandatoryFields(input);
		Hotel hotel = getHotelByCode(input.getHotelCode());

		if (hotel == null) {
			throw new NoSuchElementException(Constants.HOTEL_NOT_EXISTS);

		} else {
			if (StringUtils.isBlank(input.getReadDateTime()) && StringUtils.isNotBlank(hotel.getModifiedAt())) {
				throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);

			} else if (StringUtils.isBlank(hotel.getModifiedAt())
					|| input.getReadDateTime().equals(hotel.getModifiedAt())) {
				hotel.setName(input.getHotelName());
				hotel.setModifiedAt();
				hotel.setModifiedBy(input.getUser());
				return hotelRepository.save(hotel);

			} else {
				throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);
			}
		}
	}

	/**
	 * This method deletes a Hotel and all the referenced Entity
	 * 
	 * @param {Hotel Request} {@link} Please see
	 *               com.travel.hotel.base.api.dto.HotelDTO for more details
	 */

	public void deleteHotel(String code) {
		Hotel hotel = getHotelByCode(code);
		if (hotel == null) {
			throw new NoSuchElementException(Constants.HOTEL_NOT_EXISTS);

		} else {
			if (hotelAddressRepository.findByHotel(hotel).size() > 0) {
				throw new ElementExistsException(Constants.HOTEL_ADDRESS_EXISTS);

			} else if (hotelContactRepository.findByHotel(hotel).size() > 0) {
				throw new ElementExistsException(Constants.HOTEL_CONTACT_EXISTS);

			} else {
				hotelRepository.delete(hotel);
			}
		}

	}

	private Boolean validateMandatoryFields(HotelDTO input) {
		if (StringUtils.isBlank(input.getHotelCode())) {
			throw new InputMappingException(Constants.HOTEL_CODE_MISSING);

		} else if (StringUtils.isBlank(input.getHotelName())) {
			throw new InputMappingException(Constants.HOTEL_NAME_MISSING);

		} else if (StringUtils.isBlank(input.getUser())) {
			throw new InputMappingException(Constants.USER_MISSING);

		} else {
			return Boolean.TRUE;
		}
	}
}
