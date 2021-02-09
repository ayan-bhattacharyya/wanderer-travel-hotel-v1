package com.ayan.travel.hotel.exception;

import org.springframework.data.repository.CrudRepository;

import com.ayan.travel.hotel.entity.UserAddress;

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

}
