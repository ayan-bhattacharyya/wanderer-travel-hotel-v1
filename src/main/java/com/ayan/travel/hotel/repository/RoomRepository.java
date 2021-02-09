package com.ayan.travel.hotel.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.ayan.travel.hotel.domain.RoomType;
import com.ayan.travel.hotel.entity.Hotel;
import com.ayan.travel.hotel.entity.Room;

@Repository
@RepositoryRestResource(exported = false)
public interface RoomRepository extends CrudRepository<Room, Long> {
	
	List<Room> findByHotel(Hotel hotel);
	
	List<Room> findByRoomType(RoomType roomType);
	
	

}
