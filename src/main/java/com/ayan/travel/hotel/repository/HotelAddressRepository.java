package com.ayan.travel.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ayan.travel.hotel.domain.AddressType;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelAddress;

@RepositoryRestResource(exported = false)
public interface HotelAddressRepository extends CrudRepository<HotelAddress, Long> {
	
	List<HotelAddress> findByAddressLine4AndCountry(String city, String country);
	List<HotelAddress> findByHotel(Hotel hotel);
	List<HotelAddress> findByHotelAndAddressType(Hotel hotel, AddressType addressType);
}
