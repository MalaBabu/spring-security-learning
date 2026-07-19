package com.techtalkathon.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Configuration
@Slf4j
public class InMemoryApplicationSecurityConfigurer {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests(
				auth -> auth.requestMatchers("/security-inmemory/welcome").permitAll().anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());
		return http.build();

	}
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public InMemoryUserDetailsManager inMemoryUsers() {
		
		UserDetails dingaUserDetails = User.withDefaultPasswordEncoder()
		.username("Dinga").password("Dinga@123").build();
		
		UserDetails dingiCredentials = User.withDefaultPasswordEncoder()
				.username("Dingi").password("Dingi@123").build();
		
		
		return new InMemoryUserDetailsManager(dingaUserDetails,dingiCredentials);
	}
	



}
