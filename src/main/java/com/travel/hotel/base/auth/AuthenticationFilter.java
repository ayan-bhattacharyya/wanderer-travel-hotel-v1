package com.travel.hotel.base.auth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.hotel.base.api.dto.UserDTO;
import com.travel.hotel.base.domain.Constants;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authManager;

	public AuthenticationFilter(AuthenticationManager authManager) {
		this.authManager = authManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
            HttpServletResponse res) {
		try {
			UserDTO user = new ObjectMapper().readValue(req.getInputStream(), UserDTO.class);
			return authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
            FilterChain chain, Authentication auth) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, Constants.TOKEN_EXPIRE);
		Date expireAt = calendar.getTime();
		
		String token = JWT.create().withSubject(((User)auth.getPrincipal()).getUsername())
				.withExpiresAt(expireAt)
				.sign(Algorithm.HMAC512(Constants.SECRET.getBytes()));
		
		System.out.println("Expires At" + expireAt);
		
		res.addHeader(Constants.HEADER, Constants.TOKEN_PREFIX + token);
				
		
	}

}
