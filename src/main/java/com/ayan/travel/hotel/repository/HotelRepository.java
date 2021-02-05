package com.ayan.travel.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ayan.travel.hotel.domain.Status;
import com.ayan.travel.hotel.entity.Hotel;

@Repository
@RepositoryRestResource(exported = false)
public interface HotelRepository extends CrudRepository<Hotel, Long> {

	List<Hotel> findByHotelCodeAndStatus(String hotelCode, Status status);

	List<Hotel> findByHotelCode(String hotelCode);

	List<Hotel> findByHotelNameAndStatus(String hotelName, Status status);

}
