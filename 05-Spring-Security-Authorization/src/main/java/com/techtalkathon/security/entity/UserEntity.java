package com.techtalkathon.security.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.techtalkathon.security.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usercredentials")
@NoArgsConstructor
@Data
public class UserEntity implements UserDetails {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role.name())); // Adding Role

		Set<SimpleGrantedAuthority> permitionAuthorities = role.getPermissions()
				.stream()
				.map(permission -> new SimpleGrantedAuthority(permission.name()))
				.collect(Collectors.toSet());
		
		authorities.addAll(permitionAuthorities);
		
		return authorities;
		
	}
}
