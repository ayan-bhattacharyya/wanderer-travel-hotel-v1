package com.travel.hotel.base.repository;

import org.springframework.data.repository.CrudRepository;

import com.travel.hotel.base.entity.ApplicationUser;

public interface UserRepository extends CrudRepository<ApplicationUser, Long> {
	
	ApplicationUser findByUsername(String username);
	ApplicationUser findByEmail(String email);
	ApplicationUser findByMobile(String mobile);
	
	
	

}
