package com.ayan.travel.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ayan.travel.hotel.domain.ContactType;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelContact;

public interface HotelContactRepository extends CrudRepository<HotelContact, Long> {
	
	List<HotelContact> findByHotelAndContactType(Hotel hotel, ContactType contactType);
	

}
