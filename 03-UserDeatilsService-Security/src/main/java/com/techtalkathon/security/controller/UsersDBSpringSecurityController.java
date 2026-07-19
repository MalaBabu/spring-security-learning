package com.techtalkathon.security.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtalkathon.security.entity.UserEntity;
import com.techtalkathon.service.UserEntityService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/security-userservice")
@Slf4j
public class UsersDBSpringSecurityController {
	
	@Autowired
	private UserEntityService userEntityService;
	
	@GetMapping("/greeting")
	public String greeting() {
		return "Hello Buddy";
	}
	
	@GetMapping("/reply")
	public String reply() {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		UserDetails user = (UserDetails) authentication.getPrincipal();
		String password = user.getPassword();
		String username = user.getUsername();
		
		log.info("username={} , password={} , authorities={}", username, password, authorities);
		
		return "Hello";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Hey Buddy welcome to my application";
	}

	@PostMapping("/register")
	public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
		UserEntity saveUserDeatils = userEntityService.saveUserDeatils(user);
		return ResponseEntity.ok(saveUserDeatils);
	}
	
}
