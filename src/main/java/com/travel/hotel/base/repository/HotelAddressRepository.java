package com.travel.hotel.base.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.travel.hotel.base.domain.AddressType;
import com.travel.hotel.base.entity.Hotel;
import com.travel.hotel.base.entity.HotelAddress;

@Repository
@RepositoryRestResource(exported = false)
public interface HotelAddressRepository extends CrudRepository<HotelAddress, Long> {

	List<HotelAddress> findByCityAndCountry(String city, String country);
	
	List<HotelAddress> findByPostcode(String postcode);
	
	List<HotelAddress> findByState(String state);

	List<HotelAddress> findByHotel(Hotel hotel);

	List<HotelAddress> findByHotelAndType(Hotel hotel, AddressType type);
}
