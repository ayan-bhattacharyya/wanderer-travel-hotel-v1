package com.ayan.travel.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ayan.travel.hotel.domain.Status;
import com.ayan.travel.hotel.entity.Hotel;

@RepositoryRestResource(exported = false)
public interface HotelRepository extends CrudRepository<Hotel, Long> {
	
	List<Hotel> findByHotelCodeAndStatus(String hotelCode, Status status);
	List<Hotel> findByHotelCode(String hotelCode);
	List<Hotel> findByHotelName(String hotelName);
	
	

}
