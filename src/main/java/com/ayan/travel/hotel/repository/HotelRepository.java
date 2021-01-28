package com.ayan.travel.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ayan.travel.hotel.entity.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
	
	List<Hotel> findByHotelCode(String hotelCode);
	List<Hotel> findByHotelName(String hotelName);
	
	

}
