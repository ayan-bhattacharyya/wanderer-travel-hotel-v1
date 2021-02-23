package com.travel.hotel.base.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.travel.hotel.base.entity.ApplicationUser;
import com.travel.hotel.base.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UserRepository appUserRepo;

	public UserDetailsServiceImpl(UserRepository appUserRepo) {
		this.appUserRepo = appUserRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser appUser = appUserRepo.findByUsername(username);
		if(appUser == null) {
			throw new UsernameNotFoundException(username);
		}
		return new User(appUser.getUsername(), appUser.getPassword(), Collections.emptyList());
	}

}
