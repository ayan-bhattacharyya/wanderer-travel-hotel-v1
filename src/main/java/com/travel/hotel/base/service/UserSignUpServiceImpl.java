package com.travel.hotel.base.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.hotel.base.api.dto.UserDTO;
import com.travel.hotel.base.api.service.UserCrudServiceImpl;
import com.travel.hotel.base.entity.ApplicationUser;

@Service
public class UserSignUpServiceImpl {

	private UserCrudServiceImpl userCrudServiceImpl;

	@Autowired
	public UserSignUpServiceImpl(UserCrudServiceImpl userCrudServiceImpl) {
		this.userCrudServiceImpl = userCrudServiceImpl;
	}

	@Transactional
	public ApplicationUser signUp(UserDTO input) {
		return userCrudServiceImpl.createUser(input);
	}

}
