package com.travel.hotel.base.repository;

import org.springframework.data.repository.CrudRepository;

import com.travel.hotel.base.entity.RoomReservation;


public interface RoomReservationRepository extends CrudRepository<RoomReservation, Long> {

}
