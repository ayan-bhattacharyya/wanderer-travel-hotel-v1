package com.travel.hotel.base.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.travel.hotel.base.domain.RoomType;
import com.travel.hotel.base.entity.Hotel;
import com.travel.hotel.base.entity.Room;

@Repository
@RepositoryRestResource(exported = false)
public interface RoomRepository extends CrudRepository<Room, Long> {
	
	List<Room> findByHotel(Hotel hotel);
	
	List<Room> findByHotelAndNumber(Hotel hotel, String number);
	
	List<Room> findByType(RoomType type);
	
	

}
