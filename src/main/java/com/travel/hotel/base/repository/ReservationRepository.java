package com.travel.hotel.base.repository;

import org.springframework.data.repository.CrudRepository;

import com.travel.hotel.base.entity.Reservation;


public interface ReservationRepository extends CrudRepository<Reservation, Long> {

}
