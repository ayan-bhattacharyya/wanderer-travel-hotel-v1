package com.travel.hotel.base.repository;

import org.springframework.data.repository.CrudRepository;

import com.travel.hotel.base.entity.UserAddress;

public interface UserAddressRepository extends CrudRepository<UserAddress, Long> {

}
