package com.techtalkathon.security.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtalkathon.security.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, String> {

	Optional< UserEntity> findByUsername(String username);

}
