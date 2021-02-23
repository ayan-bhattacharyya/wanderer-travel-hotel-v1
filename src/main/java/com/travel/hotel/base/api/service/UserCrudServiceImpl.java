package com.travel.hotel.base.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.hotel.base.api.dto.UserDTO;
import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.entity.ApplicationUser;
import com.travel.hotel.base.exception.ElementExistsException;
import com.travel.hotel.base.repository.UserRepository;

@Service
public class UserCrudServiceImpl {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public UserCrudServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public ApplicationUser createUser(UserDTO input) {

		if (userRepository.findByUsername(input.getUsername()) != null) {
			throw new ElementExistsException(Constants.USER_USERNAME_EXISTS);

		} else if (userRepository.findByEmail(input.getEmail()) != null) {
			throw new ElementExistsException(Constants.USER_EMAIL_EXISTS);

		} else if (userRepository.findByMobile(input.getMobile()) != null) {
			throw new ElementExistsException(Constants.USER_MOBILE_EXISTS);

		} else {

			return userRepository.save(new ApplicationUser.UserBuilder().withUsername(input.getUsername())
					.withPassword(bCryptPasswordEncoder.encode(input.getPassword())).withEmail(input.getEmail())
					.withMobile(input.getMobile()).withCreatedBy(input.getUser()).build());
		}

	}

}
