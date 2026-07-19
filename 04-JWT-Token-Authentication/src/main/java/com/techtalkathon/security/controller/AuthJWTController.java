package com.techtalkathon.security.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtalkathon.security.model.AuthRequest;
import com.techtalkathon.security.utility.JWTUtils;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/security-jwt")
@Slf4j
public class AuthJWTController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	
	private JWTUtils jwtUtils;
	

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) {
		Collection<? extends GrantedAuthority> authorities = null;
		String password = null;
		String username = null;
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
			
			// Manual Authentication Without Filters
			log.info("************ authentication success ***************");
			authorities = authentication.getAuthorities();
			log.info(""+authentication.getPrincipal());
			UserDetails user = (UserDetails) authentication.getPrincipal();
			password = user.getPassword();
			username = user.getUsername();
		} catch (Exception e) {
			throw e;
		}

		log.info("username={} , password={} , authorities={}", username, password, authorities);

		return jwtUtils.generateToken(authRequest.getUsername());

	}

}
