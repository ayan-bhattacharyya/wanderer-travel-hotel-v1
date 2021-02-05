package com.ayan.travel.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ayan.travel.hotel.domain.ContactType;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.HotelContact;

@Repository
@RepositoryRestResource(exported = false)
public interface HotelContactRepository extends CrudRepository<HotelContact, Long> {

	List<HotelContact> findByHotelAndContactType(Hotel hotel, ContactType contactType);

	List<HotelContact> findByHotel(Hotel hotel);

}
