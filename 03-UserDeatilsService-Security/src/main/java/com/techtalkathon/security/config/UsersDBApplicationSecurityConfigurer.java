package com.techtalkathon.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
public class UsersDBApplicationSecurityConfigurer {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable()) // this is required
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(
	                "/security-userservice/welcome",
	                "/security-userservice/register"
	            ).permitAll()
	            .anyRequest().authenticated())
	        .httpBasic(Customizer.withDefaults())
	        .formLogin(Customizer.withDefaults());

	    return http.build();
	}
	
	
//	@SuppressWarnings("deprecation")
//	@Bean
//	public InMemoryUserDetailsManager inMemoryUsers() {
//		
//		UserDetails dingaUserDetails = User.withDefaultPasswordEncoder()
//		.username("Dinga").password("Dinga@123").build();
//		
//		UserDetails dingiCredentials = User.withDefaultPasswordEncoder()
//				.username("Dingi").password("Dingi@123").build();
//		
//		
//		return new InMemoryUserDetailsManager(dingaUserDetails,dingiCredentials);
//	}
//	

	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
