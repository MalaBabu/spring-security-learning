package com.techtalkathon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techtalkathon.security.entity.UserEntity;
import com.techtalkathon.security.repo.UserEntityRepository;

@Service
public class UserEntityService {

	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserEntity saveUserDeatils(UserEntity entity) {
		String encodedPassword = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(encodedPassword);
		return userEntityRepository.save(entity);
	}
	
}
