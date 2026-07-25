package com.techtalkathon.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.techtalkathon.security.enums.Permissions;
import com.techtalkathon.security.enums.Role;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
public class ApplicationSecurityAuthorizationConfigurer {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable()) // this is required
				.authorizeHttpRequests(auth -> 
				
					auth.requestMatchers("/**/welcome", "/**/register").permitAll()

						.requestMatchers(HttpMethod.GET, "/**/health","/**/greeting","/**/users").hasAuthority(Permissions.USER_READ.name())
						.requestMatchers(HttpMethod.POST,"/**/login").hasAuthority(Permissions.USER_READ.name())
						.requestMatchers(HttpMethod.PUT,"/**/update").hasAuthority(Permissions.USER_WRITE.name())
						.requestMatchers(HttpMethod.DELETE,"/**/remove/*").hasAuthority(Permissions.USER_DELETE.name())

						.anyRequest().authenticated())
	        
	        .httpBasic(Customizer.withDefaults())
	        .formLogin(Customizer.withDefaults());

	    return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	
	
	
}
