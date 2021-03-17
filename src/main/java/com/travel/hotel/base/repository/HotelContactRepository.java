package com.travel.hotel.base.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.travel.hotel.base.domain.ContactType;
import com.travel.hotel.base.entity.Hotel;
import com.travel.hotel.base.entity.HotelContact;

@Repository
@RepositoryRestResource(exported = false)
public interface HotelContactRepository extends CrudRepository<HotelContact, Long> {

	List<HotelContact> findByHotelAndTypeAndIsPrimary(Hotel hotel, ContactType type, Boolean isPrimary);
	
	List<HotelContact> findByHotelAndType(Hotel hotel, ContactType type);

	List<HotelContact> findByHotel(Hotel hotel);

}
