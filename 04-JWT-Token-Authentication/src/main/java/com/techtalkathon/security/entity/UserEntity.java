package com.techtalkathon.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usercredentials")
@NoArgsConstructor
@Data
public class UserEntity {

    @Id
    private String username;

    private String password;

    private String role;
}
