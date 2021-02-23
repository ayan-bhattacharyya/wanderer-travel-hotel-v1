package com.travel.hotel.base.auth;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.travel.hotel.base.domain.Constants;

public class AuthorizationFilter extends BasicAuthenticationFilter {
	
	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
		System.out.println();
		
		if(req != null) {
			String headerStr = req.getHeader(Constants.HEADER);
			
			if(StringUtils.isBlank(headerStr) || !headerStr.startsWith(Constants.TOKEN_PREFIX)) {
				chain.doFilter(req, res);
			} else {
				UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				chain.doFilter(req, res);
			}
		}
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(Constants.HEADER);
		
		if(StringUtils.isNotBlank(token)) {
			String user = JWT.require(Algorithm.HMAC512(Constants.SECRET))
					.build()
					.verify(token.replace(Constants.TOKEN_PREFIX, ""))
					.getSubject();
			
			if(StringUtils.isNotBlank(user)) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	

}
