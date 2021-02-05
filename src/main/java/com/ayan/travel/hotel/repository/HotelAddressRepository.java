package com.ayan.travel.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ayan.travel.hotel.domain.AddressType;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelAddress;

@Repository
@RepositoryRestResource(exported = false)
public interface HotelAddressRepository extends CrudRepository<HotelAddress, Long> {

	List<HotelAddress> findByAddressLine4AndCountry(String addressLine4, String country);
	
	List<HotelAddress> findByPostcode(String postcode);
	
	List<HotelAddress> findByState(String state);

	List<HotelAddress> findByHotel(Hotel hotel);

	List<HotelAddress> findByHotelAndAddressType(Hotel hotel, AddressType addressType);
}
