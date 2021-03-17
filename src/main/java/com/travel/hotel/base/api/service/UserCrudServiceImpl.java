package com.travel.hotel.base.api.service;

import java.util.NoSuchElementException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.travel.hotel.base.api.dto.UserDTO;
import com.travel.hotel.base.domain.Constants;
import com.travel.hotel.base.entity.ApplicationUser;
import com.travel.hotel.base.exception.ElementExistsException;
import com.travel.hotel.base.exception.ElementUpdatedException;
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
	
	public ApplicationUser updateUser(UserDTO input) {
		ApplicationUser user = userRepository.findByUsername(input.getUsername());
		
		if(user == null) {
			throw new NoSuchElementException(Constants.USER_USERNAME_NOT_EXISTS);
		} 
		
		if (StringUtils.isNotBlank(user.getModifiedAt()) || StringUtils.isBlank(input.getReadDateTime())) {
			throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);
		} else if (StringUtils.isBlank(user.getModifiedAt())
				|| input.getReadDateTime().equals(user.getModifiedAt())) {
			if(StringUtils.isNotBlank(input.getPassword())) {
				user.setPassword(bCryptPasswordEncoder.encode(input.getPassword()));
			}
			if(StringUtils.isNotBlank(input.getEmail())) {
				user.setEmail(input.getEmail());
			}
			if(StringUtils.isNotBlank(input.getMobile())) {
				user.setMobile(input.getMobile());
			}
			user.setModifiedAt();
			user.setModifiedBy(input.getUser());
			
			return userRepository.save(user);
			
		} else {
			throw new ElementUpdatedException(Constants.RESOURCE_UPDATED);
		}

	}
	
	public void deleteUser(String username) {
		ApplicationUser user = userRepository.findByUsername(username);
		
		if(user == null) {
			throw new NoSuchElementException(Constants.USER_USERNAME_NOT_EXISTS);
		} else {
			userRepository.delete(user);
		}
		
	}

}
