package com.ayan.travel.hotel.exception;

import org.springframework.data.repository.CrudRepository;

import com.ayan.travel.hotel.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
