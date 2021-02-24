package com.travel.hotel.base.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.hotel.base.api.dto.UserDTO;
import com.travel.hotel.base.api.service.UserCrudServiceImpl;
import com.travel.hotel.base.entity.ApplicationUser;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserCrudServiceImpl userCrudServiceImpl;
	
	public UserController(UserCrudServiceImpl userCrudServiceImpl) {
		this.userCrudServiceImpl = userCrudServiceImpl;
	}
	
	@PostMapping
    public ApplicationUser signUp(@RequestBody UserDTO request) {
		return userCrudServiceImpl.createUser(request);
    }
	
	

}
