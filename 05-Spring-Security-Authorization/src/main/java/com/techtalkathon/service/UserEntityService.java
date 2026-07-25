package com.techtalkathon.service;

import java.util.List;
import java.util.Optional;

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
	
	public UserEntity saveUserDetails(UserEntity entity) {
		String encodedPassword = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(encodedPassword);
		return userEntityRepository.save(entity);
	}
	
	public UserEntity updateUserDetails(UserEntity entity) {
		UserEntity userEntity = null;
		Optional<UserEntity> byUsername = userEntityRepository.findByUsername(entity.getUsername());
		if (byUsername.isPresent()) {
			userEntity = byUsername.get();

			if (entity.getPassword() != null) {
				String pass = passwordEncoder.encode(entity.getPassword());
				userEntity.setPassword(pass);
			}

			if (entity.getRole() != null) {
				userEntity.setRole(entity.getRole());
			}
		}
		return userEntityRepository.save(userEntity);
	}
	
	public UserEntity deleteUserDeatils(String username) {
		Optional<UserEntity> byUsername = userEntityRepository.findByUsername(username);
		UserEntity user = null;
		if (byUsername.isPresent()) {
			user = byUsername.get();
			userEntityRepository.delete(user);
		}
		return user;

	}


	public List<UserEntity> getUsers() {
		return userEntityRepository.findAll();
	}
}
