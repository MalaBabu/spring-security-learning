package com.techtalkathon.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techtalkathon.security.entity.UserEntity;
import com.techtalkathon.security.repo.UserEntityRepository;

@Service
public class CustomUserEntityUserDetailsService implements UserDetailsService {

	@Autowired
	private UserEntityRepository userEntityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = 
				userEntityRepository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User Not Found"));
		
		return User.withUsername(userEntity.getUsername())
					.password(userEntity.getPassword())
					.roles(userEntity.getRole())
					.build();
	}

}
