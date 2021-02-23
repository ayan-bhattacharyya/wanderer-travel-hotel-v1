package com.travel.hotel.base.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.hotel.base.api.dto.UserDTO;
import com.travel.hotel.base.entity.ApplicationUser;
import com.travel.hotel.base.service.UserSignUpServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserSignUpServiceImpl userSignupService;
	
	
	public UserController(UserSignUpServiceImpl userSignupService) {
		this.userSignupService = userSignupService;
	}
	
	@PostMapping("/sign-up")
    public ApplicationUser signUp(@RequestBody UserDTO request) {
		return userSignupService.signUp(request);
    }
	
	

}
